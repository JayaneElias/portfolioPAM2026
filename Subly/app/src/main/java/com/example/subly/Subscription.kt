package com.example.subly
data class Subscription(
    val id: String = java.util.UUID.randomUUID().toString(),
    val nome: String,
    val valor: Double,
    val tipo: String, // "Mensal" ou "Anual"
    val dataCobranca: String
)

