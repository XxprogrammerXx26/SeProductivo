package com.myprimer.seproductive


import com.myprimer.seproductive.Modelo.IdiomasViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class IdiomasViewModelTest {

    private lateinit var viewModel: IdiomasViewModel

    @Before
    fun setUp() {
        viewModel = IdiomasViewModel()
    }

    @Test
    fun testAddWord() {

        viewModel.newWordEnglish.value = "Hello"
        viewModel.newWordSpanish.value = "Hola"


        viewModel.addWord()


        assertTrue(viewModel.learnedWordsInEnglish.contains("Hello"))
        assertTrue(viewModel.learnedWordsInSpanish.contains("Hola"))


        assertEquals(viewModel.newWordEnglish.value, "")
        assertEquals(viewModel.newWordSpanish.value, "")
    }

    @Test
    fun testUpdateFlashcardTitle() {

        viewModel.newFlashcardTitle.value = "New Flashcard Title"


        viewModel.updateFlashcardTitle()


        assertEquals(viewModel.flashcardsTitle.value, "New Flashcard Title")


        assertEquals(viewModel.newFlashcardTitle.value, "")
    }

    @Test
    fun testToggleFlashcardsExpansion() {

        assertFalse(viewModel.expandedState.value)


        viewModel.toggleFlashcardsExpansion()


        assertTrue(viewModel.expandedState.value)


        viewModel.toggleFlashcardsExpansion()


        assertFalse(viewModel.expandedState.value)
    }
}
