package com.myprimer.seproductivo.Modelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


//
//class MatematicasViewModel : ViewModel() {
//    // Usamos StateFlow para manejar el estado
//    private val _baseNumber = MutableStateFlow(5)
//    val baseNumber: StateFlow<Int> = _baseNumber
//
//    private val _range = MutableStateFlow(1..10)
//    val range: StateFlow<IntRange> = _range
//
//    private val _correctAnswers = MutableStateFlow(0) // Para llevar cuenta de respuestas correctas
//    val correctAnswers: StateFlow<Int> = _correctAnswers
//
//    private val _question = MutableStateFlow(generateQuestion()) // Pregunta actual en modo juego
//    val question: StateFlow<String> = _question
//
//    private val _userAnswer = MutableStateFlow("") // Respuesta del usuario
//    val userAnswer: StateFlow<String> = _userAnswer
//
//    // Función para cambiar el número base
//    fun setBaseNumber(number: Int) {
//        _baseNumber.value = number
//        _question.value = generateQuestion() // Generar nueva pregunta con el nuevo número base
//    }
//
//    // Función para cambiar el rango de números
//    fun setRange(start: Int, end: Int) {
//        _range.value = start..end
//    }
//
//    // Función para obtener solo la tabla de multiplicar (sin divisiones)
//    fun getTablaMultiplicar(): List<String> {
//        return (1..12).map { i ->
//            val multiplicacion = "$i x ${_baseNumber.value} = ${i * _baseNumber.value}"
//            multiplicacion // Solo se muestra la multiplicación
//        }
//    }
//
//    // Generar una nueva pregunta de multiplicación aleatoria
//    private fun generateQuestion(): String {
//        val randomNum = (1..12).random() // Rango de 1 a 12 para multiplicar
//        return "${_baseNumber.value} x $randomNum"
//    }
//
//    // Función para verificar la respuesta del usuario
//    fun checkAnswer() {
//        val correctAnswer = (_baseNumber.value * getQuestionMultiplier())
//        val userInput = _userAnswer.value.toIntOrNull()
//
//        if (userInput == correctAnswer) {
//            _correctAnswers.value += 1 // Incrementa la cuenta de respuestas correctas
//        }
//
//        _question.value = generateQuestion() // Genera una nueva pregunta después de verificar
//        _userAnswer.value = "" // Limpiar la respuesta del usuario
//    }
//
//    // Obtener el multiplicador de la pregunta actual (el número que estamos multiplicando)
//    private fun getQuestionMultiplier(): Int {
//        val parts = _question.value.split(" x ")
//        return parts[1].toInt()
//    }
//
//    // Función para actualizar la respuesta del usuario
//    fun setUserAnswer(answer: String) {
//        _userAnswer.value = answer
//    }
//
//    // Función para limpiar la respuesta (para el botón "C" de la calculadora)
//    fun clearAnswer() {
//        _userAnswer.value = ""
//    }
//
//    // Función para agregar dígitos a la respuesta del usuario (para los botones de la calculadora)
//    fun appendAnswer(digit: String) {
//        _userAnswer.value += digit
//    }
//}




























//
//class MatematicasViewModel : ViewModel() {
//    private val _baseNumber = MutableStateFlow(1)
//    val baseNumber: StateFlow<Int> get() = _baseNumber
//
//    fun setBaseNumber(number: Int) {
//        _baseNumber.value = number
//    }
//
//    fun getTablaMultiplicar(): List<MultiplicationItem> {
//        val base = _baseNumber.value
//        return (0..10).map { multiplier ->
//            MultiplicationItem(
//                operation = "$base x $multiplier",
//                result = base * multiplier
//            )
//        }
//    }
//}




































class MatematicasViewModel : ViewModel() {
    private val _baseNumber = MutableStateFlow(1)
    val baseNumber: StateFlow<Int> get() = _baseNumber

    private val _isSortedAscending = MutableStateFlow(true)
    val isSortedAscending: StateFlow<Boolean> get() = _isSortedAscending

    private val _tablaMultiplicar = MutableStateFlow(generateTablaMultiplicar(_baseNumber.value))
    val tablaMultiplicar: StateFlow<List<MultiplicationItem>> get() = _tablaMultiplicar

    init {
        // Observa cambios en baseNumber y actualiza la tabla con el orden predeterminado
        viewModelScope.launch {
            _baseNumber.collect { number ->
                updateTablaMultiplicar(number)
            }
        }
    }

    fun setBaseNumber(number: Int) {
        _baseNumber.value = number
    }

    private fun updateTablaMultiplicar(base: Int) {
        _tablaMultiplicar.value = generateTablaMultiplicar(base).let {
            if (_isSortedAscending.value) it.sortedBy { item -> item.result }
            else it.sortedByDescending { item -> item.result }
        }
    }

    private fun generateTablaMultiplicar(base: Int): List<MultiplicationItem> {
        return (0..10).map { multiplier ->
            MultiplicationItem(
                operation = "$base x $multiplier",
                result = base * multiplier
            )
        }
    }

    fun toggleSortOrder() {
        _isSortedAscending.value = !_isSortedAscending.value
        updateTablaMultiplicar(_baseNumber.value)
    }
}






