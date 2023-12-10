package com.example.museumapp.model.firebaseAuth

import android.util.Log
import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.resources.AuthorFb
import com.example.museumapp.model.resources.Book
import com.example.museumapp.model.resources.CollectionFb
import com.example.museumapp.model.resources.User
import com.example.museumapp.model.resources.Work
import com.example.museumapp.model.resources.WorkFb
import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date
import javax.inject.Inject


class FirestoreRepositoryImpl @Inject constructor(private val firebaseFirestore: FirebaseFirestore): FirestoreRepository {
    override val dataBase: FirebaseFirestore?
        get() = firebaseFirestore


//    override suspend fun getAllBooks(booksIds: List<String>): List<Book?> {
//        val bookArray: MutableList<Book?> = mutableListOf()
//        return try {
//            booksIds.forEach { bookId ->
//                val book = getBook(bookId)
//                if(book != null){
//                    bookArray.add(book)
//                }
//            }
//            bookArray
//        } catch (e: Exception) {
//            Log.d("FirestoreRepository", "getAllBooks failed with ", e)
//            emptyList()
//        }
//    }

    override suspend fun getAllArtists(): List<Book?> {
        val bookArray: MutableList<Book?> = mutableListOf()
        return try {
            val querySnapshot = FirebaseFirestore.getInstance()
                .collection("artists") // Reemplaza con el nombre de tu colección en Firestore
                .get()
                .await()

            for (document in querySnapshot.documents) {
                document.getString("name")?.let { Log.d("Firestore", it) }

            }
            Log.d("Firestore", bookArray.size.toString())
            bookArray
        } catch (e: Exception) {
            Log.d("FirestoreRepository", "getAllBooks failed with ", e)
            emptyList()
        }
    }

    override suspend fun getUserById(userId: String): User? {
        val firestore = FirebaseFirestore.getInstance()
        val userCollection = firestore.collection("users")

        try {
            val documentSnapshot: DocumentSnapshot = userCollection.document(userId).get().await()

            if (documentSnapshot.exists()) {
                val userData = documentSnapshot.data

                // Extraer datos del documento y crear un objeto User
                val name = userData?.get("name") as? String ?: ""
                val email = userData?.get("email") as? String ?: ""
                val photo = userData?.get("photo") as? String ?: ""

                // Aquí asumimos que "scanned_works" es un campo de tipo Array en Firestore
                val scannedWorks = (userData?.get("scanned_works") as? List<*>)?.map { it.toString() }?.toTypedArray()
                    ?: arrayOf()
                return User(name, email, photo, scannedWorks)
            }
        } catch (e: Exception) {
            // Manejar excepciones, por ejemplo, problemas de red
            e.printStackTrace()
        }
        return null
    }


    fun convertLocalDateToTimestamp(localDate: LocalDate): Timestamp {
        val zoneId = ZoneId.systemDefault()
        val epochMillis = localDate.atStartOfDay(zoneId).toInstant().toEpochMilli()
        return Timestamp(epochMillis / 1000, ((epochMillis % 1000) * 1_000_000).toInt())
    }

    override suspend fun uploadWorktoFirestore() {
        val newWork = WorkFb("Saturno Devorando a su hijo","Francisco de Goya","", convertLocalDateToTimestamp(LocalDate.now()), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg","Es una de las obras de mayor tamaño de Velázquez y en la que puso un mayor empeño para crear una composición a la vez compleja y creíble, que transmitiera la sensación de vida y realidad, y al mismo tiempo encerrara una densa red de significados. El pintor alcanzó su objetivo y el cuadro se convirtió en la única pintura a la que el tratadista Antonio Palomino dedicó un epígrafe en su historia de los pintores españoles (1724). Lo tituló En que se describe la más ilustre obra de don Diego Velázquez, y desde entonces no ha perdido su estatus de obra maestra. Gracias a Palomino sabemos que se pintó en 1656 en el Cuarto del Príncipe del Alcázar de Madrid, que es el escenario de la acción. El tratadista cordobés también identificó a la mayor parte de los personajes: son servidores palaciegos, que se disponen alrededor de la infanta Margarita, a la que atienden doña María Agustina Sarmiento y doña Isabel de Velasco, meninas de la reina. Además de ese grupo, vemos a Velázquez trabajar ante un gran lienzo, a los enanos Mari Bárbola y Nicolasito Pertusato, que azuza a un mastín, a la dama de honor doña Marcela de Ulloa, junto a un guardadamas, y, al fondo, tras la puerta, asoma José Nieto, aposentador. En el espejo se ven reflejados los rostros de Felipe IV y Mariana de Austria, padres de la infanta y testigos de la escena. Los personajes habitan un espacio modelado no sólo mediante las leyes de la perspectiva científica sino también de la perspectiva aérea, en cuya definición representa un papel importante la multiplicación de las fuentes de luz.")

        val newCollection = CollectionFb(
            name = "Pintura romantica",
            description = "La pintura romántica sucede a la pintura neoclásica de finales del siglo xviii, con unos nuevos gustos desarrollados por todas las facetas artísticas del Romanticismo como la literatura, la filosofía y la arquitectura. Está hermanada con los movimientos sociales y políticos, que ganaron cuerpo con la Revolución francesa.\n" +
                    "La pintura romántica apela al subjetivismo y la originalidad. Se inspira en escenas violentas como en La carga de los Mamelucos de Goya, tiene un gusto por el dramatismo, que utiliza para remover el sentimiento del público.\n" +
                    "En cuanto la expresión, utiliza con frecuencia fuertes contrastes de luz y sombra (claroscuro). El colorido es característico del romanticismo, pues prevalece sobre el dibujo, que asume un papel secundario. La pincelada se hizo visible, impetuosa. El empaste es en general grumoso y espeso, de manera que la pintura adquirió una naturaleza táctil que reforzaba su carácter de creación impulsiva y espontánea. A veces el acabado del cuadro tiene un aspecto de esbozo.\n" +
                    "En cierto sentido, al hablar del romanticismo se puede hablar de un neobarroco, por el movimiento, la tensión, el empuje, los contrastes y los colores de estos cuadros. Son, en general, pintores barrocos los que influyen a los pintores románticos: la huella de Caravaggio es evidente en Géricault, mientras que Rubens influye en Gros y en Delacroix. Ello no elimina que en casos concretos otros sean los pintores que influyeron: así los nazarenos pretenden acercarse a los primitivos italianos como Fra Angélico, Gérard a Leonardo y, finalmente, Prud'hon se ve influido por el manierista Correggio.",
            works = listOf<String>(),
            cover = "https://dbe.rah.es/sites/default/files/styles/wide/public/imagenes/biografias/28608_Goya-y-Lucientes%2C-Francisco-de_0.jpg"
        )

        val newAuthor = AuthorFb(
            id = 9,
            name = "Francisco de goya",
            biography = "t",
            cover = "https://dbe.rah.es/sites/default/files/styles/wide/public/imagenes/biografias/28608_Goya-y-Lucientes%2C-Francisco-de_0.jpg",
            Place_Birth_and_Dead = "su casa",
            type = "",
            works = listOf<String>()
        )
        try {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("works")
                .add(newWork)

                .addOnSuccessListener { documentReference ->
                    // En este punto, el libro se ha subido exitosamente a Firestore
                    // Puedes imprimir el ID del documento si es necesario
                    println("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    // Maneja los errores aquí
                    println("Error adding document: $e")
                }
        } catch (e: Exception) {
            Log.d("FirestoreRepository", "uploadBook failed with ", e)
        }

        try {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("artists")
                .add(newAuthor)

                .addOnSuccessListener { documentReference ->
                    // En este punto, el libro se ha subido exitosamente a Firestore
                    // Puedes imprimir el ID del documento si es necesario
                    println("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    // Maneja los errores aquí
                    println("Error adding document: $e")
                }
        } catch (e: Exception) {
            Log.d("FirestoreRepository", "uploadAuthor failed with ", e)
        }
        try {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("collections")
                .add(newCollection)

                .addOnSuccessListener { documentReference ->
                    // En este punto, el libro se ha subido exitosamente a Firestore
                    // Puedes imprimir el ID del documento si es necesario
                    println("DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    // Maneja los errores aquí
                    println("Error adding document: $e")
                }
        } catch (e: Exception) {
            Log.d("FirestoreRepository", "uploadBook failed with ", e)
        }

    }


//    override suspend fun searchAllBooks(allBooks: List<Book?>, searchString: String): List<Book?> {
//        val bookArray: MutableList<Book?> = mutableListOf()
//
//        return try {
//            val regexPattern: String = "$searchString.*$"
//            val pattern: Pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE)
//
//            allBooks.forEach { book ->
//                if (isBookMatching(book, pattern)) {
//                    Log.d("FirestoreRepository", "Coincidencia encontrada")
//                    bookArray.add(book)
//                } else {
//                    Log.d("FirestoreRepository", "No se encontró coincidencia")
//                }
//            }
//            bookArray
//
//
//            return bookArray
//        } catch (e: Exception) {
//            Log.d("FirestoreRepository", "getAllBooks failed with ", e)
//            emptyList()
//        }
//    }


}