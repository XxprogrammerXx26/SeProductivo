package com.myprimer.seproductivo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation.NavController
import com.myprimer.seproductivo.Modelo.NavItem
import com.myprimer.seproductivo.Modelo.TodoViewModel
import com.myprimer.seproductivo.Pages.Home
import com.myprimer.seproductivo.Pages.Intellectual
import com.myprimer.seproductivo.Pages.Productivo
import com.myprimer.seproductivo.Pages.Profile


@Composable
fun MenuPrincipal(
    navController: NavController,
    modifier: Modifier = Modifier,
    todoViewModel: TodoViewModel
) {
    val navItemList = listOf(

        NavItem("Home", Icons.Default.Home),
        NavItem("Productivo", Icons.Default.CheckCircle),
        NavItem("Intellectual", Icons.Default.Person),
        NavItem("Profile", Icons.Default.AccountCircle),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = { Icon(imageVector = navItem.icon, contentDescription = "icon") },
                        label = { Text(text = navItem.label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        ContentScreen(
            navController = navController,
            modifier = Modifier.padding(innerPadding),
            selectedIndex = selectedIndex,
            todoViewModel = todoViewModel
        )
    }
}

@Composable
fun ContentScreen(
    navController: NavController,   //Navcontroller
    modifier: Modifier = Modifier,
    selectedIndex: Int,
    todoViewModel: TodoViewModel
) {
    when (selectedIndex) {

        0 -> Home()
        1 -> Productivo(modifier = modifier, viewModel = todoViewModel)
        2 -> Intellectual(navController = navController, modifier = modifier, viewModel = todoViewModel)
        3 -> Profile(modifier = modifier)

    }
}




