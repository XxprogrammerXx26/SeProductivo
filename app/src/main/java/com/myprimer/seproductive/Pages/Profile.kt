package com.myprimer.seproductive.Pages

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.myprimer.seproductive.Modelo.LoginViewModel



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier,
    viewModel: LoginViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val googleSignInClient = remember {
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
        )
    }

    val user = FirebaseAuth.getInstance().currentUser
    val isUserLoggedOut by viewModel.isLoggedOut.collectAsState()
    val showDialog = remember { mutableStateOf(false) }
    val backgroundColor = remember { mutableStateOf(Color(0xFFF1F1F1)) }


    val showColorPickerDialog = remember { mutableStateOf(false) }

    if (showColorPickerDialog.value) {
        ColorPickerDialog(
            onColorSelected = { selectedColor ->
                backgroundColor.value = selectedColor
                showColorPickerDialog.value = false
            },
            onDismiss = { showColorPickerDialog.value = false }
        )
    }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Confirmación", fontWeight = FontWeight.Bold) },
            text = { Text("¿Estás seguro de que deseas cerrar sesión?") },
            confirmButton = {
                Button(
                    onClick = {
                        googleSignInClient.signOut().addOnCompleteListener {
                            FirebaseAuth.getInstance().signOut()
                            viewModel.signOut()
                            navController.navigate("login") {
                                popUpTo("menu_principal") { inclusive = true }
                            }
                        }
                        showDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) { Text("Sí") }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog.value = false },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                ) { Text("No") }
            }
        )
    }


    Column(modifier = modifier.fillMaxSize().background(backgroundColor.value)) {
        TopAppBar(
            title = { Text("Perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold) },
            colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Foto de perfil
            if (user?.photoUrl != null) {
                Image(
                    painter = rememberImagePainter(user.photoUrl),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .shadow(4.dp, CircleShape)
                )
            } else {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Icono de usuario",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(4.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .shadow(4.dp, CircleShape),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (user != null) {
                Text(
                    text = "Hola, ${user.displayName ?: "Usuario"}!",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = user.email ?: "Correo no disponible",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            } else {
                Text(
                    text = "No hay usuario autenticado",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = { showColorPickerDialog.value = true },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
                    .padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = "Cambiar color de fondo",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Cambiar Fondo", color = MaterialTheme.colorScheme.onPrimary)
            }


            Button(
                onClick = { showDialog.value = true },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
                    .padding(bottom = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = "Cerrar sesión",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Cerrar Sesión", color = MaterialTheme.colorScheme.onSecondary)
            }


            Button(
                onClick = {
                    user?.delete()?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            viewModel.signOut()
                            navController.navigate("login") {
                                popUpTo("menu_principal") { inclusive = true }
                            }
                        } else {
                            println("Error al borrar la cuenta: ${task.exception?.message}")
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Borrar cuenta",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Borrar Cuenta", color = Color.White)
            }

            if (isUserLoggedOut) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Has cerrado sesión correctamente",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun ColorPickerDialog(onColorSelected: (Color) -> Unit, onDismiss: () -> Unit) {

    val colors = listOf(
        Color(0xFF673AB7),
        Color(0xFFF1F1F1),
        Color(0xFFE3F2FD),
        Color(0xFFFFF8E1),
        Color(0xFFFCE4EC),
        Color(0xFF2196F3),
        Color(0xFF388E3C),
        Color(0xFFF44336)
    )

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Selecciona un color de fondo") },
        text = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                colors.forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(color)
                            .clickable {
                                onColorSelected(color)
                            }
                    )
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Cerrar")
            }
        }
    )
}






