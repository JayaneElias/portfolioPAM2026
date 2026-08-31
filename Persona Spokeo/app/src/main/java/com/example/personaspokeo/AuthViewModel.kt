package com.example.personaspokeo

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(auth.currentUser != null)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn.asStateFlow()

    fun signUp(
        name: String,
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        if (name.isBlank()) {
            _errorMessage.value = "Digite seu nome."
            return
        }

        if (email.isBlank()) {
            _errorMessage.value = "Digite seu e-mail."
            return
        }

        if (password.isBlank()) {
            _errorMessage.value = "Digite sua senha."
            return
        }

        if (password.length < 6) {
            _errorMessage.value = "A senha precisa ter pelo menos 6 caracteres."
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName(name)
                        .build()

                    user?.updateProfile(profileUpdates)
                        ?.addOnCompleteListener {

                            _isLoading.value = false
                            _isLoggedIn.value = true

                            onSuccess()
                        }
                        ?: run {

                            _isLoading.value = false
                            _isLoggedIn.value = true

                            onSuccess()
                        }

                } else {

                    _isLoading.value = false

                    _errorMessage.value =
                        task.exception?.message
                            ?: "Erro ao criar a conta."
                }
            }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit
    ) {
        if (email.isBlank()) {
            _errorMessage.value = "Digite seu e-mail."
            return
        }

        if (password.isBlank()) {
            _errorMessage.value = "Digite sua senha."
            return
        }

        _isLoading.value = true
        _errorMessage.value = null

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                _isLoading.value = false

                if (task.isSuccessful) {

                    _isLoggedIn.value = true
                    onSuccess()

                } else {

                    _errorMessage.value =
                        "E-mail ou senha incorretos."
                }
            }
    }

    fun logout() {
        auth.signOut()
        _isLoggedIn.value = false
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun getCurrentUserName(): String {
        return auth.currentUser?.displayName ?: "Usuário"
    }

    fun getCurrentUserEmail(): String {
        return auth.currentUser?.email ?: ""
    }
}