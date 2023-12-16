package com.example.museumapp

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.ConfirmationNumber
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Store
import androidx.compose.material.icons.filled.Tour
import androidx.compose.material.icons.filled.Wallpaper
import androidx.compose.material.icons.outlined.Collections
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Store
import androidx.compose.material.icons.outlined.Tour
import androidx.compose.material.icons.outlined.Wallpaper
import androidx.compose.material3.DismissibleDrawerSheet
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.museumapp.ui.theme.green
import kotlinx.coroutines.launch
import coil.compose.AsyncImage
import com.example.museumapp.ui.theme.black
import com.example.museumapp.ui.theme.blue
import com.example.museumapp.ui.theme.grey
import com.example.museumapp.ui.theme.grisClaro


data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var selectedItem by remember { mutableStateOf<NavigationItem?>(null) }
    var isOnCartScreen by remember { mutableStateOf(false) }

    val items = listOf(
        NavigationItem(
            title = "Perfil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = "profileView"
        ),
        NavigationItem(
            title = "Tienda",
            selectedIcon = Icons.Filled.Store,
            unselectedIcon = Icons.Outlined.Store,
            route = "shop",
        ),
        NavigationItem(
            title = "Obras",
            selectedIcon = Icons.Filled.Wallpaper,
            unselectedIcon = Icons.Outlined.Wallpaper,
            route = "",
        ),
        NavigationItem(
            title = "Artistas",
            selectedIcon = Icons.Filled.Palette,
            unselectedIcon = Icons.Outlined.Palette,
            route = ""
        ),
        NavigationItem(
            title = "Recorridos",
            selectedIcon = Icons.Filled.Tour,
            unselectedIcon = Icons.Outlined.Tour,
            route = ""
        ),
        NavigationItem(
            title = "Colecciones",
            selectedIcon = Icons.Filled.Collections,
            unselectedIcon = Icons.Outlined.Collections,
            route = "coleccionesView"
        ),
        NavigationItem(
            title = "Comprar entradas",
            selectedIcon = Icons.Filled.ConfirmationNumber,
            unselectedIcon = Icons.Outlined.ConfirmationNumber,
            route = "BuyTicket"
        ),
        NavigationItem(
            title = "Información del Museo",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info,
            route = "info",
        )
    )
    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            selectedItem = items.find { it.route == destination.route }
        }
        navController.addOnDestinationChangedListener(listener)

        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
    }

    DismissibleNavigationDrawer(drawerContent = {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            DismissibleDrawerSheet(
                drawerContainerColor = black,
                drawerContentColor = black,
            ) {
                Spacer(modifier = Modifier.height(55.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Agrega aquí la lógica para mostrar la foto de perfil del usuario
                    // Puedes usar AsyncImage, Image, u otros componentes según tus necesidades
                    AsyncImage(
                        model = "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(70.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Agrega el nombre del usuario debajo de la imagen
                    Text(text = "Nombre de Usuario", fontSize = 16.sp, color = grey)
                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp), color = green, thickness = 1.dp)
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items.forEachIndexed { index, item ->
                        item {
                            NavigationDrawerItem(
                                label = { Text(text = item.title, color = grey ) },
                                selected = item == selectedItem,
                                onClick = {
                                    if(item.title != "Perfil" && item.title != "Cesta" && item.title != "Ayuda"){
//                                        ShoppingCart.setSelectedCategory(item.title)
                                    }
                                    navController.navigate(item.route)
                                    scope.launch {
                                        drawerState.close()
                                    }
                                },
                                icon = {
                                    Icon(
                                        imageVector = if (item == selectedItem) {
                                            item.selectedIcon
                                        } else item.unselectedIcon,
                                        contentDescription = item.title,
                                        tint = green
                                    )
                                },
                                colors = NavigationDrawerItemDefaults.colors(selectedContainerColor = grisClaro, unselectedContainerColor = black ),
                                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                            )
                            if (index == 1) {
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 30.dp, end = 30.dp),
                                    color = green,
                                    thickness = 1.dp
                                )
                            }
                        }
                    }
                }
            }
        }
    },

        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        modifier = Modifier
            .width(250.dp)
            .border(5.dp, green)
    ) {

    }
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = black),
        title = {
            Text(
                text = "MUSEO HISTORIUM",
                fontSize = 20.sp,
                color = green,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 32.dp)
                    .wrapContentSize(Alignment.Center)
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    // Navega al destino del carrito
                    scope.launch {
                        if (drawerState.currentValue  == DrawerValue.Closed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = if(drawerState.currentValue  == DrawerValue.Closed) green else blue,
                    modifier = Modifier.size(32.dp)
                )

            }
        },
    )
}