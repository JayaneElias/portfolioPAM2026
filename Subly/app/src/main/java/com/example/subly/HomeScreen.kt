package com.example.subly

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import kotlin.math.absoluteValue

@Composable
fun HomeScreen(
    subscriptions: List<Subscription>,
    onAddClick: () -> Unit,
    onProjectionClick: () -> Unit,
    onDelete: (Subscription) -> Unit,
    onEdit: (Subscription) -> Unit
) {

    val bgColor = Color(0xFFB5AFFF)
    val containerColor = Color(0xFF4D2B8C)
    val buttonColor = Color(0xFF8CC7FF)
    val buttonTextColor = Color(0xFF514B8C)

    val total = subscriptions.sumOf { it.valor }

    val rainbowColors = listOf(
        Color(0xFFE57373),
        Color(0xFFFFB74D),
        Color(0xFFFFF176),
        Color(0xFF81C784),
        Color(0xFF64B5F6),
        Color(0xFFBA68C8),
        Color(0xFFFF96B6)
    )

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
                text = "SUBLY",
                color = Color(0xFFEB507F),
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                letterSpacing = 2.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Total mensal: R$ %.2f".format(total),
                color = Color(0xFF8CC7FF),
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFF3A206B))
                    .padding(8.dp)
            ) {

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(subscriptions, key = { it.id }) { sub ->

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
                                    color = Color.DarkGray
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(
                    onClick = onAddClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "+ Adicionar",
                        color = buttonTextColor
                    )
                }

                Button(
                    onClick = onProjectionClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Projeção",
                        color = buttonTextColor
                    )
                }
            }
        }
    }
}