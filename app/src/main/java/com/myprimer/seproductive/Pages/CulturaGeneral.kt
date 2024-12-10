package com.myprimer.seproductive.Pages

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import com.myprimer.seproductive.Modelo.culturageneralviewmodel










import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay





@Composable
fun CulturaGeneral(navController: NavController) {
    val viewModel: culturageneralviewmodel = viewModel()
    val currentQuestion = viewModel.currentQuestion.collectAsState().value
    val score = viewModel.score.collectAsState().value
    val isGameOver = viewModel.isGameOver.collectAsState().value

    // Estado para el temporizador
    var timerValue by remember { mutableStateOf(20) } // 20 segundos de tiempo inicial
    val timerRunning = remember { mutableStateOf(true) } // Para controlar si el temporizador sigue activo

    // Iniciar un temporizador de 20 segundos para cada pregunta
    LaunchedEffect(currentQuestion) {
        // Reiniciar el temporizador al cambiar la pregunta
        timerValue = 20
        timerRunning.value = true

        // Contador que decrementa cada segundo
        while (timerValue > 0 && timerRunning.value) {
            delay(1000)  // Espera 1 segundo
            timerValue -= 1
        }
        // Cuando el temporizador llegue a 0, consideramos que el tiempo se agotó
        if (timerValue == 0) {
            viewModel.submitAnswer("")  // Podrías manejar esta lógica de otra forma si es necesario
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF374965), Color(0xFF0F1A2B)))) // Gradiente de fondo
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState()), // Desplazamiento para mayor contenido
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Alineación superior
        ) {

            Spacer(modifier = Modifier.height(100.dp))


            if (!isGameOver) {

                Text(
                    text = "Puntuación: $score",
                    style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 24.dp)
                )


                Text(
                    text = "Tiempo restante: $timerValue s",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 24.dp)
                )


                Text(
                    text = currentQuestion?.question ?: "Cargando pregunta...",
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .fillMaxWidth()
                        .animateContentSize() // Animación de tamaño al cambiar el contenido
                        .shadow(10.dp, shape = RoundedCornerShape(16.dp), clip = true) // Sombra de la pregunta
                        .background(Brush.horizontalGradient(listOf(Color(0xFF4CAF50), Color(0xFF00796B)))) // Fondo gradiente para la pregunta
                        .padding(16.dp)
                )

                // Mostrar las opciones de respuesta con botones mejorados
                currentQuestion?.options?.forEachIndexed { index, option ->
                    Button(
                        onClick = {
                            viewModel.submitAnswer(option)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp)
                            .height(56.dp) // Mayor tamaño para los botones
                            .clip(RoundedCornerShape(16.dp)) // Bordes redondeados en botones
                            .shadow(8.dp, shape = RoundedCornerShape(16.dp)), // Sombra para un efecto flotante
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                            color = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
            } else {
                // Mostrar el mensaje de fin de juego con un toque tridimensional
                Text(
                    text = "¡Juego terminado! Puntuación final: $score",
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                        .shadow(10.dp, shape = RoundedCornerShape(20.dp)) // Sombra alrededor del mensaje
                        .background(Brush.linearGradient(listOf(Color(0xFF9E9E9E), Color(0xFF424242)))) // Fondo gradiente
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Botón para volver al menú principal con efectos 3D
                Button(
                    onClick = {
                        navController.popBackStack()  // Regresar al menú principal
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .clip(RoundedCornerShape(20.dp)) // Bordes redondeados en el botón
                        .shadow(8.dp, shape = RoundedCornerShape(20.dp)), // Sombra para el botón
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Volver al menú principal",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Botón para volver a jugar con efecto 3D
                Button(
                    onClick = {
                        viewModel.restartGame()  // Reiniciar el juego
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .clip(RoundedCornerShape(20.dp)) // Bordes redondeados en el botón
                        .shadow(8.dp, shape = RoundedCornerShape(20.dp)), // Sombra para el botón
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Volver a jugar",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            // Botón para volver a la pantalla anterior, colocado debajo de las preguntas
            if (!isGameOver) {
                Spacer(modifier = Modifier.weight(1f)) // Espacio flexible para separar el botón de las opciones

                Button(
                    onClick = {
                        navController.popBackStack()  // Regresar a la pantalla anterior
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clip(RoundedCornerShape(20.dp)) // Bordes redondeados en el botón
                        .shadow(8.dp, shape = RoundedCornerShape(20.dp)), // Sombra para el botón
                    shape = MaterialTheme.shapes.large
                ) {
                    Text(
                        text = "Volver",
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}












