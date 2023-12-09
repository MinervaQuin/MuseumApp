package com.example.museumapp.ui


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.museumapp.R
import com.example.museumapp.viewModel.loginViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.museumapp.ui.theme.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.museumapp.model.firebaseAuth.SignInState

@Composable
fun LoginView(loginViewModel : loginViewModel = viewModel(), navController: NavController,state: SignInState, onSignInClick: () -> Unit){
    //@TODO: En vez de 1 solo elemento como fondo, hacer que sean 3 con animación de movimiento
    val image = painterResource(R.drawable.login)
    val googleIconImageVector = ImageVector.vectorResource(id = R.drawable.vector_google)



    var userEmail by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    val context = LocalContext.current
    LaunchedEffect(key1 = state.signInError){
        state.signInError?.let { error ->
            Toast.makeText(
                context,
                error,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Colocamos la imagen de fondo
        Image(
            painter = image,
            contentDescription = null, // Descripción para accesibilidad
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop // Ajusta la imagen al tamaño del Box, recortando si es necesario
        )

        Row (
            modifier = Modifier
                .align(Alignment.Center)
                //.fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                putText(text = "Inicia", color = greenDark, fontSize = 58.sp)
                putText(text = " Sesión", color = greenDark, fontSize = 58.sp)


                Spacer(modifier = Modifier.height(8.dp))



                //TODO Hacer que al darle al enter cambie el focus en vez de poner un puto enter
                InputField(value = userEmail, onChange = {userEmail = it}, label = "Correo", icon = Icons.Outlined.Email)

                Spacer(modifier = Modifier.height(8.dp))

                InputField(value = password, onChange = {password = it}, label = "Contraseña", icon = Icons.Outlined.Lock, visualTransformation = PasswordVisualTransformation())

                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "¿Has Olvidado la contraseña?")
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onSignInClick,
                    modifier = Modifier
                        .height(50.dp), // Altura del botón
                    colors = ButtonDefaults.buttonColors(containerColor = green)

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .padding(horizontal = 16.dp) // Padding horizontal para el contenido
                    ) {
                        Icon(
                            imageVector = googleIconImageVector,
                            contentDescription = "Icono de Google",
                            modifier = Modifier.size(24.dp) // Tamaño del ícono
                        )
                        Spacer(Modifier.width(8.dp)) // Espacio entre el ícono y el texto
                        Text(
                            text = "Iniciar Sesión con Google",
                            fontSize = 16.sp
                        )
                    }
                }

            }
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                //.fillMaxWidth()
                .padding(16.dp),

        ){
            Button(
                onClick = {
                    //loginViewModel.updateCredentials(userEmail.text, password.text)
                    loginViewModel.signInWithEmail(userEmail.text,password.text){ //TODO A esto de iniciar sesión con el usuario y contraseña hay que echarle un ojo, muy poco MVVM
                        navController.navigate("homePage")
                    }
                          },
                colors = ButtonDefaults.buttonColors(green),
                shape = CircleShape,
                modifier = Modifier
                    .size(75.dp),

                ) {
                Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Avanzar")
            }
            Spacer(modifier = Modifier.height(150.dp))
        }

        Row (
            modifier = Modifier
                .align(Alignment.BottomEnd)
                //.fillMaxWidth()
                .padding(16.dp),
        ){
            Text(text = "No estás registrado? ", color= green)
            Text(
                color= green,
                text = "Registrate",
                fontWeight = FontWeight.Bold, // Esto pone el texto en negrita
                textDecoration = TextDecoration.Underline, // Esto subraya el texto
                modifier = Modifier.clickable {
                    navController.navigate("signUp") {
                        popUpTo("login") { inclusive = true }
                    }
                }
            )
            Spacer(modifier = Modifier.height(50.dp))
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun putText(
    text: String,
    fontSize: TextUnit,  // Parámetro adicional para el tamaño de la fuente
    modifier: Modifier = Modifier,
    color: Color = Color.Black,  // Parámetro de color con valor predeterminado
    fontWeight: FontWeight = FontWeight.Bold  // Parámetro de fontWeight con valor predeterminado
) {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            lineHeight = 50.sp,
            color = color,
            shadow = Shadow(
                    color = Color.White,
                    offset = offset,
                    blurRadius = 3f
        )
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    value: TextFieldValue,
    onChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onChange,
        label = { Text(text = label) },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        visualTransformation = visualTransformation,
        modifier = modifier,

    )


}




@Preview(showBackground = true)
@Composable
fun PreviewLoginView() {
    // Creas un NavController ficticio que no hará nada en la previsualización
    val navController = rememberNavController()
    //LoginView(navController = navController, onSignInClick = null, )
}