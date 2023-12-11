package com.example.museumapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
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
import com.example.museumapp.viewModel.AuthorViewModel
import com.example.museumapp.viewModel.AuthorWorksViewModel
import com.example.museumapp.viewModel.BuyTicketViewModel
import com.example.museumapp.viewModel.WorkViewModel
import com.example.museumapp.viewModel.coleccionViewModel
import com.example.museumapp.viewModel.coleccionesViewModel
import com.example.museumapp.viewModel.qrViewModel
import com.google.zxing.integration.android.IntentIntegrator
import java.time.LocalDate

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val qrviewModel: qrViewModel by viewModels()
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
            NavHost(navController = navController, startDestination = "coleccionesView"){

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
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
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
                    val viewModel : coleccionesViewModel = hiltViewModel()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
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
                    val viewModel : coleccionViewModel = hiltViewModel()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                coleccionView(navController,viewModel)
                            }
                        }
                    )
                }
                composable("workView"){
                    val viewModel : WorkViewModel = hiltViewModel()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                workView(navController,viewModel)
                            }
                        }
                    )
                }


                composable("profileView"){
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
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
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
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

                composable("AuthorScreen"){
                    val viewModel : AuthorViewModel = hiltViewModel()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                AuthorScreen(viewModel)
                            }
                        }
                    )
                }

                composable("AuthorWorksScreen"){
                    val viewModel : AuthorWorksViewModel = hiltViewModel()
                    Scaffold(
                        bottomBar = { BottomBar(navController = navController,qrviewModel) },
                        topBar = { TopBar(navController = navController)},
                        content = { paddingValues ->
                            Column(
                                modifier = Modifier
                                    .padding(paddingValues)
                                    .fillMaxSize()
                            ) {
                                AuthorWorksScreen(viewModel)
                            }
                        }
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result != null){
            if(result.contents == null) {
                Toast.makeText(this,"Cancelado", Toast.LENGTH_SHORT).show()
            }
            else{
                lifecycleScope.launch{
                    qrviewModel.handleScanResult(result.contents)
                }
                qrviewModel.isFallo.observe(this, Observer { isFallo ->
                    if (isFallo) {
                        Toast.makeText(this,"No se ha encontrado la obra", Toast.LENGTH_SHORT).show()
                    }
                })
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data)
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