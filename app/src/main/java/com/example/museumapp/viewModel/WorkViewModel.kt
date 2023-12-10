package com.example.museumapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Author
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WorkViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
):ViewModel(){

    var work: Work = Work()
    var autor: Author = Author()

    init {
        museumAppState.setWork(Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg","Es una de las obras de mayor tamaño de Velázquez y en la que puso un mayor empeño para crear una composición a la vez compleja y creíble, que transmitiera la sensación de vida y realidad, y al mismo tiempo encerrara una densa red de significados. El pintor alcanzó su objetivo y el cuadro se convirtió en la única pintura a la que el tratadista Antonio Palomino dedicó un epígrafe en su historia de los pintores españoles (1724). Lo tituló En que se describe la más ilustre obra de don Diego Velázquez, y desde entonces no ha perdido su estatus de obra maestra. Gracias a Palomino sabemos que se pintó en 1656 en el Cuarto del Príncipe del Alcázar de Madrid, que es el escenario de la acción. El tratadista cordobés también identificó a la mayor parte de los personajes: son servidores palaciegos, que se disponen alrededor de la infanta Margarita, a la que atienden doña María Agustina Sarmiento y doña Isabel de Velasco, meninas de la reina. Además de ese grupo, vemos a Velázquez trabajar ante un gran lienzo, a los enanos Mari Bárbola y Nicolasito Pertusato, que azuza a un mastín, a la dama de honor doña Marcela de Ulloa, junto a un guardadamas, y, al fondo, tras la puerta, asoma José Nieto, aposentador. En el espejo se ven reflejados los rostros de Felipe IV y Mariana de Austria, padres de la infanta y testigos de la escena. Los personajes habitan un espacio modelado no sólo mediante las leyes de la perspectiva científica sino también de la perspectiva aérea, en cuya definición representa un papel importante la multiplicación de las fuentes de luz."))
        work = museumAppState.getWork()
        autor= Author(name = "Francisco De Goya", cover = "https://dbe.rah.es/sites/default/files/styles/wide/public/imagenes/biografias/28608_Goya-y-Lucientes%2C-Francisco-de_0.jpg")

    }
}