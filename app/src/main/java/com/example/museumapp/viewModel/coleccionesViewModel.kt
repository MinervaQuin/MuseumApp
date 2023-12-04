package com.example.museumapp.viewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.SuperCollection

class coleccionesViewModel: ViewModel()  {
    var colecciones by mutableStateOf(listOf<SuperCollection?>())
        private set

    init {
        colecciones= listOf(SuperCollection("Pintura Española", arrayOf(
            Collection("Pintura romantica", "ta mu bueno ",arrayOf(
                Book(12, 15, "Stephen King", "Misery1", "hola hola", 3, "Tapa Dura", 35.0),
                Book(12, 15, "Stephen King", "Misery1", "hola hola", 3, "Tapa Dura", 35.0),
                Book(12, 15, "Stephen King", "Misery1", "hola hola", 3, "Tapa Dura", 35.0)
            ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg")
        )),
            SuperCollection("Pintura Romana", arrayOf(
                Collection("Pintura romantica", "ta mu bueno ",arrayOf(
                    Book(12, 15, "Stephen King", "Misery1", "hola hola", 3, "Tapa Dura", 35.0),
                    Book(12, 15, "Stephen King", "Misery1", "hola hola", 3, "Tapa Dura", 35.0),
                    Book(12, 15, "Stephen King", "Misery1", "hola hola", 3, "Tapa Dura", 35.0)
                ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"))))
    }
}