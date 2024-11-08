package com.myprimer.seproductivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.myprimer.seproductivo.Modelo.TodoViewModel
import com.myprimer.seproductivo.ui.theme.SeProductivoTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el ViewModel
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            SeProductivoTheme {
                // Crear NavController
                val navController = rememberNavController()

                // Configura la navegación con NavHost

                NavHost(navController, startDestination = "home") {
                    composable("home") {


                        MenuPrincipal(navController = navController, todoViewModel = todoViewModel)
                    }

                }
            }
        }
    }
}
