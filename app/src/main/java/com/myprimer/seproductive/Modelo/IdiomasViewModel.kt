package com.myprimer.seproductive.Modelo



import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class IdiomasViewModel : ViewModel() {

    // Listas de palabras aprendidas
    val learnedWordsInEnglish = mutableStateListOf<String>()
    val learnedWordsInSpanish = mutableStateListOf<String>()

    // Estado de las nuevas palabras
    val newWordEnglish = mutableStateOf("")
    val newWordSpanish = mutableStateOf("")

    // Estado del título de flashcards
    val flashcardsTitle = mutableStateOf("Título de Flashcards Guardadas")

    // Estado para el nuevo título de flashcards
    val newFlashcardTitle = mutableStateOf("")

    // Estado para manejar si el bloque de flashcards está expandido
    val expandedState = mutableStateOf(false)

    // Función para agregar palabras
    fun addWord() {
        if (newWordEnglish.value.isNotEmpty() && newWordSpanish.value.isNotEmpty()) {
            learnedWordsInEnglish.add(newWordEnglish.value)
            learnedWordsInSpanish.add(newWordSpanish.value)
            newWordEnglish.value = ""
            newWordSpanish.value = ""
        }
    }

    // Función para actualizar el título de las flashcards
    fun updateFlashcardTitle() {
        if (newFlashcardTitle.value.isNotEmpty()) {
            flashcardsTitle.value = newFlashcardTitle.value
            newFlashcardTitle.value = "" // Limpiar después de asignar
        }
    }

    // Función para alternar el estado expandido de flashcards
    fun toggleFlashcardsExpansion() {
        expandedState.value = !expandedState.value
    }
}







































