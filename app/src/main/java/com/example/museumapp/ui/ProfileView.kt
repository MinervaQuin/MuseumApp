package com.example.museumapp.ui

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import coil.compose.AsyncImage
import com.example.museumapp.ui.theme.MuseumAppTheme
import com.example.museumapp.viewModel.ProfileViewModel

@Composable
fun ProfileView(profileViewModel: ProfileViewModel = hiltViewModel()) {


    if(profileViewModel.museumAppState.getUser() == null){
        LaunchedEffect(profileViewModel) {
            try {

                profileViewModel.museumAppState.getUserById()
                    ?.let { profileViewModel.museumAppState.setUser(it)
                        profileViewModel.helloworld()}
//                if(profileViewModel.museumAppState.getUser() == null) profileViewModel.helloworld()
            } catch (e: Exception) {
                Log.e("Firestore", "Error en ProfileView", e)
            }
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Profile Image
        AsyncImage(
            model = profileViewModel.museumAppState.getUser()?.photo,
            contentDescription = "Profile Picture",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape)
                .border(width = 2.dp, color = Color.Black, CircleShape)
                .clickable { // TODO: clickar en la imagen
                },
        )
        Spacer(modifier = Modifier.height(16.dp))

        profileViewModel.museumAppState.getUser()?.name.let {
            if (it != null) {
                androidx.compose.material.Text(it)
            }
        }

        Text(text = "Has escaneado " + profileViewModel.museumAppState.getScannedObras() + " de " + profileViewModel.museumAppState.getTotalObras() + " obras")

        Spacer(modifier = Modifier.height(35.dp))

        // Buttons
        Button(
            onClick = {
                // TODO: cambiar correo
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            border = BorderStroke(2.dp, Color(0xFF77CF7C))
        ) {
            androidx.compose.material.Text("Cambiar correo")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                // TODO: cambiar correo
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            border = BorderStroke(2.dp, Color(0xFF77CF7C))
        ) {
            androidx.compose.material.Text("Cambiar contraseña")
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                // TODO: cambiar correo
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 30.dp, start = 30.dp)
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(Color.White),
            border = BorderStroke(2.dp, Color(0xFF77CF7C))
        ) {
            androidx.compose.material.Text("Mis compras")
        }

        Spacer(modifier = Modifier.height(85.dp))

        // Logout Button
        Button(
            onClick = {
                // TODO: cambiar correo
            },
            modifier = Modifier
                .height(40.dp)
                .width(180.dp)
                .padding(end = 10.dp)
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(Color.White),
            border = BorderStroke(2.dp, Color(0xFF841F0B))
        ) {
            androidx.compose.material.Text("Cerrar sesión")
        }
    }


}




@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    MuseumAppTheme {
        ProfileView()
    }
}