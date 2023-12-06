package com.example.museumapp.viewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.SuperCollection
import com.example.museumapp.model.resources.Work

class coleccionesViewModel: ViewModel()  {
    var colecciones by mutableStateOf(listOf<SuperCollection?>())
        private set

    init {
        colecciones= listOf(SuperCollection("Pintura Española", arrayOf(
            Collection("Pintura romantica", "ta mu bueno ",arrayOf(
            ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg")
        ),
            ),
            SuperCollection("Pintura Romana", arrayOf(
                Collection("Pintura romantica", "ta mu bueno ",arrayOf(

                ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"),
                Collection("Pintura no romantica", "ta mu bueno ",arrayOf(

                ),"https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/La_Virgen_de_los_Reyes_Cat%C3%B3licos.jpg/1200px-La_Virgen_de_los_Reyes_Cat%C3%B3licos.jpg"),
                Collection("Pintura mamona", "ta mu bueno ",arrayOf(

                ),"https://i.etsystatic.com/5312870/r/il/c71887/1940638463/il_570xN.1940638463_blhi.jpg"),
                Collection("Pintura pintona", "ta mu bueno ",arrayOf(

                ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg")
                )))
    }
}