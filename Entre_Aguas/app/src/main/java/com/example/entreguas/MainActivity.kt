package com.example.entreaguas

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import com.google.firebase.firestore.FirebaseFirestore
import com.example.entreaguas.ui.theme.EntreAguasTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EntreAguasTheme {
                EntreAguasApp()
            }
        }
    }
}

@Composable
fun EntreAguasApp() {

    var telaAtual by remember {
        mutableStateOf("login")
    }

    var registroParaEditar by remember {
        mutableStateOf<RegistroExemplo?>(null)
    }

    val context = LocalContext.current
    val db = FirebaseFirestore.getInstance()

    when (telaAtual) {

        "login" -> {

            Login(
                onLogin = {
                    telaAtual = "home"
                },
                onCadastro = {
                    telaAtual = "cadastro"
                }
            )
        }

        "cadastro" -> {

            Cadastro(
                onCadastroConcluido = {
                    telaAtual = "login"
                },
                onVoltar = {
                    telaAtual = "login"
                }
            )
        }

        "home" -> {

            Home(
                onNovoRegistro = {
                    registroParaEditar = null
                    telaAtual = "registro"
                },

                onEditarRegistro = { registro ->
                    registroParaEditar = registro
                    telaAtual = "registro"
                },

                onExcluirRegistro = { registro ->

                    if (registro.id.isNotBlank()) {

                        db.collection("registros")
                            .document(registro.id)
                            .delete()
                            .addOnSuccessListener {

                                Toast.makeText(
                                    context,
                                    "Registro excluído com sucesso!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { erro ->

                                Toast.makeText(
                                    context,
                                    "Erro ao excluir: ${erro.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                    }
                },

                onSair = {
                    telaAtual = "login"
                }
            )
        }

        "registro" -> {

            Registro(
                registroParaEditar = registroParaEditar,

                onSalvar = {
                    registroParaEditar = null
                    telaAtual = "home"
                },

                onVoltar = {
                    registroParaEditar = null
                    telaAtual = "home"
                }
            )
        }
    }
}