package com.example.museumapp.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.museumapp.R
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.homeViewModel

@Composable
fun HomeView(name: String, navController: NavController, modifier: Modifier = Modifier) {
    val homeViewModel : homeViewModel = hiltViewModel()
    val image = painterResource(R.drawable.fondo_homepage)


    Box (
        modifier = Modifier.fillMaxHeight()
    ) {
        Image(
            painter = image,
            contentDescription = null, // Descripción para accesibilidad
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajusta la imagen al tamaño del Box, recortando si es necesario
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp), // Agregamos relleno para separar los elementos del borde de la pantalla
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            val cornerRadius = 10.dp
            val btonHeight = 45.dp
            val fontSize = 18.sp
            Button(
                onClick = {
                    navController.navigate("authorsView")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(btonHeight),
                colors = ButtonDefaults.buttonColors(Color(0xFF1E1E1E)),
                shape = RoundedCornerShape(cornerRadius),
                border = BorderStroke(2.dp, Color(0xFF5FB49C))
            ) {
                Text(text = "Artistas",
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center),
                    color = Color(0xFF5FB49C)

                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    navController.navigate("worksView")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(btonHeight),
                colors = ButtonDefaults.buttonColors(Color(0xFF1E1E1E)),
                shape = RoundedCornerShape(cornerRadius),
                border = BorderStroke(2.dp, Color(0xFF5FB49C))
            ) {
                Text(text = "Obras",
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center),
                    color = Color(0xFF5FB49C)

                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    navController.navigate("coleccionesView")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(btonHeight),
                colors = ButtonDefaults.buttonColors(Color(0xFF1E1E1E)),
                shape = RoundedCornerShape(cornerRadius),
                border = BorderStroke(2.dp, Color(0xFF5FB49C))
            ) {
                Text(text = "Colecciones",
                    style = TextStyle(
                        fontSize = fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center),
                    color = Color(0xFF5FB49C)

                )

            }

            Spacer(modifier = Modifier.padding(35.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuseumAppTheme {
        com.example.museumapp.Greeting("Android")
    }
}