package com.example.museumapp.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.museumapp.model.resources.TicketType
import com.example.museumapp.model.resources.TicketTypes
import com.example.museumapp.ui.theme.blue
import com.example.museumapp.ui.theme.green
import com.example.museumapp.ui.theme.gris
import com.example.museumapp.viewModel.BuyTicketViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.util.Locale


@Composable
fun TicketScreen(viewModel: BuyTicketViewModel) {
    val selectedDate: MutableState<LocalDate?> = viewModel.selectedDate
    val currentSelectedDate = selectedDate.value

    // Llama a la función loadCalendarData cuando sea necesario, por ejemplo, al inicializar la pantalla
    DisposableEffect(Unit) {
        onDispose {
            viewModel.loadCalendarData(LocalDate.now())
        }
    }

    // Muestra el encabezado y el contenido del calendario
    currentSelectedDate?.let { selectedDate ->
        Header(selectedDate, viewModel)
        Content(viewModel)
        TicketTypeSection(viewModel)
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end= 30.dp, start =30.dp, top = 55.dp )
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            border = BorderStroke(2.dp, green),
        ) {
           Text("Realizar Pago")
        }
    }
}

@Composable
fun Header(selectedDate: LocalDate, viewModel: BuyTicketViewModel) {
    Text(
        text = "Seleccione una fecha",
        fontSize = 22.sp,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(top = 20.dp)
    )
    Row {
        Text(
            // Muestra "Hoy" si el usuario selecciona la fecha de hoy
            // de lo contrario, muestra el formato completo de la fecha
            text = if (selectedDate.isEqual(LocalDate.now())) {
                "Hoy"
            } else {
                selectedDate.format(
                    DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale("es", "ES")) // Configura el idioma a español
                )
            },
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
                .padding(start = 12.dp)
        )
        IconButton(onClick = {
            // Acciones cuando se hace clic en la flecha izquierda
            viewModel.loadCalendarData(selectedDate.minusDays(7)) // Retroceder 7 días, por ejemplo
        }) {
            Icon(
                imageVector = Icons.Filled.ChevronLeft,
                contentDescription = "Atrás"
            )
        }
        IconButton(onClick = {
            // Acciones cuando se hace clic en la flecha derecha
            viewModel.loadCalendarData(selectedDate.plusDays(7)) // Avanzar 7 días, por ejemplo
        }) {
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "Adelante"
            )
        }
    }
}

@Composable
fun Content(viewModel: BuyTicketViewModel) {
    LazyRow(
        modifier = Modifier
            .padding(bottom =  12.dp) // Ajusta el valor según tus necesidades
    ) {
        items(viewModel.getVisibleDates()) { date ->
            ContentItem(date, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ContentItem(date: LocalDate, viewModel: BuyTicketViewModel) {
    val isSelected = date == viewModel.selectedDate.value
    val isTodayOrFuture = date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now())
    val backgroundColor = when {
        isSelected -> green
        isTodayOrFuture -> blue
        else -> gris
    }

    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp),
        backgroundColor = backgroundColor,
        onClick = {
            if (isTodayOrFuture) {
                viewModel.onDateSelected(date)
            }
        }
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale("es", "ES")),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Text(
                text = date.dayOfMonth.toString(),
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
        }
    }
}
@Composable
fun TicketTypeSection(viewModel: BuyTicketViewModel) {
    val ticketTypesList by TicketTypes.ticketTypesList.collectAsState()

    Column {
        ticketTypesList.forEach { ticketType ->
            TicketTypeCard(ticketType, viewModel)
        }
    }
}

@Composable
fun TicketTypeCard(ticketType: TicketType, viewModel: BuyTicketViewModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(60.dp),
        elevation = 4.dp
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Columna 1: Tipo de entrada
            Text(text = ticketType.name, fontWeight = FontWeight.Bold)

            // Columna 2: Precio
            Text(
                text = "${ticketType.price}€",
                textAlign = TextAlign.End,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 30.dp)
            )

            // Columna 3: Cantidad y botones
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                IconButton(onClick = { viewModel.decreaseTicketCount(ticketType) }) {
                    Icon(imageVector = Icons.Default.Remove, contentDescription = "Decrease")
                }
                Text(text = "${ticketType.quantity}", modifier = Modifier.padding(horizontal = 4.dp))
                IconButton(onClick = { viewModel.increaseTicketCount(ticketType) }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Increase")
                }
            }
        }
    }
}








