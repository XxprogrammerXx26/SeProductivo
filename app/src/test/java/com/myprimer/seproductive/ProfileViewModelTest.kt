package com.myprimer.seproductive.Modelo

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProfileViewModelTest {

    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setup() {
        // Inicializa el ViewModel para las pruebas
        viewModel = ProfileViewModel()
    }

    @Test
    fun testLoadUser_success() = runTest {
        // Simula que FirebaseAuth devuelve un usuario autenticado
        val fakeUser = FakeFirebaseUser("fake_uid", "fake@example.com")

        // Asigna un usuario ficticio a FirebaseAuth
        FirebaseAuth.getInstance().currentUser = fakeUser

        // Llama al método para cargar el usuario
        viewModel.loadUser()

        // Verifica que el usuario se haya cargado correctamente
        assertNotNull(viewModel.user.first())
        assertEquals(fakeUser, viewModel.user.first())
    }

    @Test
    fun testLoadUser_noUser() = runTest {
        // Simula que no hay usuario autenticado
        FirebaseAuth.getInstance().currentUser = null

        // Llama al método para cargar el usuario
        viewModel.loadUser()

        // Verifica que el valor de _user sea null
        assertNull(viewModel.user.first())
    }

    @Test
    fun testSignOut_success() = runTest {
        // Simula que el sign out se realiza correctamente
        val fakeUser = FakeFirebaseUser("fake_uid", "fake@example.com")

        // Asigna un usuario ficticio a FirebaseAuth
        FirebaseAuth.getInstance().currentUser = fakeUser

        // Llama al método para cerrar sesión
        viewModel.signOut()

        // Verifica que el estado de _user sea null y _isLoggedOut sea true
        assertNull(viewModel.user.first())
        assertTrue(viewModel.isLoggedOut.first())
    }

    @Test
    fun testSignOut_failure() = runTest {
        // Fuerza que FirebaseAuth falle al intentar cerrar sesión
        FirebaseAuth.getInstance().currentUser = null

        // Llama al método para cerrar sesión
        viewModel.signOut()

        // Verifica que el estado de _isLoggedOut sea false
        assertFalse(viewModel.isLoggedOut.first())
    }

    // Clase simulada de FirebaseUser
    class FakeFirebaseUser(private val uid: String, private val email: String?) : FirebaseUser() {
        override fun getUid(): String = uid
        override fun getEmail(): String? = email
        // Implementar otros métodos que se necesiten
    }
}
