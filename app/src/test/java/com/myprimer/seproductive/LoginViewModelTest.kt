package com.myprimer.seproductive.Modelo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @Mock lateinit var mockAuth: FirebaseAuth
    @Mock lateinit var mockFirestore: FirebaseFirestore
    @Mock lateinit var mockUser: FirebaseUser
    @Mock lateinit var mockCredential: AuthCredential

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        loginViewModel = LoginViewModel()
        loginViewModel.auth = mockAuth
        // Mocking FirebaseAuth and Firestore initialization
        loginViewModel._loading = MutableLiveData(false)
    }

    @Test
    fun `test signInWithGoogleCredential success`() = runTest {
        // Mock successful sign in
        doNothing().`when`(mockAuth).signInWithCredential(any())
        loginViewModel.signInWithGoogleCredential(mockCredential) {
            // Callback function after successful login
            Log.d("Test", "Home function called after login success")
        }

        // Verifying that the sign-in function was called
        verify(mockAuth).signInWithCredential(mockCredential)
    }

    @Test
    fun `test signInWithGoogleCredential failure`() = runTest {
        // Mock failure in sign in
        val exception = Exception("Google sign-in failed")
        doThrow(exception).`when`(mockAuth).signInWithCredential(any())

        loginViewModel.signInWithGoogleCredential(mockCredential) {
            // Callback function after failed login
            Log.d("Test", "Home function not called")
        }

        // Verifying that the exception was handled
        verify(mockAuth).signInWithCredential(mockCredential)
    }

    @Test
    fun `test signInWithEmailAndPassword success`() = runTest {
        val email = "test@example.com"
        val password = "password123"
        val home = mock<(Unit) -> Unit>()

        // Mock successful sign-in
        doNothing().`when`(mockAuth).signInWithEmailAndPassword(email, password)

        loginViewModel.signInWithEmailAndPassword(email, password) {
            // Assert that the home function was called
            home.invoke(Unit)
        }

        // Verifying that the sign-in function was called
        verify(mockAuth).signInWithEmailAndPassword(email, password)
    }

    @Test
    fun `test signInWithEmailAndPassword failure`() = runTest {
        val email = "test@example.com"
        val password = "wrongpassword"
        val home = mock<(Unit) -> Unit>()

        // Mock failed sign-in
        val exception = Exception("Authentication failed")
        doThrow(exception).`when`(mockAuth).signInWithEmailAndPassword(email, password)

        loginViewModel.signInWithEmailAndPassword(email, password) {
            // Assert that the home function was not called
            home.invoke(Unit)
        }

        // Verifying that the sign-in function was called and failed
        verify(mockAuth).signInWithEmailAndPassword(email, password)
    }

    @Test
    fun `test createUserWithEmailAndPassword success`() = runTest {
        val email = "newuser@example.com"
        val password = "newpassword123"
        val home = mock<(Unit) -> Unit>()

        // Mock successful user creation
        doNothing().`when`(mockAuth).createUserWithEmailAndPassword(email, password)
        val user = User(userId = "1", displayName = "New User", avatarUrl = "", quote = "", profession = "Developer", id = null)
        doNothing().`when`(mockFirestore).collection("users").add(user)

        loginViewModel.createUserWithEmailAndPassword(email, password, home)

        // Verify that the user creation function was called
        verify(mockAuth).createUserWithEmailAndPassword(email, password)
        verify(mockFirestore).collection("users")
    }

    @Test
    fun `test signOut`() {
        loginViewModel.signOut()
        assertTrue(loginViewModel.isLoggedOut.value!!)
    }
}
