package com.example.entreaguas

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.entreaguas.R
import com.google.firebase.auth.FirebaseAuth

@Composable
fun Login(
    onLogin: () -> Unit,
    onCadastro: () -> Unit
) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var mostrarSenha by remember {
        mutableStateOf(false)
    }

    val fundoNoturno = Color(0xFF4B4A42)
    val bege = Color(0xFFEDE8D0)
    val creme = Color(0xFFF5F5DC)
    val dourado = Color(0xFFD2B48C)
    val marrom = Color(0xFF6E632E)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fundoNoturno)
    ) {


        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 50.dp,
                    end = 35.dp
                )
                .size(78.dp)
        ) {

            // Parte clara da lua
            Box(
                modifier = Modifier
                    .size(70.dp)
                    .clip(RoundedCornerShape(50))
                    .background(bege)
            )

            // Recorte da lua
            Box(
                modifier = Modifier
                    .padding(
                        start = 18.dp,
                        top = 2.dp
                    )
                    .size(70.dp)
                    .clip(RoundedCornerShape(50))
                    .background(fundoNoturno)
            )
        }


        // =====================================================
        // ESTRELAS
        // =====================================================

        Text(
            text = "✦",
            color = bege,
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 40.dp,
                    top = 65.dp
                )
        )

        Text(
            text = "✦",
            color = dourado,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    end = 125.dp,
                    top = 85.dp
                )
        )

        Text(
            text = "✧",
            color = bege,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    end = 45.dp,
                    top = 155.dp
                )
        )

        Text(
            text = "✦",
            color = dourado,
            fontSize = 11.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 90.dp,
                    top = 150.dp
                )
        )

        Text(
            text = "✧",
            color = bege,
            fontSize = 13.sp,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(
                    start = 22.dp,
                    bottom = 180.dp
                )
        )

        Text(
            text = "✦",
            color = dourado,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(
                    end = 24.dp,
                    bottom = 160.dp
                )
        )

        Text(
            text = "✧",
            color = bege,
            fontSize = 10.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 55.dp,
                    bottom = 75.dp
                )
        )

        Text(
            text = "✦",
            color = dourado,
            fontSize = 15.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 45.dp,
                    bottom = 85.dp
                )
        )


        // CONTEÚDO
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(184.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(bege)
                    .padding(7.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(23.dp))
                        .background(fundoNoturno)
                        .padding(3.dp)
                ) {

                    Image(
                        painter = painterResource(
                            id = R.drawable.logo_entreaguas
                        ),
                        contentDescription = "Logo Entre Águas",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "Retome a Navegação!",
                color = bege,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Entre para continuar suas aventuras, Viajantes. Guarde e relembre cada história.",
                color = dourado,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )


            // E-MAIL

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("E-mail")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = bege,
                    unfocusedBorderColor = dourado,
                    focusedLabelColor = bege,
                    unfocusedLabelColor = dourado,
                    cursorColor = bege,
                    focusedTextColor = creme,
                    unfocusedTextColor = creme
                )
            )

            Spacer(
                modifier = Modifier.height(14.dp)
            )


            // SENHA

            OutlinedTextField(
                value = senha,
                onValueChange = {
                    senha = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Senha")
                },
                singleLine = true,
                visualTransformation = if (mostrarSenha) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {

                    Text(
                        text = if (mostrarSenha) {
                            "Ocultar"
                        } else {
                            "Mostrar"
                        },
                        color = bege,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clickable {
                                mostrarSenha = !mostrarSenha
                            }
                            .padding(end = 10.dp)
                    )
                },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = bege,
                    unfocusedBorderColor = dourado,
                    focusedLabelColor = bege,
                    unfocusedLabelColor = dourado,
                    cursorColor = bege,
                    focusedTextColor = creme,
                    unfocusedTextColor = creme
                )
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Button(
                onClick = {

                    if (email.isBlank() || senha.isBlank()) {

                        Toast.makeText(
                            context,
                            "Digite seu e-mail e sua senha.",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        auth.signInWithEmailAndPassword(
                            email.trim(),
                            senha
                        )
                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Login realizado!",
                                    Toast.LENGTH_SHORT
                                ).show()

                                onLogin()
                            }
                            .addOnFailureListener {

                                Toast.makeText(
                                    context,
                                    "E-mail ou senha incorretos.",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = bege,
                    contentColor = marrom
                )
            ) {

                Text(
                    text = "ENTRAR",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // CADASTRO


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Ainda não possui uma conta?",
                    color = bege,
                    fontSize = 13.sp
                )

                Text(
                    text = "  Cadastre-se",
                    color = dourado,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .clickable {
                            onCadastro()
                        }
                )
            }
        }
    }
}