package com.myprimer.seproductive.Pages

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color
import com.myprimer.seproductive.Modelo.Question



//
//@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("RememberReturnType")
//@Composable
//fun Idiomas() {
//
//    val learnedWordsInEnglish = remember { mutableStateListOf<String>() }
//    val learnedWordsInSpanish = remember { mutableStateListOf<String>() }
//    val newWordEnglish = remember { mutableStateOf("") }
//    val newWordSpanish = remember { mutableStateOf("") }
//    val expandedState = remember { mutableStateOf(false) }  // Track the expanded state of the block
//    val flashcardsTitle = remember { mutableStateOf("Título de Flashcards Guardadas") } // Nuevo estado para el título de las flashcards
//    val newFlashcardTitle = remember { mutableStateOf("") } // Estado para el nuevo título de las flashcards
//
//
//    Column(
//        modifier = Modifier
//            .padding(24.dp)
//            .fillMaxSize()
//            .background(MaterialTheme.colorScheme.background)
//    ) {
//        // Title
//        Text(
//            text = "Palabras Aprendidas",
//            style = MaterialTheme.typography.headlineLarge.copy(
//                color = MaterialTheme.colorScheme.primary,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.padding(bottom = 24.dp)
//        )
//
//        // Input for English word
//        OutlinedTextField(
//            value = newWordEnglish.value,
//            onValueChange = { newWordEnglish.value = it },
//            label = { Text("Palabra en inglés", color = MaterialTheme.colorScheme.onSurface) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            shape = MaterialTheme.shapes.medium,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
//            ),
//            leadingIcon = {
//                Icon(Icons.Filled.Add, contentDescription = "Agregar palabra en inglés", tint = MaterialTheme.colorScheme.primary)
//            },
//            singleLine = true,
//        )
//
//        // Input for Spanish word (translation)
//        OutlinedTextField(
//            value = newWordSpanish.value,
//            onValueChange = { newWordSpanish.value = it },
//            label = { Text("Traducción en español", color = MaterialTheme.colorScheme.onSurface) },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            shape = MaterialTheme.shapes.medium,
//            colors = TextFieldDefaults.outlinedTextFieldColors(
//                focusedBorderColor = MaterialTheme.colorScheme.primary,
//                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
//            ),
//            leadingIcon = {
//                Icon(Icons.Filled.Translate, contentDescription = "Agregar traducción", tint = MaterialTheme.colorScheme.primary)
//            },
//            singleLine = true,
//        )
//
//        // Show error message if either input is empty
//        if (newWordEnglish.value.isEmpty() || newWordSpanish.value.isEmpty()) {
//            Text(
//                text = "Ambas palabras son necesarias.",
//                color = MaterialTheme.colorScheme.error,
//                style = MaterialTheme.typography.bodySmall,
//                modifier = Modifier.padding(bottom = 16.dp)
//            )
//        }
//
//        // Button to add words to the list
//        Button(
//            onClick = {
//                if (newWordEnglish.value.isNotEmpty() && newWordSpanish.value.isNotEmpty()) {
//                    learnedWordsInEnglish.add(newWordEnglish.value)
//                    learnedWordsInSpanish.add(newWordSpanish.value)
//                    newWordEnglish.value = ""
//                    newWordSpanish.value = ""
//                }
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 24.dp)
//                .height(56.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
//        ) {
//            Icon(Icons.Filled.Add, contentDescription = "Agregar palabras", tint = Color.White)
//            Spacer(modifier = Modifier.width(8.dp))
//            Text("Agregar palabras", color = Color.White)
//        }
//
//        // Title for flashcards section
//        Text(
//            text = "Tus Flashcards",
//            style = MaterialTheme.typography.titleLarge.copy(
//                color = MaterialTheme.colorScheme.primary,
//                fontWeight = FontWeight.Bold
//            ),
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        // Flashcards block
//        Card(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(vertical = 8.dp)
//                .clickable { expandedState.value = !expandedState.value },
//            shape = MaterialTheme.shapes.medium,
//            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
//        ) {
//            Column(modifier = Modifier.padding(16.dp)) {
//                // Input for new flashcards title
//                OutlinedTextField(
//                    value = newFlashcardTitle.value,
//                    onValueChange = { newFlashcardTitle.value = it },
//                    label = { Text("Agregar título a las flashcards", color = MaterialTheme.colorScheme.onSurface) },
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp),
//                    shape = MaterialTheme.shapes.medium,
//                    colors = TextFieldDefaults.outlinedTextFieldColors(
//                        focusedBorderColor = MaterialTheme.colorScheme.primary,
//                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
//                    ),
//                    leadingIcon = {
//                        Icon(Icons.Filled.Edit, contentDescription = "Editar título", tint = MaterialTheme.colorScheme.primary)
//                    },
//                    singleLine = true,
//                )
//
//                // Button to set the title of flashcards
//                Button(
//                    onClick = {
//                        if (newFlashcardTitle.value.isNotEmpty()) {
//                            flashcardsTitle.value = newFlashcardTitle.value
//                            newFlashcardTitle.value = ""  // Clear the input field after setting the title
//                        }
//                    },
//                    modifier = Modifier.fillMaxWidth(),
//                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
//                ) {
//                    Text("Agregar Título a las Flashcards", color = Color.White)
//                }
//
//                // Title for Flashcard section
//                Text(
//                    text = flashcardsTitle.value,  // El título dinámico de las flashcards
//                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface),
//                    fontWeight = FontWeight.Bold,
//                    modifier = Modifier.padding(top = 16.dp)
//                )
//
//                // Display number of words in the flashcard block
//                Text(
//                    text = "Tienes ${learnedWordsInEnglish.size} palabras guardadas",
//                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
//                    modifier = Modifier.padding(top = 8.dp)
//                )
//
//                // Display the word pairs if the block is expanded
//                if (expandedState.value) {
//                    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
//                        itemsIndexed(learnedWordsInEnglish) { index, word ->
//                            Text(
//                                text = "${word} -> ${learnedWordsInSpanish[index]}",
//                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewIdiomas() {
//    MaterialTheme {
//        Idiomas()
//    }
//}
//
//





























import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.myprimer.seproductive.Modelo.IdiomasViewModel



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun Idiomas(viewModel: IdiomasViewModel = viewModel()) {

    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Título principal
        Text(
            text = "",
            style = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Input para la palabra en inglés
        OutlinedTextField(
            value = viewModel.newWordEnglish.value,
            onValueChange = { viewModel.newWordEnglish.value = it },
            label = { Text("Palabra en inglés", color = MaterialTheme.colorScheme.onSurface) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            leadingIcon = {
                Icon(Icons.Filled.Add, contentDescription = "Agregar palabra en inglés", tint = MaterialTheme.colorScheme.primary)
            },
            singleLine = true,
        )

        // Input para la palabra en español
        OutlinedTextField(
            value = viewModel.newWordSpanish.value,
            onValueChange = { viewModel.newWordSpanish.value = it },
            label = { Text("Traducción en español", color = MaterialTheme.colorScheme.onSurface) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            shape = MaterialTheme.shapes.medium,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
            ),
            leadingIcon = {
                Icon(Icons.Filled.Translate, contentDescription = "Agregar traducción", tint = MaterialTheme.colorScheme.primary)
            },
            singleLine = true,
        )

        // Mostrar mensaje de error si algún campo está vacío
        if (viewModel.newWordEnglish.value.isEmpty() || viewModel.newWordSpanish.value.isEmpty()) {
            Text(
                text = "Ambas palabras son necesarias.",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Botón para agregar las palabras
        Button(
            onClick = {
                viewModel.addWord()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Agregar palabras", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Agregar palabras", color = Color.White)
        }

        // Título de la sección de flashcards
        Text(
            text = "Tus Flashcards",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Bloque de flashcards
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
                .clickable { viewModel.toggleFlashcardsExpansion() },
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // Input para el título de flashcards
                OutlinedTextField(
                    value = viewModel.newFlashcardTitle.value,
                    onValueChange = { viewModel.newFlashcardTitle.value = it },
                    label = { Text("Agregar título a las flashcards", color = MaterialTheme.colorScheme.onSurface) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        Icon(Icons.Filled.Edit, contentDescription = "Editar título", tint = MaterialTheme.colorScheme.primary)
                    },
                    singleLine = true,
                )

                // Botón para actualizar el título de las flashcards
                Button(
                    onClick = {
                        viewModel.updateFlashcardTitle()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Agregar Título a las Flashcards", color = Color.White)
                }

                // Título de las flashcards
                Text(
                    text = viewModel.flashcardsTitle.value,
                    style = MaterialTheme.typography.titleMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )

                // Número de palabras guardadas
                Text(
                    text = "Tienes ${viewModel.learnedWordsInEnglish.size} palabras guardadas",
                    style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onSurface),
                    modifier = Modifier.padding(top = 8.dp)
                )

                // Mostrar las palabras si el bloque está expandido
                if (viewModel.expandedState.value) {
                    LazyColumn(modifier = Modifier.padding(top = 8.dp)) {
                        itemsIndexed(viewModel.learnedWordsInEnglish) { index, word ->
                            Text(
                                text = "${word} -> ${viewModel.learnedWordsInSpanish[index]}",
                                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewIdiomas() {
    MaterialTheme {
        Idiomas()
    }
}


































































