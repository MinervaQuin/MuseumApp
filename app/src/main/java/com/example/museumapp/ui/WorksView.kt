package com.example.museumapp.ui



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.AuthorFb
import com.example.museumapp.model.resources.Work
import com.example.museumapp.ui.theme.green
import com.example.museumapp.viewModel.WorksViewModel


@Composable
fun WorksView(
    modifier: Modifier = Modifier.padding(10.dp),
    navController: NavHostController,
) {
    val worksViewModel: WorksViewModel = hiltViewModel()
    val booksLoaded by worksViewModel.booksLoaded.observeAsState(initial = false)

    if (!booksLoaded) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(80.dp)
                    .padding(16.dp),
                color = green


            )
        }

    } else {
        var books by remember { mutableStateOf<List<Work?>>(emptyList()) }

        LaunchedEffect(books) {
            books = worksViewModel.getAllWorks()
        }

        Text(
            text="Obras",
            modifier = Modifier. padding(10.dp),

            style = TextStyle(
                color = green, fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            ),


            )
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 180.dp),
        ) {
            items(items = books) {
                BookItem2(it, modifier = Modifier, navController, worksViewModel)
            }
        }
    }



}

@Composable
fun BookItem2(
    book: Work?,
    modifier: Modifier,
    navController: NavHostController,
    worksViewModel: WorksViewModel
) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp)
            .clickable {
                if (book != null) {
                    worksViewModel.museumAppState.setWork(book)
                navController.navigate("workView")
                }

            } //BookDetail(
        , verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        if (book != null) {
            LoadBookCover(imageUrl = book.cover)
        }
        BookInfo2(book)


    }
}

@Composable
fun LoadBookCover2(imageUrl: String) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(250.dp),
        contentAlignment = Alignment.CenterStart
    ) {

        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )
    }
}

@Composable
fun BookInfo2(book: Work?) {

    Row(
        modifier = Modifier
            .width(180.dp)
            .padding(start = 5.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(0.7f),
            verticalArrangement = Arrangement.Center,
        ) {
            book?.let { Text(text = it.name) }
        }

    }
}