package com.example.subly

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
    onCadastro: () -> Unit
) {

    val bgColor = Color(0xFFB5AFFF)
    val containerColor = Color(0xFF4D2B8C)
    val buttonColor = Color(0xFF8CC7FF)
    val buttonTextColor = Color(0xFF514B8C)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .clip(RoundedCornerShape(20.dp))
                .background(containerColor)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.subly),
                contentDescription = "Logo Subly",
                modifier = Modifier.size(140.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Subly",
                fontSize = 40.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFFEB507F)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Organize suas assinaturas de forma simples e eficiente. " +
                        "Controle valores, datas e pagamentos em um só lugar.",
                fontSize = 18.sp,
                color = Color(0xFFECA0B4),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onLogin,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                )
            ) {
                Text(
                    text = "Entrar",
                    color = buttonTextColor
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(
                onClick = onCadastro,
                modifier = Modifier.fillMaxWidth(),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF8CC7FF))
                ),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF8CC7FF)
                )
            ) {
                Text("Criar conta")
            }
        }
    }
}