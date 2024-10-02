package com.igndl.juegodecisiones
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.igndl.juegodecisiones.db.Pregunta
import com.igndl.juegodecisiones.db.dbHelper
import com.igndl.juegodecisiones.db.rellenadorDB


var BDINICIALIZADA : Boolean = false

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var dbHelper = dbHelper(this)
        val db = dbHelper.writableDatabase
        val rellenador = rellenadorDB(dbHelper)
        if(!BDINICIALIZADA) {
            rellenador.rellenarDB(db, 1, 1)
            BDINICIALIZADA = true
        }
        setContent {
                // Crear controlador de navegación
                val navController = rememberNavController()

                // Configurar el host de navegación
                NavHost(navController = navController, startDestination = "main_screen") {
                    // Pantalla principal con botones
                    composable("main_screen") {
                        MainScreen(navController = navController)
                    }

                    // Pantalla secundaria con colores divididos
                    composable("second_screen") {
                        var listaPreguntas: MutableList<Pregunta> = ArrayList()
                        listaPreguntas = dbHelper!!.getPreguntasPorTipo("moral")
                        //listaPreguntas.add(Pregunta(23,"pregunta de prueba","moral","respuesta1","respuesta2",32,21))
                        SecondScreen(navController = navController, listaPreguntas)
                    }
                }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Imagen de fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_blanco_nubes), // Coloca aquí el ID de tu imagen
            contentDescription = null, // No necesita descripción porque es decorativa
            contentScale = ContentScale.Crop, // Ajusta la imagen para llenar el fondo
            modifier = Modifier.fillMaxSize() // La imagen ocupa toda la pantalla
        )
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Modos de juego",
                fontSize = 28.sp, // Tamaño de la fuente grande
                fontWeight = FontWeight.ExtraBold, // Peso del texto grueso
                letterSpacing = 1.5.sp, // Espaciado entre letras
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    shadow = Shadow(
                        color = Color.Gray, // Color de la sombra
                        offset = Offset(4f, 4f), // Posición de la sombra (horizontal, vertical)
                        blurRadius = 8f // Radio de difuminado
                    )
                ),
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )

            // Imagen 1
            Image(
                painter = painterResource(id = R.drawable.eleccion_dificil), // Referencia a la imagen en drawable
                contentDescription = "Elección Difícil",
                modifier = Modifier
                    .width(350.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)), // Bordes redondeados
                contentScale = ContentScale.Crop
            )

            // Botón 1 para navegar a la segunda pantalla
            Button(
                onClick = { navController.navigate("second_screen") },
                modifier = Modifier
                    .padding(bottom = 16.dp) // Espacio por debajo del botón
                    .fillMaxWidth(0.87f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFb8b8b8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Decisiones difíciles",
                    fontSize = 18.sp,
                    style = TextStyle(
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.White, // Color de la sombra
                            offset = Offset(2f, 2f), // Posición de la sombra (horizontal, vertical)
                            blurRadius = 4f // Radio de difuminado
                        )
                    ))
            }

            // Imagen 2
            Image(
                painter = painterResource(id = R.drawable.mono), // Referencia a la imagen en drawable
                contentDescription = "Elección Difícil",
                modifier = Modifier
                    .width(350.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            // Botón 2 también navega a la segunda pantalla
            Button(
                onClick = { navController.navigate("second_screen") },
                modifier = Modifier
                    .padding(bottom = 16.dp) // Espacio por debajo del botón
                    .fillMaxWidth(0.87f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFb8b8b8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Decisiones absurdas",
                    fontSize = 18.sp,
                    style = TextStyle(
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.White, // Color de la sombra
                            offset = Offset(2f, 2f), // Posición de la sombra (horizontal, vertical)
                            blurRadius = 4f // Radio de difuminado
                        )
                    ))
            }
            // Imagen 3
            Image(
                painter = painterResource(id = R.drawable.sociedad), // Referencia a la imagen en drawable
                contentDescription = "Decisiones variadas",
                modifier = Modifier
                    .width(350.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            // Botón 3 también navega a la segunda pantalla
            Button(
                onClick = { navController.navigate("second_screen") },
                modifier = Modifier
                    .padding(bottom = 16.dp) // Espacio por debajo del botón
                    .fillMaxWidth(0.87f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFb8b8b8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Decisiones variadas",
                    fontSize = 18.sp,
                    style = TextStyle(
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.White, // Color de la sombra
                            offset = Offset(2f, 2f), // Posición de la sombra (horizontal, vertical)
                            blurRadius = 4f // Radio de difuminado
                        )
                    ))
            }

            // Imagen 4
            Image(
                painter = painterResource(id = R.drawable.coming_soon), // Referencia a la imagen en drawable
                contentDescription = "Decisiones variadas",
                modifier = Modifier
                    .width(350.dp)
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )

            // Botón 4 también navega a la segunda pantalla
            Button(
                onClick = { },
                modifier = Modifier
                    .padding(bottom = 16.dp) // Espacio por debajo del botón
                    .fillMaxWidth(0.87f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFb8b8b8),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Más proximamente",
                    fontSize = 18.sp,
                    style = TextStyle(
                        color = Color.White,
                        shadow = Shadow(
                            color = Color.White, // Color de la sombra
                            offset = Offset(2f, 2f), // Posición de la sombra (horizontal, vertical)
                            blurRadius = 4f // Radio de difuminado
                        )
                    ))
            }
        }
    }
}

