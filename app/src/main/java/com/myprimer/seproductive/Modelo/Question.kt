package com.myprimer.seproductive.Modelo


data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val category: String // Nueva propiedad para la categoría
)



