package com.example.museumapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.museumapp.model.resources.Work
import com.example.museumapp.ui.theme.greenDark
import com.example.museumapp.viewModel.AuthorViewModel
import com.example.museumapp.viewModel.AuthorWorksViewModel

@Composable
fun AuthorWorksScreen (viewModel: AuthorWorksViewModel) {
    var autor = viewModel.autor
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(5.dp)
    ){
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(10.dp)
        ){
            Row(
            ) {
                AsyncImage(
                    model = autor!!.cover,
                    contentDescription = null,
                    modifier = Modifier
                        .width(65.dp)
                        .height(65.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {
                    Text(
                        text = autor!!.name,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF010101),
                        )
                    )
                    Text(
                        text = autor!!.type,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(450),
                            color = Color(0xCC010101),
                        )
                    )
                    Text(
                        text = autor!!.place_Birth_and_Dead,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xCC010101),
                        ),
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "More info Icon",
                tint = greenDark,
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .padding(8.dp)
                    .clickable(){
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                color = greenDark,
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
            )

            Text(
                text = "Obras",
                style = TextStyle(
                    textAlign = TextAlign.Justify,
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )

            Divider(
                color = greenDark,
                modifier = Modifier
                    .weight(1f)
                    .height(2.dp)
            )
        }
        // Mostrar las obras
        autor?.works
            ?.filterNotNull()
            ?.forEach { obra ->
                ObraItem(obra)
            }

    }
}
@Composable
fun ObraItem(obra: Work) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        obra.cover?.let { coverUrl ->
            AsyncImage(
                model = coverUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(65.dp)
                    .height(65.dp)
                    .padding(end = 8.dp),  // Espaciado entre la imagen y el texto
                contentScale = ContentScale.Crop
            )
        }

        Column {
            Text(
                text = obra.name,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF010101),
                )
            )
            Text(
                text = "${obra.temp}",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(450),
                    color = Color(0xCC010101),
                ),
            )
        }
    }
}