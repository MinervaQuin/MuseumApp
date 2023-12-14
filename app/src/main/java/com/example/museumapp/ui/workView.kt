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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Help
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Author
import com.example.museumapp.ui.theme.greenDark
import com.example.museumapp.viewModel.AuthorViewModel
import com.example.museumapp.viewModel.WorkViewModel

@Composable
fun workView (navController: NavController, viewModel: WorkViewModel){

    var work = viewModel.work
    viewModel.autor?.let { viewModel.museumAppState.setAutor(it) }
    viewModel.museumAppState.setWork(viewModel.work)
    var autor = viewModel.autor
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AuthorScreen(autor as Author,onDismiss = { showDialog = false })
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(5.dp)
    ){
        AsyncImage(
            model = work!!.cover,
            contentDescription = null,
            modifier = Modifier
                .width(300.dp)
                .align(alignment = Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(85.dp)
                .padding(10.dp)
        ){
            Row(
                modifier= Modifier
                    .clickable {
                        navController.navigate("AuthorWorksScreen")
                    }
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
                        text = work!!.name,
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(450),
                            color = Color(0xCC010101),
                        )
                    )
                    Text(
                        text = work!!.date_of_creation.toString(),
                        style = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xCC010101),
                        )
                    )
                }
            }
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "More info Icon",
                tint = greenDark,
                modifier = Modifier
                    .align(alignment = Alignment.CenterEnd)
                    .padding(10.dp)
                    .clickable(){
                        showDialog=true
                    }
            )
        }
        Text(
            text= work!!.description,
            style = TextStyle(
                textAlign = TextAlign.Justify,
                fontSize = 15.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            ),
            modifier = Modifier
                .padding(10.dp)
        )
    }
}

@Composable
fun AuthorScreen(autor: Author, onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .padding(16.dp)
                .shadow(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            shape = RoundedCornerShape(10.dp),
        ) {
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
                }
                Text(
                    text= autor!!.biography,
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                        fontSize = 15.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}