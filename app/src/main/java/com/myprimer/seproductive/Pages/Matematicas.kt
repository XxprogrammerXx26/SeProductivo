package com.myprimer.seproductive.Pages


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.delay



//
//@Composable
//fun Matematicas() {
//    var score by remember { mutableStateOf(0) }
//    var currentQuestion by remember { mutableStateOf(generateQuestion()) }
//    var userAnswer by remember { mutableStateOf("") }
//    var gameOver by remember { mutableStateOf(false) }
//    var timer by remember { mutableStateOf(5) }  // Tiempo restante para la respuesta
//    var timerRunning by remember { mutableStateOf(true) }  // Indicador de si el temporizador está en marcha
//
//    // Efecto que decrementa el temporizador
//    LaunchedEffect(currentQuestion) {
//        // Reiniciar el temporizador cada vez que cambie la pregunta
//        timer = 5
//        timerRunning = true
//    }
//
//    LaunchedEffect(timerRunning) {
//        if (timerRunning) {
//            while (timer > 0) {
//                delay(1000) // Esperar 1 segundo
//                timer -= 1
//            }
//            if (timer == 0 && !gameOver) {
//                // Si el tiempo se acaba y no ha terminado el juego, se pasa a la siguiente pregunta
//                currentQuestion = generateQuestion()
//                timer = 5 // Reiniciar el temporizador a 30 segundos
//            }
//        }
//    }
//
//    if (gameOver) {
//        // Pantalla de fin de juego
//        GameOverScreen(score, onRetry = {
//            score = 0
//            currentQuestion = generateQuestion()
//            gameOver = false
//            timer = 5 // Reiniciar el temporizador
//            timerRunning = true // Reiniciar el temporizador
//        })
//    } else {
//        // Pantalla del juego
//        GameScreen(score, currentQuestion, timer, onAnswer = { answer ->
//            if (answer == currentQuestion.third) {
//                score += 1
//            }
//            if (score == 5) {
//                gameOver = true
//            } else {
//                currentQuestion = generateQuestion()
//                timer = 5 // Reiniciar el temporizador
//            }
//            timerRunning = false // Detener el temporizador después de responder
//        })
//    }
//}
//
//@Composable
//fun GameScreen(score: Int, currentQuestion: Triple<String, List<String>, String>, timer: Int, onAnswer: (String) -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()  // Ocupa toda la pantalla
//            .padding(16.dp)
//            .background(Brush.verticalGradient(listOf(Color(0xFF3F51B5), Color(0xFF61616A)))), // Fondo con degradado
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            "Puntaje: $score",
//            fontSize = 30.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.White,
//            modifier = Modifier.padding(8.dp)
//        )
//        Spacer(modifier = Modifier.height(24.dp))
//
//        Text(
//            "Pregunta: ${currentQuestion.first}",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color(0xFFF0F0F0),
//            modifier = Modifier.padding(8.dp)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Text(
//            "Tiempo restante: $timer segundos",
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Medium,
//            color = Color(0xFFDD2C00),
//            modifier = Modifier.padding(8.dp)
//        )
//        Spacer(modifier = Modifier.height(32.dp))
//
//        // Opciones de respuesta
//        currentQuestion.second.forEach { option ->
//            AnswerButton(option = option, onClick = { onAnswer(option) })
//        }
//    }
//}
//
//@Composable
//fun AnswerButton(option: String, onClick: () -> Unit) {
//    Button(
//        onClick = onClick,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp)
//            .height(56.dp)
//            .clip(RoundedCornerShape(16.dp)),
//        colors = ButtonDefaults.buttonColors(Color(0xFF3A4CCB)),
//        contentPadding = PaddingValues(16.dp)
//    ) {
//        Text(
//            option,
//            fontSize = 20.sp,
//            fontWeight = FontWeight.Bold,
//            color = Color.White
//        )
//    }
//}
//
//@Composable
//fun GameOverScreen(score: Int, onRetry: () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()  // Ocupa toda la pantalla
//            .background(Brush.verticalGradient(listOf(Color(0xFF3F51B5), Color(0xFF283593)))), // Fondo con degradado
//        contentAlignment = Alignment.Center
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
//            Text(
//                "Juego Terminado",
//                fontSize = 36.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//
//            Text(
//                "Tu Puntaje: $score",
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Medium,
//                color = Color(0xFFBFBFBF)
//            )
//            Spacer(modifier = Modifier.height(32.dp))
//
//            Button(
//                onClick = onRetry,
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(50.dp)
//                    .clip(RoundedCornerShape(8.dp)),
//                colors = ButtonDefaults.buttonColors(Color(0xFF134568))
//            ) {
//                Text(
//                    "Reintentar",
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color.White
//                )
//            }
//        }
//    }
//}
//
//// Función para generar una pregunta matemática aleatoria
//fun generateQuestion(): Triple<String, List<String>, String> {
//    val num1 = (1..10).random()
//    val num2 = (1..10).random()
//    val operation = listOf("+", "-", "*").random()
//    val correctAnswer: Int
//
//    val questionText = when (operation) {
//        "+" -> {
//            correctAnswer = num1 + num2
//            "$num1 + $num2"
//        }
//        "-" -> {
//            correctAnswer = num1 - num2
//            "$num1 - $num2"
//        }
//        "*" -> {
//            correctAnswer = num1 * num2
//            "$num1 * $num2"
//        }
//        else -> throw IllegalStateException("Operación no válida")
//    }
//
//    val options = mutableListOf(correctAnswer)
//    while (options.size < 4) {
//        val randomOption = (1..20).random()
//        if (!options.contains(randomOption)) {
//            options.add(randomOption)
//        }
//    }
//    options.shuffle()
//
//    // Convertimos correctAnswer a String para que coincida con el tipo del tercer valor del Triple
//    return Triple(questionText, options.map { it.toString() }, correctAnswer.toString())
//}















































@Composable
fun Matematicas() {
    var score by remember { mutableStateOf(0) }
    var currentQuestion by remember { mutableStateOf(generateQuestion()) }
    var gameOver by remember { mutableStateOf(false) }
    var timer by remember { mutableStateOf(5) }  // Tiempo restante para la respuesta
    var timerRunning by remember { mutableStateOf(true) }  // Indicador de si el temporizador está en marcha

    // Reiniciar el temporizador cada vez que cambie la pregunta
    LaunchedEffect(currentQuestion) {
        // Reiniciar el temporizador y el estado de ejecución
        timer = 5
        timerRunning = true
    }

    // Efecto para disminuir el temporizador
    LaunchedEffect(timerRunning) {
        if (timerRunning) {
            while (timer > 0 && !gameOver) {
                delay(1000)  // Esperar 1 segundo
                timer -= 1
            }

            if (timer == 0 && !gameOver) {
                // Si el tiempo se acaba y no ha terminado el juego, pasar a la siguiente pregunta
                currentQuestion = generateQuestion()
                timer = 5  // Reiniciar el temporizador a 5 segundos
                timerRunning = true  // Reiniciar el temporizador
            }
        }
    }

    if (gameOver) {
        // Pantalla de fin de juego
        GameOverScreen(score, onRetry = {
            score = 0
            currentQuestion = generateQuestion()
            gameOver = false
            timer = 5  // Reiniciar el temporizador
            timerRunning = true  // Reiniciar el temporizador
        })
    } else {
        // Pantalla del juego
        GameScreen(score, currentQuestion, timer, onAnswer = { answer ->
            if (answer == currentQuestion.third) {
                score += 1
            }
            if (score == 5) {
                gameOver = true
            } else {
                currentQuestion = generateQuestion()
                timer = 5  // Reiniciar el temporizador
                timerRunning = true  // Reiniciar el temporizador
            }
        })
    }
}

@Composable
fun GameScreen(score: Int, currentQuestion: Triple<String, List<String>, String>, timer: Int, onAnswer: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()  // Ocupa toda la pantalla
            .padding(16.dp)
            .background(Brush.verticalGradient(listOf(Color(0xFF3F51B5), Color(0xFF61616A)))), // Fondo con degradado
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Puntaje: $score",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            "Pregunta: ${currentQuestion.first}",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFF0F0F0),
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            "Tiempo restante: $timer segundos",
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFFDD2C00),
            modifier = Modifier.padding(8.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Opciones de respuesta
        currentQuestion.second.forEach { option ->
            AnswerButton(option = option, onClick = { onAnswer(option) })
        }
    }
}

@Composable
fun AnswerButton(option: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = ButtonDefaults.buttonColors(Color(0xFF3A4CCB)),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            option,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun GameOverScreen(score: Int, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()  // Ocupa toda la pantalla
            .background(Brush.verticalGradient(listOf(Color(0xFF3F51B5), Color(0xFF283593)))), // Fondo con degradado
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Juego Terminado",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Tu Puntaje: $score",
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFFBFBFBF)
            )
            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onRetry,
                modifier = Modifier
                    .width(200.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(8.dp)),
                colors = ButtonDefaults.buttonColors(Color(0xFF134568))
            ) {
                Text(
                    "Reintentar",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }
}

// Función para generar una pregunta matemática aleatoria
fun generateQuestion(): Triple<String, List<String>, String> {
    val num1 = (1..10).random()
    val num2 = (1..10).random()
    val operation = listOf("+", "-", "*").random()
    val correctAnswer: Int

    val questionText = when (operation) {
        "+" -> {
            correctAnswer = num1 + num2
            "$num1 + $num2"
        }
        "-" -> {
            correctAnswer = num1 - num2
            "$num1 - $num2"
        }
        "*" -> {
            correctAnswer = num1 * num2
            "$num1 * $num2"
        }
        else -> throw IllegalStateException("Operación no válida")
    }

    val options = mutableListOf(correctAnswer)
    while (options.size < 4) {
        val randomOption = (1..20).random()
        if (!options.contains(randomOption)) {
            options.add(randomOption)
        }
    }
    options.shuffle()

    // Convertimos correctAnswer a String para que coincida con el tipo del tercer valor del Triple
    return Triple(questionText, options.map { it.toString() }, correctAnswer.toString())
}








