package com.example.subly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.subly.ui.theme.SublyTheme
import java.util.UUID

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SublyTheme {
                SublyApp()
            }
        }
    }
}

@Composable
fun SublyApp() {

    // 👇 AGORA A PRIMEIRA TELA É A WELCOME
    var screen by remember { mutableStateOf("welcome") }

    val usuarios = remember { mutableStateListOf<Usuario>() }
    val subscriptions = remember { mutableStateListOf<Subscription>() }

    var selectedSub by remember {
        mutableStateOf<Subscription?>(null)
    }

    when (screen) {

        // 🟣 WELCOME (NOVA TELA INICIAL)
        "welcome" -> WelcomeScreen(
            onLogin = {
                screen = "login"
            },
            onCadastro = {
                screen = "cadastro"
            }
        )

        // 🔐 LOGIN
        "login" -> LoginScreen(

            onLogin = { email, senha ->

                val usuarioExiste = usuarios.any {
                    it.email == email && it.senha == senha
                }

                if (usuarioExiste) {
                    screen = "home"
                }
            },

            onIrCadastro = {
                screen = "cadastro"
            }
        )

        // 📝 CADASTRO
        "cadastro" -> CadastroScreen(

            onCadastrar = { usuario ->
                usuarios.add(usuario)
                screen = "login"
            },

            onIrLogin = {
                screen = "login"
            }
        )

        // 🏠 HOME
        "home" -> HomeScreen(

            subscriptions = subscriptions,

            onAddClick = {
                selectedSub = null
                screen = "add"
            },

            onProjectionClick = {
                screen = "projection"
            },

            onDelete = { sub ->
                subscriptions.removeIf { it.id == sub.id }
            },

            onEdit = { sub ->
                selectedSub = sub
                screen = "add"
            }
        )

        // ➕ ADD / EDIT
        "add" -> AddSubscriptionScreen(

            editingSub = selectedSub,

            onSave = { sub ->

                val updated = sub.copy(
                    id = selectedSub?.id ?: UUID.randomUUID().toString()
                )

                val index = subscriptions.indexOfFirst {
                    it.id == selectedSub?.id
                }

                if (index != -1) {
                    subscriptions[index] = updated
                } else {
                    subscriptions.add(updated)
                }

                selectedSub = null
                screen = "home"
            },

            onBack = {
                selectedSub = null
                screen = "home"
            }
        )

        // 📊 PROJECTION
        "projection" -> ProjectionScreen(

            subscriptions = subscriptions,

            onBack = {
                screen = "home"
            },

            onDelete = { sub ->
                subscriptions.removeIf { it.id == sub.id }
            },

            onEdit = { sub ->
                selectedSub = sub
                screen = "add"
            }
        )
    }
}