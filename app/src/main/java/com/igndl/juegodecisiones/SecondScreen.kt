package com.igndl.juegodecisiones

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.igndl.juegodecisiones.db.Pregunta
import kotlin.random.Random

var INDICE: Int = 0

@Composable
fun SecondScreen(navController: NavController, preguntas: List<Pregunta>) {// Estado para controlar si alguna imagen ha sido pulsada
    INDICE =( INDICE + 1 ) % preguntas.size
    var isImageClicked by remember { mutableStateOf(false) }

    // Animar la opacidad desde 0.5 a 0.3 cuando las imágenes son pulsadas
    val alphaValue by animateFloatAsState(targetValue = if (isImageClicked) 0.3f else 0.5f)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Pantalla dividida en dos imágenes
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Parte superior con imagen y texto en el centro
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable { isImageClicked = true }, // Detectar cuando la imagen es clicada
                contentAlignment = Alignment.Center // Alineación central para el texto
            ) {
                Image(
                    painter = painterResource(id = R.drawable.coming_soon), // Tu imagen aquí
                    contentDescription = "Imagen superior",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = alphaValue } // Animar la opacidad
                        .animateContentSize(), // Añadir animación de contenido
                    contentScale = ContentScale.Crop // Ajustar la imagen al tamaño del contenedor
                )
                // Texto en el centro de la imagen superior
                Text(
                    text = if (isImageClicked) preguntas[INDICE].respuesta1+"\n"+preguntas[INDICE].porcentaje1+"%" else preguntas[INDICE].respuesta1,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }

            // Parte inferior con imagen y texto en el centro
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .clickable { isImageClicked = true }, // Detectar cuando la imagen es clicada
                contentAlignment = Alignment.Center // Alineación central para el texto
            ) {
                Image(
                    painter = painterResource(id = R.drawable.eleccion_dificil), // Tu imagen aquí
                    contentDescription = "Imagen inferior",
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { alpha = alphaValue } // Animar la opacidad
                        .animateContentSize(), // Añadir animación de contenido
                    contentScale = ContentScale.Crop // Ajustar la imagen al tamaño del contenedor
                )
                // Texto en el centro de la imagen inferior
                Text(
                    text = if (isImageClicked) preguntas[INDICE].respuesta2+"\n"+preguntas[INDICE].porcentaje2+"%" else preguntas[INDICE].respuesta2,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Texto superpuesto en el centro de la pantalla, cubriendo ambas imágenes
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center // Alinear el texto en el centro de la pantalla
        ) {
            Text(
                text = preguntas[INDICE].question,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black // Color negro para el texto
            )
        }

        // Filtro oscuro que aparece tras pulsar las imágenes
        if (isImageClicked) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f)) // Fondo negro semi-transparente
            )
        }

        // Botón y nuevo texto que aparecen tras pulsar las imágenes
        if (isImageClicked) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.Bottom, // Alinea el botón en la parte inferior
                horizontalAlignment = Alignment.CenterHorizontally // Centra el botón horizontalmente
            ) {
                Button(
                    onClick = { navController.navigate("second_screen") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White, // Fondo blanco del botón
                        contentColor = Color.Gray // Letras grises del botón
                    )
                ) {
                    Text(text = "Botón Inferior")
                }
            }
        }

        // Botón de flecha hacia atrás en la parte superior izquierda
        IconButton(
            onClick = { navController.navigate("main_screen") }, // Volver a la pantalla anterior
            modifier = Modifier
                .align(Alignment.TopStart) // Alinea el botón en la esquina superior izquierda
                .padding(16.dp) // Margen para que no esté pegado al borde
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Flecha prediseñada
                contentDescription = "Volver",
                tint = Color.DarkGray // Color de la flecha
            )
        }
    }
}
