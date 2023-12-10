package com.example.museumapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.model.firebaseAuth.GoogleAuthUiClient
import com.example.museumapp.model.resources.Collection
import com.example.museumapp.model.resources.Work
import com.example.museumapp.ui.HomeView
import com.example.museumapp.ui.LoginView
import com.example.museumapp.ui.signUpView
//import com.example.museumapp.ui.LoginView
//import com.example.museumapp.ui.signUpView
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.loginViewModel
import com.google.android.gms.auth.api.identity.Identity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.example.museumapp.ui.*
import com.example.museumapp.viewModel.BuyTicketViewModel
import com.example.museumapp.viewModel.coleccionesViewModel
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    public val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MuseumAppTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
//    }




    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "firstScreens"){

                navigation(
                    startDestination = "login",
                    route = "firstScreens"
                ){
                    composable("login") {

                        val viewModel = viewModel<loginViewModel>()
                        val state by viewModel.state.collectAsStateWithLifecycle()

                        LaunchedEffect(key1 = Unit){
                            if(googleAuthUiClient.getSignedInUser() != null){
                                navController.navigate("secondScreens")
                            }
                        }

                        val launcher = rememberLauncherForActivityResult(

                            contract = ActivityResultContracts.StartIntentSenderForResult(),
                            onResult = {result ->
                                Log.d("launcher", "result = ${result.resultCode}")
                                if(result.resultCode == RESULT_OK) {
                                    lifecycleScope.launch {
                                        val signInResult = googleAuthUiClient.signInWithIntent(
                                            intent = result.data ?: return@launch
                                        )
                                        viewModel.onSignInResult(signInResult)
                                    }
                                }
                            }
                        )

                        LaunchedEffect(key1 = state.isSignInSuccessful) {
                            if(state.isSignInSuccessful) {
                                Toast.makeText(
                                    applicationContext,
                                    "Sesión Iniciada",
                                    Toast.LENGTH_LONG
                                ).show()

                                navController.navigate("secondScreens")
                                viewModel.resetState()
                            }
                        }

                        LoginView(
                            navController = navController,
                            state = state,
                            onSignInClick = {
                                lifecycleScope.launch {
                                    val signInIntentSender = googleAuthUiClient.signIn()
                                    Log.d("signInIntentSender", " ${googleAuthUiClient.signIn()}")
                                    launcher.launch(
                                        IntentSenderRequest.Builder(
                                            signInIntentSender ?: return@launch
                                        ).build()
                                    )
                                }
                            }

                        )
                    }

                    composable("signUp") { signUpView(navController = navController)}
                }

                composable("homePage") {
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                HomeView("Minerva", navController = navController)
                            }
                        }
                    )
                }

                composable("coleccionesView"){
                    val viewModel = viewModel<coleccionesViewModel>()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                coleccionesView(navController,viewModel)
                            }
                        }
                    )
                }
                composable("coleccionView"){
                    val viewModel = viewModel<coleccionesViewModel>()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                coleccionView(navController,viewModel, Collection("Pintura romantica", "La pintura romántica sucede a la pintura neoclásica de finales del siglo xviii, con unos nuevos gustos desarrollados por todas las facetas artísticas del Romanticismo como la literatura, la filosofía y la arquitectura. Está hermanada con los movimientos sociales y políticos, que ganaron cuerpo con la Revolución francesa.\n" +
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
                                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),
                                        Work("Saturno Devorando a su hijo","Francisco de Goya",LocalDate.now(), "https://s1.ppllstatics.com/laverdad/www/multimedia/202211/20/media/cortadas/174982724--1248x1888.jpg"),)
                                    ,"https://historia.nationalgeographic.com.es/medio/2019/12/12/caminante-mar-de-nubes-friedrich-cc_64c7fed4_1280x720.jpg"),

                                )
                            }
                        }
                    )
                }

                composable("profileView"){
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                ProfileView()
                            }
                        }
                    )
                }

                composable("BuyTicket"){
                    val viewModel : BuyTicketViewModel = hiltViewModel()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                TicketScreen(viewModel)
                            }
                        }
                    )
                }
            }
        }
    }







}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MuseumAppTheme {
        Greeting("Android")
    }
}