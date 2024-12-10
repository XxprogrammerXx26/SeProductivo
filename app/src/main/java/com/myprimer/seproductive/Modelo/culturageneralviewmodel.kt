package com.myprimer.seproductive.Modelo


import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow



class culturageneralviewmodel : ViewModel() {

    private val _currentQuestion = MutableStateFlow<Question?>(null)
    val currentQuestion: StateFlow<Question?> = _currentQuestion

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _isGameOver = MutableStateFlow(false)
    val isGameOver: StateFlow<Boolean> = _isGameOver

    private val questions = listOf(
        Question("¿Cuál es la capital de Francia?", listOf("Madrid", "París", "Roma", "Londres"), "París", "Cultura General"),
        Question("¿Cuál es el río más largo del mundo?", listOf("Amazonas", "Nilo", "Yangtsé", "Misisipi"), "Amazonas", "Cultura General"),
        Question("¿En qué año llegó Cristóbal Colón a América?", listOf("1492", "1500", "1485", "1510"), "1492", "Cultura General"),
        Question("¿Quién pintó la Mona Lisa?", listOf("Vincent van Gogh", "Pablo Picasso", "Leonardo da Vinci", "Claude Monet"), "Leonardo da Vinci", "Cultura General"),
        Question("¿Cuál es la capital de Italia?", listOf("Roma", "Milán", "Venecia", "Florencia"), "Roma", "Cultura General"),
        Question("¿En qué continente está Egipto?", listOf("Asia", "África", "Europa", "Oceanía"), "África", "Cultura General"),
        Question("¿Cuántos planetas tiene el sistema solar?", listOf("7", "8", "9", "10"), "8", "Cultura General"),
        Question("¿Quién fue el primer presidente de los Estados Unidos?", listOf("George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams"), "George Washington", "Cultura General"),
        Question("¿En qué año cayó el Muro de Berlín?", listOf("1989", "1990", "1985", "1992"), "1989", "Cultura General") ,
        Question("¿Quién escribió 'Don Quijote de la Mancha'?", listOf("Miguel de Cervantes", "Gabriel García Márquez", "Pablo Neruda", "Federico García Lorca"), "Miguel de Cervantes", "Literatura"),
    Question("¿Cuál es el país más grande del mundo?", listOf("Estados Unidos", "Canadá", "Rusia", "China"), "Rusia", "Geografía"),
    Question("¿Cuál es la moneda oficial de Japón?", listOf("Yen", "Won", "Baht", "Ringgit"), "Yen", "Economía"),
    Question("¿Cuál es el océano más grande?", listOf("Atlántico", "Índico", "Ártico", "Pacífico"), "Pacífico", "Geografía"),
    Question("¿En qué año se firmó la independencia de los Estados Unidos?", listOf("1776", "1789", "1800", "1812"), "1776", "Historia"),
    Question("¿Quién descubrió América?", listOf("Cristóbal Colón", "Fernando de Magallanes", "Marco Polo", "Hernán Cortés"), "Cristóbal Colón", "Historia"),
    Question("¿Qué planeta es conocido como el 'planeta rojo'?", listOf("Marte", "Venus", "Júpiter", "Saturno"), "Marte", "Ciencia"),
    Question("¿Cómo se llama la capital de Australia?", listOf("Sídney", "Melbourne", "Canberra", "Adelaida"), "Canberra", "Geografía"),
    Question("¿Quién fue el último emperador romano?", listOf("Rómulo Augústulo", "Julio César", "Nerón", "Augusto"), "Rómulo Augústulo", "Historia"),
    Question("¿Qué instrumento musical tiene teclas blancas y negras?", listOf("Guitarra", "Piano", "Violín", "Flauta"), "Piano", "Música"),
    Question("¿Cuál es el gas más abundante en la atmósfera terrestre?", listOf("Oxígeno", "Nitrógeno", "Dióxido de carbono", "Argón"), "Nitrógeno", "Ciencia"),
    Question("¿De qué color es el sol en realidad?", listOf("Rojo", "Amarillo", "Blanco", "Azul"), "Blanco", "Ciencia"),
    Question("¿Cuántos continentes existen en la Tierra?", listOf("5", "6", "7", "8"), "7", "Geografía"),
    Question("¿Qué significa la palabra 'renaissance'?", listOf("Renacimiento", "Revolución", "Ilustración", "Revolución Industrial"), "Renacimiento", "Historia"),
    Question("¿Cuál es el animal terrestre más rápido?", listOf("León", "Cheetah", "Guepardo", "Tigre"), "Cheetah", "Fauna"),
    Question("¿Quién fue el autor de 'Cien años de soledad'?", listOf("Gabriel García Márquez", "Carlos Fuentes", "Mario Vargas Llosa", "Julio Cortázar"), "Gabriel García Márquez", "Literatura"),
    Question("¿Cuál es la lengua más hablada en el mundo?", listOf("Español", "Inglés", "Mandarín", "Francés"), "Mandarín", "Lenguas"),
    Question("¿En qué continente se encuentra la selva amazónica?", listOf("Asia", "África", "América", "Oceanía"), "América", "Geografía"),
    Question("¿Quién fue el líder de la Revolución Mexicana?", listOf("Emiliano Zapata", "Pancho Villa", "Venustiano Carranza", "Porfirio Díaz"), "Emiliano Zapata", "Historia"),
    Question("¿En qué año comenzó la Segunda Guerra Mundial?", listOf("1939", "1941", "1914", "1929"), "1939", "Historia"),
    Question("¿Quién inventó la bombilla eléctrica?", listOf("Nikola Tesla", "Albert Einstein", "Thomas Edison", "Graham Bell"), "Thomas Edison", "Ciencia"),
    Question("¿Cuál es el animal más grande del planeta?", listOf("Ballena azul", "Elefante africano", "Tiburón blanco", "Rinoceronte"), "Ballena azul", "Fauna"),
    Question("¿Qué ciudad es conocida como 'La Ciudad de la Luz'?", listOf("Nueva York", "Londres", "París", "Roma"), "París", "Geografía"),
    Question("¿Qué famoso científico desarrolló la teoría de la relatividad?", listOf("Isaac Newton", "Albert Einstein", "Niels Bohr", "Galileo Galilei"), "Albert Einstein", "Ciencia"),
    )

    private var currentQuestionIndex = 0

    init {
        loadNextQuestion() // Empieza con la primera pregunta
    }

    private fun loadNextQuestion() {
        _currentQuestion.value = questions.getOrNull(currentQuestionIndex)
    }

    fun submitAnswer(answer: String) {
        if (_currentQuestion.value?.correctAnswer == answer) {
            _score.value += 1
        }
        currentQuestionIndex += 1
        if (currentQuestionIndex >= questions.size) {
            _isGameOver.value = true
        } else {
            loadNextQuestion()
        }
    }

    fun restartGame() {
        _score.value = 0
        _isGameOver.value = false
        currentQuestionIndex = 0
        loadNextQuestion()
    }
}




































































































































