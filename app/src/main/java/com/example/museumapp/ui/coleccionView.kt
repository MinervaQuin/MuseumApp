package com.example.museumapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.museumapp.model.resources.Work
import com.example.museumapp.viewModel.coleccionesViewModel
import com.example.museumapp.model.resources.Collection

@Composable
fun coleccionView (navController: NavController, viewModel: coleccionesViewModel, collection: Collection){
    var introduccion by remember { mutableStateOf(true) }

    val styleNoSelected = TextStyle(
        color = Color(0xFF5FB49C),
        fontWeight =FontWeight.Bold,
        fontSize = 15.sp)

    val styleSelected = TextStyle(
        color = Color.White,
        fontWeight =FontWeight.Bold,
        fontSize = 15.sp)

    Column(
        modifier= Modifier
            .fillMaxSize()
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(125.dp)
        ) {
            AsyncImage(
                model = collection!!.cover,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = collection!!.name,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                    shadow = Shadow(
                        offset = Offset(4f, 4f),
                        blurRadius = 8f
                    )
                ),
                modifier = Modifier
                    .width(354.dp)
                    .height(22.dp)
                    .padding(start = 5.dp)
                    .align(alignment = Alignment.BottomStart)
            )
        }
        Row(
            modifier= Modifier
                .fillMaxWidth()
                .height(35.dp)
                .background(color = Color(0x891E1E1E))
        ){
            ClickableText(
                text= AnnotatedString("INTRODUCCIÓN"),
                onClick = {
                    introduccion = true
                },
                style = if (introduccion ) styleSelected else styleNoSelected,
                modifier = Modifier
                    .padding(5.dp)
            )
            ClickableText(
                text= AnnotatedString("OBRAS"),
                onClick = {
                    introduccion = false
                },
                style = if (introduccion ) styleNoSelected else styleSelected,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
        if(introduccion){
            Text(
                    text= collection!!.description,
                    style = TextStyle(
                    fontSize = 15.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                    ),
                modifier = Modifier
                    .padding(10.dp)
                    .verticalScroll(rememberScrollState()),
            )
        }
        else{
            LazyColumn(){
                items(1){
                    for (i in 0 until collection.works.size){
                        collection.works[i]?.let { it1 -> worksPreview(it1,navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun worksPreview(work: Work,navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .padding(10.dp)
            .clickable {
            }
    )
    {
        AsyncImage(
            model = work!!.cover,
            contentDescription = null,
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                text = work!!.author,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF010101),
                )
            )
            Text(
                text = work!!.name,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xCC010101),
                )
            )
            Text(
                text = work!!.date_of_creation.toString(),
                style = TextStyle(
                    fontSize = 8.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xCC010101),
                )
            )
        }

    }
}