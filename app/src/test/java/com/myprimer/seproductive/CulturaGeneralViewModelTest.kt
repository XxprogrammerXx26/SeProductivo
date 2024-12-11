import com.myprimer.seproductive.Modelo.culturageneralviewmodel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class CulturaGeneralViewModelTest {

    private lateinit var viewModel: culturageneralviewmodel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = culturageneralviewmodel()
    }

    @Test
    fun `la primera pregunta es cargada correctamente`() = runTest {
        val firstQuestion = viewModel.currentQuestion.first()
        assertNotNull("La pregunta inicial no debe ser nula", firstQuestion)
        assertEquals("¿Cuál es la capital de Francia?", firstQuestion?.question)
    }

    @Test
    fun `responder correctamente incrementa el puntaje`() = runTest {
        val initialScore = viewModel.score.first()
        val correctAnswer = viewModel.currentQuestion.first()?.correctAnswer ?: ""

        viewModel.submitAnswer(correctAnswer)
        advanceUntilIdle() // Asegura que las corutinas se ejecuten

        val updatedScore = viewModel.score.first()
        assertEquals("El puntaje debería incrementarse en 1", initialScore + 1, updatedScore)
    }

    @Test
    fun `responder incorrectamente no incrementa el puntaje`() = runTest {
        val initialScore = viewModel.score.first()
        viewModel.submitAnswer("Respuesta incorrecta")
        advanceUntilIdle()

        val updatedScore = viewModel.score.first()
        assertEquals("El puntaje no debería cambiar", initialScore, updatedScore)
    }

    @Test
    fun `cuando termina el juego, isGameOver es verdadero`() = runTest {
        // Avanza a través de todas las preguntas
        while (!viewModel.isGameOver.first()) {
            viewModel.submitAnswer("Respuesta incorrecta") // Respuestas arbitrarias
        }
        advanceUntilIdle()

        assertTrue("El juego debería terminar después de todas las preguntas", viewModel.isGameOver.first())
    }

    @Test
    fun `reiniciar el juego restablece los valores`() = runTest {
        viewModel.submitAnswer("Respuesta incorrecta") // Avanza al menos una pregunta
        viewModel.restartGame()
        advanceUntilIdle()

        assertEquals("El puntaje debería ser 0 tras reiniciar", 0, viewModel.score.first())
        assertFalse("El juego no debería estar terminado tras reiniciar", viewModel.isGameOver.first())
        assertNotNull("La primera pregunta debería cargarse tras reiniciar", viewModel.currentQuestion.first())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}
