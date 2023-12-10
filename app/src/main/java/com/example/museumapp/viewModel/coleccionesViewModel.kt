package com.example.museumapp.viewModel
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.museumapp.model.MuseumAppState
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.SuperCollection
import com.example.museumapp.model.resources.Work
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class coleccionesViewModel @Inject constructor(
    val museumAppState: MuseumAppState,
): ViewModel()  {
    var colecciones by mutableStateOf(listOf<SuperCollection?>())
        private set

    init {
        colecciones= listOf(SuperCollection("Pintura Española", arrayOf(
            Collection("Pintura romantica", "ta mu bueno ",arrayOf(
            ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg")
        ),
        ),
            SuperCollection("Pintura Romana", arrayOf(
                Collection("Pintura romantica", "La pintura romántica sucede a la pintura neoclásica de finales del siglo xviii, con unos nuevos gustos desarrollados por todas las facetas artísticas del Romanticismo como la literatura, la filosofía y la arquitectura. Está hermanada con los movimientos sociales y políticos, que ganaron cuerpo con la Revolución francesa.\n" +
                        "La pintura romántica apela al subjetivismo y la originalidad. Se inspira en escenas violentas como en La carga de los Mamelucos de Goya, tiene un gusto por el dramatismo, que utiliza para remover el sentimiento del público.\n" +
                        "En cuanto la expresión, utiliza con frecuencia fuertes contrastes de luz y sombra (claroscuro). El colorido es característico del romanticismo, pues prevalece sobre el dibujo, que asume un papel secundario. La pincelada se hizo visible, impetuosa. El empaste es en general grumoso y espeso, de manera que la pintura adquirió una naturaleza táctil que reforzaba su carácter de creación impulsiva y espontánea. A veces el acabado del cuadro tiene un aspecto de esbozo.\n" +
                        "En cierto sentido, al hablar del romanticismo se puede hablar de un neobarroco, por el movimiento, la tensión, el empuje, los contrastes y los colores de estos cuadros. Son, en general, pintores barrocos los que influyen a los pintores románticos: la huella de Caravaggio es evidente en Géricault, mientras que Rubens influye en Gros y en Delacroix. Ello no elimina que en casos concretos otros sean los pintores que influyeron: así los nazarenos pretenden acercarse a los primitivos italianos como Fra Angélico, Gérard a Leonardo y, finalmente, Prud'hon se ve influido por el manierista Correggio.",
                    arrayOf(Work("Saturno Devorando a su hijo","Francisco de Goya",
                        LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),)
                    ,"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"),
                Collection("Pintura no romantica", "ta mu bueno ",arrayOf(

                ),"https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/La_Virgen_de_los_Reyes_Cat%C3%B3licos.jpg/1200px-La_Virgen_de_los_Reyes_Cat%C3%B3licos.jpg"),
                Collection("Pintura romantica", "La pintura romántica sucede a la pintura neoclásica de finales del siglo xviii, con unos nuevos gustos desarrollados por todas las facetas artísticas del Romanticismo como la literatura, la filosofía y la arquitectura. Está hermanada con los movimientos sociales y políticos, que ganaron cuerpo con la Revolución francesa.\n" +
                        "La pintura romántica apela al subjetivismo y la originalidad. Se inspira en escenas violentas como en La carga de los Mamelucos de Goya, tiene un gusto por el dramatismo, que utiliza para remover el sentimiento del público.\n" +
                        "En cuanto la expresión, utiliza con frecuencia fuertes contrastes de luz y sombra (claroscuro). El colorido es característico del romanticismo, pues prevalece sobre el dibujo, que asume un papel secundario. La pincelada se hizo visible, impetuosa. El empaste es en general grumoso y espeso, de manera que la pintura adquirió una naturaleza táctil que reforzaba su carácter de creación impulsiva y espontánea. A veces el acabado del cuadro tiene un aspecto de esbozo.\n" +
                        "En cierto sentido, al hablar del romanticismo se puede hablar de un neobarroco, por el movimiento, la tensión, el empuje, los contrastes y los colores de estos cuadros. Son, en general, pintores barrocos los que influyen a los pintores románticos: la huella de Caravaggio es evidente en Géricault, mientras que Rubens influye en Gros y en Delacroix. Ello no elimina que en casos concretos otros sean los pintores que influyeron: así los nazarenos pretenden acercarse a los primitivos italianos como Fra Angélico, Gérard a Leonardo y, finalmente, Prud'hon se ve influido por el manierista Correggio.",
                    arrayOf(Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),)
                    ,"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"),
                Collection("Pintura pintona", "ta mu bueno ",arrayOf(

                ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg")
            )))
    }

    fun setcolection(coleccion: Collection){
        museumAppState.setcoleccion(coleccion)
    }
}
