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
                    arrayOf(
                        Work("Saturno Devorando a su hijo","Francisco de Goya",
                        "",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg","Es una de las obras de mayor tamaño de Velázquez y en la que puso un mayor empeño para crear una composición a la vez compleja y creíble, que transmitiera la sensación de vida y realidad, y al mismo tiempo encerrara una densa red de significados. El pintor alcanzó su objetivo y el cuadro se convirtió en la única pintura a la que el tratadista Antonio Palomino dedicó un epígrafe en su historia de los pintores españoles (1724). Lo tituló En que se describe la más ilustre obra de don Diego Velázquez, y desde entonces no ha perdido su estatus de obra maestra. Gracias a Palomino sabemos que se pintó en 1656 en el Cuarto del Príncipe del Alcázar de Madrid, que es el escenario de la acción. El tratadista cordobés también identificó a la mayor parte de los personajes: son servidores palaciegos, que se disponen alrededor de la infanta Margarita, a la que atienden doña María Agustina Sarmiento y doña Isabel de Velasco, meninas de la reina. Además de ese grupo, vemos a Velázquez trabajar ante un gran lienzo, a los enanos Mari Bárbola y Nicolasito Pertusato, que azuza a un mastín, a la dama de honor doña Marcela de Ulloa, junto a un guardadamas, y, al fondo, tras la puerta, asoma José Nieto, aposentador. En el espejo se ven reflejados los rostros de Felipe IV y Mariana de Austria, padres de la infanta y testigos de la escena. Los personajes habitan un espacio modelado no sólo mediante las leyes de la perspectiva científica sino también de la perspectiva aérea, en cuya definición representa un papel importante la multiplicación de las fuentes de luz."),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","", LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),)
                    ,"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"),
                Collection("Pintura no romantica", "ta mu bueno ",arrayOf(

                ),"https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/La_Virgen_de_los_Reyes_Cat%C3%B3licos.jpg/1200px-La_Virgen_de_los_Reyes_Cat%C3%B3licos.jpg"),
                Collection("Pintura romantica", "La pintura romántica sucede a la pintura neoclásica de finales del siglo xviii, con unos nuevos gustos desarrollados por todas las facetas artísticas del Romanticismo como la literatura, la filosofía y la arquitectura. Está hermanada con los movimientos sociales y políticos, que ganaron cuerpo con la Revolución francesa.\n" +
                        "La pintura romántica apela al subjetivismo y la originalidad. Se inspira en escenas violentas como en La carga de los Mamelucos de Goya, tiene un gusto por el dramatismo, que utiliza para remover el sentimiento del público.\n" +
                        "En cuanto la expresión, utiliza con frecuencia fuertes contrastes de luz y sombra (claroscuro). El colorido es característico del romanticismo, pues prevalece sobre el dibujo, que asume un papel secundario. La pincelada se hizo visible, impetuosa. El empaste es en general grumoso y espeso, de manera que la pintura adquirió una naturaleza táctil que reforzaba su carácter de creación impulsiva y espontánea. A veces el acabado del cuadro tiene un aspecto de esbozo.\n" +
                        "En cierto sentido, al hablar del romanticismo se puede hablar de un neobarroco, por el movimiento, la tensión, el empuje, los contrastes y los colores de estos cuadros. Son, en general, pintores barrocos los que influyen a los pintores románticos: la huella de Caravaggio es evidente en Géricault, mientras que Rubens influye en Gros y en Delacroix. Ello no elimina que en casos concretos otros sean los pintores que influyeron: así los nazarenos pretenden acercarse a los primitivos italianos como Fra Angélico, Gérard a Leonardo y, finalmente, Prud'hon se ve influido por el manierista Correggio.",
                    arrayOf(
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                        Work("Saturno Devorando a su hijo","Francisco de Goya","",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),)
                    ,"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"),
                Collection("Pintura pintona", "ta mu bueno ",arrayOf(

                ),"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg")
            )))
    }

    fun setcolection(coleccion: Collection){
        museumAppState.setcoleccion(coleccion)
    }
}
