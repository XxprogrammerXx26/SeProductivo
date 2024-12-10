package com.myprimer.seproductive.Pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.myprimer.seproductive.R


@Composable
fun Home(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(  // Gradient for a more dynamic background
                    colors = listOf(Color(0xFF000046), Color(0xFF1CB5E0))  // Dark to light blue gradient
                )
            ),
        contentAlignment = Alignment.Center  // Center content
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Optional: Add an icon or logo
            Icon(
                painter = painterResource(id = R.drawable.p),  // Add your logo resource here
                contentDescription = "Logo",
                tint = Color.White,
                modifier = Modifier.size(400.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))  // Space between logo and text

            Text(
                text = " ",  // Changed to give a welcoming feel
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge  // Consistent with Material Design
            )

            Spacer(modifier = Modifier.height(32.dp))  // Space before button

//            Button(
//                onClick = { /* Navigate to next screen */ },
//                shape = RoundedCornerShape(12.dp),
//                colors = ButtonDefaults.buttonColors(
//                    //            backgroundscolors = MaterialTheme.colorScheme.primary  // Primary color for modern look
//                ),
//                modifier = Modifier
//                    .width(200.dp)
//                    .height(56.dp)  // Taller button for emphasis
//            ) {
            Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = "version 1.0",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }


