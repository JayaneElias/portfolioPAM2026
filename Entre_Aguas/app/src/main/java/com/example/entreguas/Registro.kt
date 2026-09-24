package com.example.entreaguas

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar

// =====================================================
// CORES
// =====================================================

private val BegeFundo = Color(0xFFEDE8D0)
private val BegeCampo = Color(0xFFFFFDF3)

private val Marrom = Color(0xFF6E632E)
private val MarromEscuro = Color(0xFF514824)

private val Dourado = Color(0xFFD2B48C)
private val DouradoClaro = Color(0xFFE0C59F)

private val AzulNuvem = Color(0xFFABBEED)
private val AzulNuvemClara = Color(0xFFD9E3FA)

private val LinhaCaderno = Color(0xFFD8CFAE)


// =====================================================
// TELA DE REGISTRO
// =====================================================

@Composable
fun Registro(
    registroParaEditar: RegistroExemplo? = null,
    onSalvar: () -> Unit,
    onVoltar: () -> Unit
) {

    val context = LocalContext.current

    val db = FirebaseFirestore.getInstance()
    val usuario = FirebaseAuth.getInstance().currentUser

    val estaEditando = registroParaEditar != null


    // =================================================
    // CAMPOS
    // =================================================

    var titulo by remember(registroParaEditar) {
        mutableStateOf(
            registroParaEditar?.titulo ?: ""
        )
    }

    var data by remember(registroParaEditar) {

        mutableStateOf(

            if (registroParaEditar != null) {

                val mesNumero = when (
                    registroParaEditar.mes.uppercase()
                ) {

                    "JAN" -> "01"
                    "FEV" -> "02"
                    "MAR" -> "03"
                    "ABR" -> "04"
                    "MAI" -> "05"
                    "JUN" -> "06"
                    "JUL" -> "07"
                    "AGO" -> "08"
                    "SET" -> "09"
                    "OUT" -> "10"
                    "NOV" -> "11"
                    "DEZ" -> "12"

                    else -> registroParaEditar.mes
                }

                "${registroParaEditar.dia}/$mesNumero/${registroParaEditar.ano}"

            } else {
                ""
            }
        )
    }

    var local by remember(registroParaEditar) {
        mutableStateOf(
            registroParaEditar?.local ?: ""
        )
    }

    var rota by remember(registroParaEditar) {
        mutableStateOf(
            registroParaEditar?.rota ?: ""
        )
    }

    var paradas by remember(registroParaEditar) {
        mutableStateOf(
            registroParaEditar?.paradas ?: ""
        )
    }

    var observacoes by remember(registroParaEditar) {
        mutableStateOf(
            registroParaEditar?.observacoes ?: ""
        )
    }

    var estrelas by remember(registroParaEditar) {
        mutableIntStateOf(
            registroParaEditar?.estrelas ?: 0
        )
    }


    // =================================================
    // FUNDO
    // =================================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BegeFundo)
    ) {

        DecoracaoRegistro()

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // =================================================
            // TOPO
            // =================================================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 18.dp,
                        end = 18.dp,
                        top = 14.dp,
                        bottom = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .width(48.dp)
                        .height(42.dp)
                        .clip(
                            RoundedCornerShape(12.dp)
                        )
                        .background(Dourado)
                        .border(
                            width = 1.dp,
                            color = Marrom,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            onVoltar()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "<",
                        color = MarromEscuro,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )
                }


                Spacer(
                    modifier = Modifier.width(22.dp)
                )


                Column {

                    Text(
                        text = if (estaEditando) {
                            "Editar registro"
                        } else {
                            "Novo registro"
                        },

                        color = MarromEscuro,

                        fontSize = 24.sp,

                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(2.dp)
                    )

                    Text(
                        text = if (estaEditando) {
                            "Altere os detalhes dessa lembrança"
                        } else {
                            "Guarde uma nova lembrança da viagem"
                        },

                        color = Marrom,

                        fontSize = 12.sp
                    )
                }
            }


            // =================================================
            // DIVISÓRIA
            // =================================================

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 30.dp,
                        vertical = 4.dp
                    ),

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Dourado)
                )

                Text(
                    text = "✦",

                    color = Dourado,

                    fontSize = 18.sp,

                    modifier = Modifier.padding(
                        horizontal = 12.dp
                    )
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(1.dp)
                        .background(Dourado)
                )
            }


            // =================================================
            // FORMULÁRIO
            // =================================================

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 10.dp
                    )
                    .clip(
                        RoundedCornerShape(18.dp)
                    )
                    .background(BegeCampo)
                    .border(
                        width = 1.dp,
                        color = Dourado,
                        shape = RoundedCornerShape(18.dp)
                    )
            ) {

                LinhasCaderno(
                    modifier = Modifier.matchParentSize()
                )


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 18.dp,
                            vertical = 18.dp
                        ),

                    verticalArrangement =
                        Arrangement.spacedBy(14.dp)
                ) {

                    // =================================================
                    // TÍTULO
                    // =================================================

                    item {

                        CampoTexto(
                            valor = titulo,

                            aoAlterar = {
                                titulo = it
                            },

                            label = "Título da experiência",

                            placeholder =
                                "Ex.: Passeio pelo Rio Paraná"
                        )
                    }


                    // =================================================
                    // DATA
                    // =================================================

                    item {

                        CampoData(
                            valor = data,

                            aoSelecionar = {
                                data = it
                            }
                        )
                    }


                    // =================================================
                    // LOCAL
                    // =================================================

                    item {

                        CampoTexto(
                            valor = local,

                            aoAlterar = {
                                local = it
                            },

                            label = "Rio ou local",

                            placeholder =
                                "Ex.: Rio Paraná"
                        )
                    }


                    // =================================================
                    // ROTA
                    // =================================================

                    item {

                        CampoTextoGrande(
                            valor = rota,

                            aoAlterar = {
                                rota = it
                            },

                            label = "Rota percorrida",

                            placeholder =
                                "Descreva o caminho percorrido..."
                        )
                    }


                    // =================================================
                    // PARADAS
                    // =================================================

                    item {

                        CampoTextoGrande(
                            valor = paradas,

                            aoAlterar = {
                                paradas = it
                            },

                            label = "Paradas",

                            placeholder =
                                "Registre os lugares onde parou..."
                        )
                    }


                    // =================================================
                    // OBSERVAÇÕES
                    // =================================================

                    item {

                        CampoTextoGrande(
                            valor = observacoes,

                            aoAlterar = {
                                observacoes = it
                            },

                            label =
                                "Observações da natureza",

                            placeholder =
                                "O que você observou durante o passeio?"
                        )
                    }


                    // =================================================
                    // AVALIAÇÃO
                    // =================================================

                    item {

                        Column(
                            modifier =
                                Modifier.fillMaxWidth()
                        ) {

                            Text(
                                text =
                                    "Como foi essa experiência?",

                                color =
                                    MarromEscuro,

                                fontSize =
                                    16.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )


                            Spacer(
                                modifier =
                                    Modifier.height(4.dp)
                            )


                            Text(
                                text =
                                    "Escolha de 1 a 5 estrelas",

                                color =
                                    Marrom,

                                fontSize =
                                    13.sp
                            )


                            Spacer(
                                modifier =
                                    Modifier.height(7.dp)
                            )


                            Row(
                                modifier =
                                    Modifier.fillMaxWidth(),

                                horizontalArrangement =
                                    Arrangement.Center
                            ) {

                                repeat(5) { indice ->

                                    Text(

                                        text =
                                            if (
                                                indice < estrelas
                                            ) {
                                                "★"
                                            } else {
                                                "☆"
                                            },

                                        color =
                                            Dourado,

                                        fontSize =
                                            36.sp,

                                        modifier = Modifier
                                            .padding(
                                                horizontal = 3.dp
                                            )
                                            .clickable {

                                                estrelas =
                                                    indice + 1
                                            }
                                    )
                                }
                            }
                        }
                    }


                    // =================================================
                    // SALVAR / ATUALIZAR
                    // =================================================

                    item {

                        Spacer(
                            modifier =
                                Modifier.height(4.dp)
                        )


                        Button(

                            onClick = {

                                // -------------------------
                                // VALIDAÇÃO
                                // -------------------------

                                if (
                                    titulo.isBlank() ||
                                    data.isBlank() ||
                                    local.isBlank()
                                ) {

                                    Toast.makeText(
                                        context,

                                        "Preencha título, data e local.",

                                        Toast.LENGTH_SHORT
                                    ).show()

                                    return@Button
                                }


                                if (usuario == null) {

                                    Toast.makeText(
                                        context,

                                        "Usuário não está conectado.",

                                        Toast.LENGTH_SHORT
                                    ).show()

                                    return@Button
                                }


                                // -------------------------
                                // DADOS
                                // -------------------------

                                val registro: Map<String, Any> =
                                    mapOf(

                                        "titulo" to
                                                titulo.trim(),

                                        "data" to
                                                data.trim(),

                                        "local" to
                                                local.trim(),

                                        "rota" to
                                                rota.trim(),

                                        "paradas" to
                                                paradas.trim(),

                                        "observacoes" to
                                                observacoes.trim(),

                                        "estrelas" to
                                                estrelas,

                                        "usuarioId" to
                                                usuario.uid
                                    )


                                // -------------------------
                                // EDITAR
                                // -------------------------

                                if (estaEditando) {

                                    db.collection(
                                        "registros"
                                    )
                                        .document(
                                            registroParaEditar!!.id
                                        )
                                        .update(
                                            registro
                                        )
                                        .addOnSuccessListener {

                                            Toast.makeText(
                                                context,

                                                "Registro atualizado com sucesso!",

                                                Toast.LENGTH_SHORT
                                            ).show()

                                            onSalvar()
                                        }
                                        .addOnFailureListener { erro ->

                                            Toast.makeText(
                                                context,

                                                "Erro ao atualizar: ${erro.message}",

                                                Toast.LENGTH_LONG
                                            ).show()
                                        }

                                }

                                // -------------------------
                                // NOVO REGISTRO
                                // -------------------------

                                else {

                                    db.collection(
                                        "registros"
                                    )
                                        .add(
                                            registro
                                        )
                                        .addOnSuccessListener {

                                            Toast.makeText(
                                                context,

                                                "Registro salvo com sucesso!",

                                                Toast.LENGTH_SHORT
                                            ).show()

                                            onSalvar()
                                        }
                                        .addOnFailureListener { erro ->

                                            Toast.makeText(
                                                context,

                                                "Erro ao salvar: ${erro.message}",

                                                Toast.LENGTH_LONG
                                            ).show()
                                        }
                                }
                            },


                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp),


                            shape =
                                RoundedCornerShape(14.dp),


                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        Marrom
                                )
                        ) {

                            Text(

                                text =
                                    if (estaEditando) {
                                        "Atualizar registro"
                                    } else {
                                        "Salvar registro"
                                    },

                                color =
                                    BegeCampo,

                                fontSize =
                                    16.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }


                        Spacer(
                            modifier =
                                Modifier.height(25.dp)
                        )
                    }
                }
            }
        }
    }
}


// =====================================================
// CAMPO DE DATA
// =====================================================

@Composable
fun CampoData(
    valor: String,
    aoSelecionar: (String) -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Text(
            text = "Data",

            color = MarromEscuro,

            fontSize = 15.sp,

            fontWeight = FontWeight.Bold
        )


        Spacer(
            modifier =
                Modifier.height(5.dp)
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                )
                .background(
                    BegeCampo.copy(
                        alpha = 0.82f
                    )
                )
                .border(
                    width = 1.dp,

                    color = Dourado,

                    shape =
                        RoundedCornerShape(10.dp)
                )
                .clickable {

                    val calendario =
                        Calendar.getInstance()


                    // Se já existe uma data,
                    // abre o calendário nela.

                    val partes =
                        valor.split("/")


                    val ano =
                        partes
                            .getOrNull(2)
                            ?.toIntOrNull()
                            ?: calendario.get(
                                Calendar.YEAR
                            )


                    val mes =
                        partes
                            .getOrNull(1)
                            ?.toIntOrNull()
                            ?.minus(1)
                            ?: calendario.get(
                                Calendar.MONTH
                            )


                    val dia =
                        partes
                            .getOrNull(0)
                            ?.toIntOrNull()
                            ?: calendario.get(
                                Calendar.DAY_OF_MONTH
                            )


                    val datePicker =
                        DatePickerDialog(

                            context,

                            { _, anoSelecionado,
                              mesSelecionado,
                              diaSelecionado ->

                                val dataFormatada =
                                    String.format(
                                        "%02d/%02d/%04d",
                                        diaSelecionado,
                                        mesSelecionado + 1,
                                        anoSelecionado
                                    )

                                aoSelecionar(
                                    dataFormatada
                                )
                            },

                            ano,
                            mes,
                            dia
                        )


                    datePicker.show()
                },

            contentAlignment =
                Alignment.CenterStart
        ) {

            Text(
                text =
                    if (valor.isBlank()) {
                        "Selecione uma data"
                    } else {
                        valor
                    },

                modifier =
                    Modifier.padding(
                        horizontal = 16.dp
                    ),

                color =
                    if (valor.isBlank()) {
                        Marrom.copy(
                            alpha = 0.55f
                        )
                    } else {
                        MarromEscuro
                    },

                fontSize = 16.sp
            )
        }
    }
}


// =====================================================
// DECORAÇÃO
// =====================================================

@Composable
private fun DecoracaoRegistro() {

    Box(
        modifier =
            Modifier.fillMaxSize()
    ) {

        Text(
            text = "✦",

            color = Dourado,

            fontSize = 22.sp,

            modifier =
                Modifier.padding(
                    start = 30.dp,
                    top = 8.dp
                )
        )


        Text(
            text = "✧",

            color = Marrom,

            fontSize = 20.sp,

            modifier =
                Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(
                        end = 38.dp,
                        top = 18.dp
                    )
        )


        Text(
            text = "·",

            color = Dourado,

            fontSize = 28.sp,

            modifier =
                Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(
                        end = 78.dp,
                        top = 6.dp
                    )
        )


        Nuvem(
            modifier =
                Modifier
                    .align(
                        Alignment.TopStart
                    )
                    .padding(
                        start = 70.dp,
                        top = 4.dp
                    )
        )


        Nuvem(
            modifier =
                Modifier
                    .align(
                        Alignment.TopEnd
                    )
                    .padding(
                        end = 10.dp,
                        top = 42.dp
                    )
        )
    }
}


// =====================================================
// NUVEM
// =====================================================

@Composable
private fun Nuvem(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier =
            modifier
                .width(58.dp)
                .height(24.dp)
    ) {

        val path =
            Path().apply {

                moveTo(
                    5f,
                    size.height * 0.75f
                )


                cubicTo(
                    5f,
                    size.height * 0.45f,

                    size.width * 0.25f,
                    size.height * 0.45f,

                    size.width * 0.32f,
                    size.height * 0.28f
                )


                cubicTo(
                    size.width * 0.40f,
                    -2f,

                    size.width * 0.62f,
                    0f,

                    size.width * 0.66f,
                    size.height * 0.32f
                )


                cubicTo(
                    size.width * 0.78f,
                    size.height * 0.18f,

                    size.width * 0.96f,
                    size.height * 0.40f,

                    size.width * 0.92f,
                    size.height * 0.70f
                )


                lineTo(
                    size.width * 0.92f,
                    size.height * 0.82f
                )


                lineTo(
                    5f,
                    size.height * 0.82f
                )


                close()
            }


        drawPath(
            path = path,

            color =
                AzulNuvemClara
        )
    }
}


// =====================================================
// LINHAS DE CADERNO
// =====================================================

@Composable
private fun LinhasCaderno(
    modifier: Modifier = Modifier
) {

    Canvas(
        modifier = modifier
    ) {

        val espacamento =
            48.dp.toPx()

        var y =
            42.dp.toPx()


        while (
            y < size.height
        ) {

            drawLine(

                color =
                    LinhaCaderno,

                start =
                    androidx.compose.ui.geometry.Offset(
                        x = 0f,
                        y = y
                    ),

                end =
                    androidx.compose.ui.geometry.Offset(
                        x = size.width,
                        y = y
                    ),

                strokeWidth =
                    1.dp.toPx()
            )

            y += espacamento
        }
    }
}


// =====================================================
// CAMPO DE TEXTO
// =====================================================

@Composable
fun CampoTexto(
    valor: String,
    aoAlterar: (String) -> Unit,
    label: String,
    placeholder: String
) {

    Column(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Text(
            text = label,

            color =
                MarromEscuro,

            fontSize =
                15.sp,

            fontWeight =
                FontWeight.Bold
        )


        Spacer(
            modifier =
                Modifier.height(5.dp)
        )


        OutlinedTextField(

            value = valor,

            onValueChange =
                aoAlterar,

            modifier =
                Modifier.fillMaxWidth(),

            placeholder = {

                Text(
                    text = placeholder,

                    color =
                        Marrom.copy(
                            alpha = 0.55f
                        )
                )
            },

            singleLine = true,

            shape =
                RoundedCornerShape(10.dp),

            colors =
                OutlinedTextFieldDefaults.colors(

                    focusedContainerColor =
                        BegeCampo.copy(
                            alpha = 0.82f
                        ),

                    unfocusedContainerColor =
                        BegeCampo.copy(
                            alpha = 0.82f
                        ),

                    disabledContainerColor =
                        BegeCampo.copy(
                            alpha = 0.82f
                        ),

                    focusedBorderColor =
                        Marrom,

                    unfocusedBorderColor =
                        Dourado,

                    focusedTextColor =
                        MarromEscuro,

                    unfocusedTextColor =
                        MarromEscuro,

                    focusedPlaceholderColor =
                        Marrom.copy(
                            alpha = 0.55f
                        ),

                    unfocusedPlaceholderColor =
                        Marrom.copy(
                            alpha = 0.55f
                        )
                )
        )
    }
}


// =====================================================
// CAMPO DE TEXTO GRANDE
// =====================================================

@Composable
fun CampoTextoGrande(
    valor: String,
    aoAlterar: (String) -> Unit,
    label: String,
    placeholder: String
) {

    Column(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Text(
            text = label,

            color =
                MarromEscuro,

            fontSize =
                15.sp,

            fontWeight =
                FontWeight.Bold
        )


        Spacer(
            modifier =
                Modifier.height(5.dp)
        )


        OutlinedTextField(

            value = valor,

            onValueChange =
                aoAlterar,

            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(115.dp),

            placeholder = {

                Text(
                    text = placeholder,

                    color =
                        Marrom.copy(
                            alpha = 0.55f
                        )
                )
            },

            shape =
                RoundedCornerShape(10.dp),

            colors =
                OutlinedTextFieldDefaults.colors(

                    focusedContainerColor =
                        BegeCampo.copy(
                            alpha = 0.82f
                        ),

                    unfocusedContainerColor =
                        BegeCampo.copy(
                            alpha = 0.82f
                        ),

                    disabledContainerColor =
                        BegeCampo.copy(
                            alpha = 0.82f
                        ),

                    focusedBorderColor =
                        Marrom,

                    unfocusedBorderColor =
                        Dourado,

                    focusedTextColor =
                        MarromEscuro,

                    unfocusedTextColor =
                        MarromEscuro,

                    focusedPlaceholderColor =
                        Marrom.copy(
                            alpha = 0.55f
                        ),

                    unfocusedPlaceholderColor =
                        Marrom.copy(
                            alpha = 0.55f
                        )
                )
        )
    }
}