package com.example.app_foodreview
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.graphics.Color
import com.example.app_foodreview.ui.theme.DebugButtonColors
import com.example.app_foodreview.ui.theme.ErrorButtonColors
import com.example.app_foodreview.ui.theme.InfoButtonColors
import com.example.app_foodreview.ui.theme.WarningButtonColors
import java.lang.RuntimeException

import com.example.app_foodreview.ui.theme.App_FoodReviewTheme

const val TAG = "FoodReviewApp"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App_FoodReviewTheme {
                App()
            }
        }
    }
}


@Composable
private fun App(){
    var nome by remember { mutableStateOf("") }
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFADAEEE)
    ){
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            val image = painterResource(R.drawable.foodreview)
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop, modifier = Modifier
                    .width(140.dp)
                    .height(140.dp)
            )
            Greeting("COMIDA")
            Row(
                Modifier
                    .fillMaxWidth(),
                Arrangement.Center

            ) {
                TextField(
                    value = nome, onValueChange = { novoValor -> nome = novoValor },
                    label = { Text("Qual a Comida irá Avaliar:") },
                )
            }


            ActionButton(
                text = "I",
                buttonColors = ErrorButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.e(TAG, "App: $nome Nota I")
            }


            ActionButton(
                text = "R",
                buttonColors = WarningButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.w(TAG, "App: $nome Nota R")
            }



            ActionButton(
                text = "B",
                buttonColors = DebugButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.d(TAG, "App: $nome Nota B")
            }


            ActionButton(
                text = "MB",
                buttonColors = InfoButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.i(TAG, "App: $nome Nota MB")
            }

        }
    }
}

@Composable
fun ActionButton(
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    block: () -> Unit
){
    ElevatedButton(
        onClick = block,
        shape = RoundedCornerShape(5.dp),
        colors = buttonColors,
        modifier = modifier
    ) {
        Text(text = text)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier){
    Text(
        text = "AVALIAÇÃO DE $name",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = MaterialTheme.colorScheme.secondary
    )
}

@Preview(showBackground = true, widthDp = 150, heightDp = 200)
@Composable
fun AppPreview(){
    App_FoodReviewTheme {
        App()
    }
}

@Preview(showBackground = true, widthDp = 120)
@Composable
fun ActionButtonPreview(){
    ActionButton(text = "Cadastrar") {
    }
}

@Preview (showBackground = true)
@Composable
fun GreetingPreview() {
    App_FoodReviewTheme {
        Greeting("COMIDA")
    }
}