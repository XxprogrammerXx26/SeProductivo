package com.myprimer.seproductivo.Pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun Profile(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Configuración",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Button(
            onClick = {
                // Lógica de cierre de sesión (aquí puedes agregar el comportamiento que desees)
                println("Cerrar sesión") // Solo para ilustrar
            },
            colors = ButtonDefaults.buttonColors(),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .width(200.dp)
                .height(48.dp)
        ) {
            Text(text = "Cerrar Sesión", color = MaterialTheme.colorScheme.onSecondary)
        }
    }
}

