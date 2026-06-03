package com.example.subly

data class Usuario(
    val id: String = java.util.UUID.randomUUID().toString(),
    val nome: String,
    val email: String,
    val senha: String
)