package com.example.subly

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubscriptionScreen(
    onSave: (Subscription) -> Unit,
    onBack: () -> Unit,
    editingSub: Subscription? = null
) {

    val bgColor = Color(0xFFB5AFFF)
    val containerColor = Color(0xFF4D2B8C)
    val buttonColor = Color(0xFF8CC7FF)
    val buttonTextColor = Color(0xFF514B8C)
    val fieldColor = Color(0xFFECF1C2)

    // CAMPOS PREENCHIDOS AUTOMATICAMENTE AO EDITAR
    var nome by remember(editingSub) {
        mutableStateOf(editingSub?.nome ?: "")
    }

    var valor by remember(editingSub) {
        mutableStateOf(
            editingSub?.valor?.toString() ?: ""
        )
    }

    var tipo by remember(editingSub) {
        mutableStateOf(
            editingSub?.tipo ?: "Mensal"
        )
    }

    var data by remember(editingSub) {
        mutableStateOf(
            editingSub?.dataCobranca ?: ""
        )
    }

    var expanded by remember {
        mutableStateOf(false)
    }

    val tiposPlano = listOf(
        "Mensal",
        "Trimestral",
        "Semestral",
        "Anual"
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
                .heightIn(min = 420.dp, max = 550.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(containerColor)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.subly),
                contentDescription = "Logo Subly",
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = if (editingSub == null)
                    "Nova Assinatura"
                else
                    "Editar Assinatura",

                fontWeight = FontWeight.ExtraBold,
                fontStyle = FontStyle.Italic,
                color = Color(0xFF8CC7FF),
                fontSize = 26.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                placeholder = { Text("Nome da assinatura") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = valor,
                onValueChange = {
                    if (it.matches(Regex("^[0-9]*\\.?[0-9]{0,2}$"))) {
                        valor = it
                    }
                },
                placeholder = { Text("Valor (ex: 29.90)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                placeholder = { Text("Data cobrança (dd/mm)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = fieldColor,
                    unfocusedContainerColor = fieldColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {

                OutlinedTextField(
                    value = tipo,
                    onValueChange = {},
                    readOnly = true,
                    placeholder = {
                        Text("Tipo de plano")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults
                            .TrailingIcon(expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = fieldColor,
                        unfocusedContainerColor = fieldColor,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier = Modifier.background(
                        color = Color(0xFFE3F2FD),
                        shape = RoundedCornerShape(14.dp)
                    )
                ) {

                    tiposPlano.forEach { option ->

                        DropdownMenuItem(
                            text = {
                                Text(
                                    option,
                                    fontSize = 14.sp,
                                    color = Color(0xFF1C1C1C)
                                )
                            },
                            onClick = {
                                tipo = option
                                expanded = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    onClick = {

                        onSave(
                            Subscription(
                                nome = nome,
                                valor = valor.toDoubleOrNull() ?: 0.0,
                                tipo = tipo,
                                dataCobranca = data
                            )
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Salvar",
                        color = buttonTextColor
                    )
                }

                Button(
                    onClick = onBack,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor
                    ),
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Voltar",
                        color = buttonTextColor
                    )
                }
            }
        }
    }
}