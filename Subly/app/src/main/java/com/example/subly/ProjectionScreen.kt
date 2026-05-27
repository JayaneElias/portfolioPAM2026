package com.example.subly

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import kotlin.math.absoluteValue

@Composable
fun ProjectionScreen(
    subscriptions: List<Subscription>,
    onBack: () -> Unit,
    onDelete: (Subscription) -> Unit,
    onEdit: (Subscription) -> Unit
) {

    val bgColor = Color(0xFFB5AFFF)
    val containerColor = Color(0xFF4D2B8C)
    val buttonColor = Color(0xFF8CC7FF)
    val buttonTextColor = Color(0xFF514B8C)

    var mode by remember { mutableStateOf<String?>(null) }

    val rainbowColors = listOf(
        Color(0xFFE57373),
        Color(0xFFFFB74D),
        Color(0xFFFFF176),
        Color(0xFF81C784),
        Color(0xFF64B5F6),
        Color(0xFFBA68C8),
        Color(0xFFFF96B6)
    )

    val total = when (mode) {
        "Mensal" -> subscriptions.sumOf {
            when (it.tipo) {
                "Mensal" -> it.valor
                "Trimestral" -> it.valor / 3
                "Semestral" -> it.valor / 6
                "Anual" -> it.valor / 12
                else -> 0.0
            }
        }

        "Trimestral" -> subscriptions.sumOf {
            when (it.tipo) {
                "Mensal" -> it.valor * 3
                "Trimestral" -> it.valor
                "Semestral" -> it.valor / 2
                "Anual" -> it.valor / 4
                else -> 0.0
            }
        }

        "Semestral" -> subscriptions.sumOf {
            when (it.tipo) {
                "Mensal" -> it.valor * 6
                "Trimestral" -> it.valor * 2
                "Semestral" -> it.valor
                "Anual" -> it.valor / 2
                else -> 0.0
            }
        }

        "Anual" -> subscriptions.sumOf {
            when (it.tipo) {
                "Mensal" -> it.valor * 12
                "Trimestral" -> it.valor * 4
                "Semestral" -> it.valor * 2
                "Anual" -> it.valor
                else -> 0.0
            }
        }

        else -> 0.0
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.9f)
                .clip(RoundedCornerShape(20.dp))
                .background(containerColor)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.subly),
                contentDescription = "Logo Subly",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Projeção de Gastos",
                color = Color(0xFFEB507F),
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                listOf("Mensal", "Trimestral", "Semestral", "Anual").forEach { m ->

                    val selecionado = mode == m

                    Button(
                        onClick = { mode = m },
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (selecionado)
                                    Color(0xFFB3E5FC)
                                else
                                    buttonColor
                        ),
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(4.dp)
                    ) {

                        Text(
                            text = m,
                            fontSize = 13.sp,
                            color =
                                if (selecionado)
                                    Color(0xFF1B3A57)
                                else
                                    buttonTextColor,
                            fontWeight =
                                if (selecionado)
                                    FontWeight.Bold
                                else
                                    FontWeight.Normal
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Total: R$ %.2f".format(total),
                fontSize = 20.sp,
                color = Color(0xFFFFE686),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF3A206B))
                    .padding(10.dp)
            ) {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(
                        subscriptions,
                        key = { it.id }
                    ) { sub ->

                        val color = rainbowColors[
                            sub.id.hashCode().absoluteValue % rainbowColors.size
                        ]

                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = color
                            ),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Column(
                                modifier = Modifier.padding(12.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Text(
                                        text = sub.nome,
                                        color = Color.Black,
                                        fontSize = 22.sp,
                                        style = MaterialTheme.typography.titleMedium,
                                        modifier = Modifier.weight(1f)
                                    )

                                    Row {

                                        IconButton(onClick = { onEdit(sub) }) {
                                            Icon(
                                                imageVector = Icons.Default.Edit,
                                                contentDescription = "Editar",
                                                tint = Color.Black
                                            )
                                        }

                                        IconButton(onClick = { onDelete(sub) }) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Remover",
                                                tint = Color.Black
                                            )
                                        }
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))

                                Text(
                                    text = "Plano: ${sub.tipo}",
                                    color = Color.DarkGray
                                )

                                Text(
                                    text = "Valor: R$ %.2f".format(sub.valor),
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onBack,
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Voltar",
                    color = buttonTextColor
                )
            }
        }
    }
}