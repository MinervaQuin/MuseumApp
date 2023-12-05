package com.example.museumapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.museumapp.viewModel.coleccionesViewModel
import com.example.museumapp.model.resources.Collection

@Composable
fun coleccionesView (navController: NavController,viewModel: coleccionesViewModel){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Box(
                modifier = Modifier
                    .width(111.dp)
                    .height(2.dp)
                    .background(color = Color(0xFF5FB49C))
                    .align(alignment = Alignment.CenterVertically)
            )
            Text(
                text = "Colecciones",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Italic,
                ),
                modifier = Modifier
                    .padding(10.dp)
            )
            Box(
                modifier = Modifier
                    .width(111.dp)
                    .height(2.dp)
                    .background(color = Color(0xFF5FB49C))
                    .align(alignment = Alignment.CenterVertically)
            )
        }
        viewModel.colecciones?.forEach { superCollection ->
                Text(
                    text = superCollection?.name ?: "",
                    style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF000000),
                ),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            )
            LazyRow() {
                    items(1) {
                        superCollection?.collections?.forEach { collection ->
                            collection?.let { it1 -> colectionPreview(it1, navController) }
                        }
                    }
                }
        }

    }
}


@Composable
fun colectionPreview(coleccion: Collection, navController: NavController){
    Box(
        modifier = Modifier
            .padding(10.dp)
            .width(100.dp)
            .height(129.dp)
            .shadow(
                elevation = 4.dp,
                spotColor = Color(0x40000000),
                ambientColor = Color(0x40000000)
            )
            .border(width = 1.dp, color = Color(0xFF000000))
            .clickable {
            }){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = coleccion!!.cover,
                contentDescription = null,
                modifier = Modifier
                    .width(85.dp)
                    .height(85.2.dp)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Text(
                text = coleccion!!.name,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF010101),
                    )
            )

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewCategory() {
    val navController = rememberNavController()
    coleccionesView(navController, coleccionesViewModel())
}