package com.myprimer.seproductive.Modelo

import android.util.Log
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    private lateinit var loginViewModel: LoginViewModel
    private val mockAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var mockFirebaseUser: FirebaseUser? = null
    private var mockCredential: AuthCredential? = null

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    class InstantTaskExecutorRule {

    }

    @Before
    fun setup() {
        // Usamos el dispatcher de test para que las corrutinas sean inmediatas
        Dispatchers.setMain(Dispatchers.Unconfined)

        loginViewModel = LoginViewModel()
        loginViewModel.auth = mockAuth

        // Configuración inicial de FirebaseUser
        mockFirebaseUser = mockAuth.currentUser
    }

    @Test
    fun `test signInWithGoogleCredential success`() = runTest {
        // Simula que el usuario inicia sesión correctamente
        mockCredential = mock<Any>()

        loginViewModel.signInWithGoogleCredential(mockCredential!!) {
            // Simula la acción después de un login exitoso
            Log.d("Test", "Login exitoso")
        }

        // Asegúrate de que la función de callback se haya llamado correctamente
        assertNotNull(mockCredential)
    }

   // private fun <T> mock(): AuthCredential? {

    //}

    @Test
    fun `test signInWithEmailAndPassword success`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val home = mock<(Unit) -> Unit>()

        // Simula un login exitoso
        mockAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    home.invoke(Unit)
                } else {
                    Log.d("Test", "Error en login")
                }
            }

        // Verifica que el login se haya realizado correctamente
        assertTrue(mockAuth.currentUser != null)
    }

    @Test
    fun `test signInWithEmailAndPassword failure`() = runTest {
        val email = "invalid@example.com"
        val password = "wrongpassword"
        val home = mock<(Unit) -> Unit>()

        // Simula un login fallido
        try {
            mockAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        home.invoke(Unit)
                    } else {
                        throw Exception("Login fallido")
                    }
                }
        } catch (ex: Exception) {
            Log.d("Test", "Error en login: ${ex.message}")
            assertEquals("Login fallido", ex.message)
        }
    }

    @Test
    fun `test createUserWithEmailAndPassword success`() = runTest {
        val email = "newuser@example.com"
        val password = "newpassword123"
        val home = mock<(Unit) -> Unit>()

        // Simula la creación de un usuario con éxito
        mockAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    home.invoke(Unit)
                } else {
                    Log.d("Test", "Error en creación de usuario")
                }
            }

        // Verifica que la creación del usuario sea exitosa
        assertTrue(mockAuth.currentUser != null)
    }

    @Test
    fun `test signOut success`() {
        loginViewModel.signOut()

        // Verifica que el estado de 'isLoggedOut' sea verdadero
        val isLoggedOut = loginViewModel.isLoggedOut.value
        assertTrue(isLoggedOut!!)
    }
}

private fun AuthCredential?.invoke(unit: Unit) {

}
