package com.myprimer.seproductivo.Pages




import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.myprimer.seproductivo.Modelo.MatematicasViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Face
import androidx.compose.ui.graphics.RectangleShape












@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MatematicasScreen(matematicasViewModel: MatematicasViewModel = viewModel()) {
    val baseNumber by matematicasViewModel.baseNumber.collectAsState()
    val tablaMultiplicar by matematicasViewModel.tablaMultiplicar.collectAsState()
    val isSortedAscending by matematicasViewModel.isSortedAscending.collectAsState()

    val backgroundColor by animateColorAsState(
        targetValue = if (baseNumber % 2 == 0) Color(0xFF009688) else Color(0xFF009688)
    )

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Botones de selección de tabla
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            (1..13).forEach { number ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = if (baseNumber == number) Color(0xFF0288D1) else Color(
                                0xFF4CAF50
                            ),
                            shape = RectangleShape
                        )
                        .clickable { matematicasViewModel.setBaseNumber(number) }
                ) {
                    Text(
                        text = number.toString(),
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Botón para cambiar el orden
        IconButton(onClick = { matematicasViewModel.toggleSortOrder() }) {
            Icon(
                imageVector = if (isSortedAscending) Icons.Default.Face else Icons.Default.Build,
                contentDescription = "Ordenar",
                tint = Color.White
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Visualización de la tabla de multiplicar para el número base seleccionado
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF4CAF50), shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                items(tablaMultiplicar) { item ->
                    Text(
                        text = "${item.operation} = ${item.result}",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botones de navegación entre tablas (opcional)
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { matematicasViewModel.setBaseNumber((baseNumber - 1).coerceAtLeast(1)) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "Tabla anterior",
                    tint = Color.White
                )
            }

            IconButton(
                onClick = { matematicasViewModel.setBaseNumber((baseNumber + 1).coerceAtMost(13)) },
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = "Tabla siguiente",
                    tint = Color.White
                )
            }
        }
    }
}






