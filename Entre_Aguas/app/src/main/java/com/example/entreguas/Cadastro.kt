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
import com.google.firebase.auth.UserProfileChangeRequest

@Composable
fun Cadastro(
    onCadastroConcluido: () -> Unit,
    onVoltar: () -> Unit
) {

    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()

    var nome by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    var confirmarSenha by remember {
        mutableStateOf("")
    }

    var mostrarSenha by remember {
        mutableStateOf(false)
    }

    var mostrarConfirmacao by remember {
        mutableStateOf(false)
    }

    // =====================================================
    // CORES
    // =====================================================

    val fundo = Color(0xFFEDE8D0)
    val creme = Color(0xFFF5F5DC)
    val trigo = Color(0xFFF5DEB3)
    val dourado = Color(0xFFD2B48C)

    val marrom = Color(0xFF6E632E)
    val marromEscuro = Color(0xFF514824)

    // Azul um pouco mais forte
    val azul = Color(0xFF8FA9D9)
    val azulClaro = Color(0xFFABBEED)

    // Laranjinha vintage
    val laranja = Color(0xFFD99A68)
    val laranjaClaro = Color(0xFFE7B98F)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fundo)
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 8.dp,
                    top = 58.dp
                )
                .size(
                    width = 145.dp,
                    height = 65.dp
                )
        ) {


            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .size(
                        width = 125.dp,
                        height = 32.dp
                    )
                    .clip(RoundedCornerShape(30.dp))
                    .background(azul.copy(alpha = 0.45f))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 15.dp)
                    .size(38.dp)
                    .clip(RoundedCornerShape(50))
                    .background(azul.copy(alpha = 0.45f))
            )


            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 8.dp)
                    .size(50.dp)
                    .clip(RoundedCornerShape(50))
                    .background(azul.copy(alpha = 0.45f))
            )

            // Pequeno detalhe bege
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(end = 5.dp)
                    .size(25.dp)
                    .clip(RoundedCornerShape(50))
                    .background(creme.copy(alpha = 0.8f))
            )
        }



        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    top = 42.dp,
                    end = 24.dp
                )
                .size(120.dp)
        ) {

            Text(
                text = "│",
                color = laranja,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
            )

            Text(
                text = "╱",
                color = laranja,
                fontSize = 17.sp,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(
                        top = 7.dp,
                        end = 14.dp
                    )
            )

            Text(
                text = "—",
                color = laranja,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )

            Text(
                text = "╲",
                color = laranja,
                fontSize = 17.sp,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(
                        bottom = 8.dp,
                        end = 14.dp
                    )
            )

            Text(
                text = "│",
                color = laranja,
                fontSize = 18.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )

            Text(
                text = "╱",
                color = laranja,
                fontSize = 17.sp,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(
                        bottom = 8.dp,
                        start = 14.dp
                    )
            )

            Text(
                text = "—",
                color = laranja,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )

            Text(
                text = "╲",
                color = laranja,
                fontSize = 17.sp,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(
                        top = 7.dp,
                        start = 14.dp
                    )
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(72.dp)
                    .clip(RoundedCornerShape(50))
                    .background(trigo)
            )
        }


        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    end = 8.dp,
                    top = 155.dp
                )
                .size(
                    width = 120.dp,
                    height = 52.dp
                )
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(
                        width = 105.dp,
                        height = 25.dp
                    )
                    .clip(RoundedCornerShape(30.dp))
                    .background(azulClaro.copy(alpha = 0.55f))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(start = 10.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(50))
                    .background(azulClaro.copy(alpha = 0.55f))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 5.dp)
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
                    .background(azulClaro.copy(alpha = 0.55f))
            )
        }


        // =====================================================
        // PEQUENA NUVEM LARANJA
        // =====================================================

        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 12.dp,
                    bottom = 82.dp
                )
                .size(
                    width = 100.dp,
                    height = 42.dp
                )
        ) {

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .size(
                        width = 90.dp,
                        height = 22.dp
                    )
                    .clip(RoundedCornerShape(30.dp))
                    .background(laranjaClaro.copy(alpha = 0.65f))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(start = 15.dp)
                    .size(30.dp)
                    .clip(RoundedCornerShape(50))
                    .background(laranjaClaro.copy(alpha = 0.65f))
            )

            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
                    .size(38.dp)
                    .clip(RoundedCornerShape(50))
                    .background(laranjaClaro.copy(alpha = 0.65f))
            )
        }

        Text(
            text = "✦",
            color = azul,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 38.dp,
                    top = 145.dp
                )
        )

        Text(
            text = "✧",
            color = laranja,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    start = 100.dp,
                    top = 190.dp
                )
        )

        Text(
            text = "✦",
            color = marrom.copy(alpha = 0.55f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(
                    end = 135.dp,
                    top = 210.dp
                )
        )

        Text(
            text = "⌁",
            color = azul,
            fontSize = 30.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 28.dp,
                    bottom = 70.dp
                )
        )

        Text(
            text = "✧",
            color = dourado,
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 75.dp,
                    bottom = 115.dp
                )
        )

        Text(
            text = "•",
            color = marrom.copy(alpha = 0.45f),
            fontSize = 22.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(
                    start = 120.dp,
                    bottom = 55.dp
                )
        )


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
                    .background(creme)
                    .padding(7.dp)
            ) {

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(23.dp))
                        .background(fundo)
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
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Venha conosco Navegar!",
                color = marrom,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Guarde cada caminho, cada parada e cada memória. Você, meu caro Viajante, é que tornará isso possível.",
                color = marrom.copy(alpha = 0.75f),
                fontSize = 13.sp
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // NOME

            OutlinedTextField(
                value = nome,
                onValueChange = {
                    nome = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Nome")
                },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = marrom,
                    unfocusedBorderColor = dourado,
                    focusedLabelColor = marrom,
                    unfocusedLabelColor = marrom,
                    cursorColor = marrom,
                    focusedTextColor = marrom,
                    unfocusedTextColor = marrom
                )
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =================================================
            // E-MAIL
            // =================================================

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
                    focusedBorderColor = marrom,
                    unfocusedBorderColor = dourado,
                    focusedLabelColor = marrom,
                    unfocusedLabelColor = marrom,
                    cursorColor = marrom,
                    focusedTextColor = marrom,
                    unfocusedTextColor = marrom
                )
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =================================================
            // SENHA
            // =================================================

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
                        color = marrom,
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
                    focusedBorderColor = marrom,
                    unfocusedBorderColor = dourado,
                    focusedLabelColor = marrom,
                    unfocusedLabelColor = marrom,
                    cursorColor = marrom,
                    focusedTextColor = marrom,
                    unfocusedTextColor = marrom
                )
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // =================================================
            // CONFIRMAR SENHA
            // =================================================

            OutlinedTextField(
                value = confirmarSenha,
                onValueChange = {
                    confirmarSenha = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Confirmar senha")
                },
                singleLine = true,
                visualTransformation = if (mostrarConfirmacao) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {

                    Text(
                        text = if (mostrarConfirmacao) {
                            "Ocultar"
                        } else {
                            "Mostrar"
                        },
                        color = marrom,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .clickable {
                                mostrarConfirmacao = !mostrarConfirmacao
                            }
                            .padding(end = 10.dp)
                    )
                },
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = marrom,
                    unfocusedBorderColor = dourado,
                    focusedLabelColor = marrom,
                    unfocusedLabelColor = marrom,
                    cursorColor = marrom,
                    focusedTextColor = marrom,
                    unfocusedTextColor = marrom
                )
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = {

                    when {

                        nome.isBlank() -> {

                            Toast.makeText(
                                context,
                                "Digite seu nome.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        email.isBlank() -> {

                            Toast.makeText(
                                context,
                                "Digite seu e-mail.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        senha.isBlank() -> {

                            Toast.makeText(
                                context,
                                "Digite uma senha.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        senha != confirmarSenha -> {

                            Toast.makeText(
                                context,
                                "As senhas não coincidem.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        else -> {

                            auth.createUserWithEmailAndPassword(
                                email.trim(),
                                senha
                            )
                                .addOnSuccessListener { resultado ->

                                    val usuario = resultado.user

                                    val perfil =
                                        UserProfileChangeRequest.Builder()
                                            .setDisplayName(nome.trim())
                                            .build()

                                    usuario
                                        ?.updateProfile(perfil)
                                        ?.addOnCompleteListener {

                                            Toast.makeText(
                                                context,
                                                "Conta criada com sucesso!",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                            auth.signOut()

                                            onCadastroConcluido()
                                        }
                                }
                                .addOnFailureListener { erro ->

                                    Toast.makeText(
                                        context,
                                        "Não foi possível criar a conta: ${erro.message}",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = marrom,
                    contentColor = creme
                )
            ) {

                Text(
                    text = "CRIAR MINHA CONTA",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // =================================================
            // VOLTAR
            // =================================================

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Já possui uma conta?",
                    color = marrom.copy(alpha = 0.8f),
                    fontSize = 13.sp
                )

                Text(
                    text = "  Entrar",
                    color = marrom,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .clickable {
                            onVoltar()
                        }
                )
            }
        }
    }
}