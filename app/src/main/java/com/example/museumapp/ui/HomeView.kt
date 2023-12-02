package com.example.museumapp.ui

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.homeViewModel

@Composable
fun HomeView(name: String, navController: NavController, modifier: Modifier = Modifier) {
    val homeViewModel : homeViewModel = hiltViewModel()


    LaunchedEffect(homeViewModel) {
        try {
            homeViewModel.getAllArtists()
        } catch (e: Exception) {
            Log.e("Firestore", "Error en BookScreen", e)
        }
    }
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuseumAppTheme {
        com.example.museumapp.Greeting("Android")
    }
}