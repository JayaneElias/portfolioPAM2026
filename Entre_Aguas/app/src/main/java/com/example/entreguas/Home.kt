package com.example.entreaguas

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

// CORES

private val BegeFundo = Color(0xFFEDE8D0)
private val BegePagina = Color(0xFFF5F5DC)
private val BegeClaro = Color(0xFFFFFDF3)
private val CorCard = Color(0xFFF5DEB3)
private val Marrom = Color(0xFF6E632E)
private val MarromEscuro = Color(0xFF514824)
private val Dourado = Color(0xFFD2B48C)
private val DouradoMenu = Color(0xFFE0C59F)
private val LinhaCaderno = Color(0xFFD8CFAE)
private val LinhaPapel = Color(0xFFE2C9AA)


// TELA HOME

@Composable
fun Home(
    onNovoRegistro: () -> Unit,
    onEditarRegistro: (RegistroExemplo) -> Unit,
    onExcluirRegistro: (RegistroExemplo) -> Unit,
    onSair: () -> Unit
) {

    val db = FirebaseFirestore.getInstance()
    val usuario = FirebaseAuth.getInstance().currentUser
    val context = androidx.compose.ui.platform.LocalContext.current

    val nomeUsuario =
        usuario?.displayName
            ?.takeIf { it.isNotBlank() }
            ?: "Fulano"

    var registros by remember {
        mutableStateOf<List<RegistroExemplo>>(emptyList())
    }

    // BUSCAR REGISTROS

    DisposableEffect(usuario?.uid) {

        var listener: ListenerRegistration? = null

        if (usuario != null) {

            listener = db.collection("registros")
                .whereEqualTo("usuarioId", usuario.uid)
                .addSnapshotListener { resultado, erro ->

                    if (erro != null) {
                        Toast.makeText(
                            context,
                            "Erro ao carregar registros.",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@addSnapshotListener
                    }

                    if (resultado != null) {

                        registros = resultado.documents.mapNotNull { documento ->

                            val titulo = documento.getString("titulo")
                                ?: return@mapNotNull null

                            val data = documento.getString("data") ?: ""

                            val partesData = separarData(data)

                            RegistroExemplo(
                                id = documento.id,
                                dia = partesData.first,
                                mes = partesData.second,
                                ano = partesData.third,
                                titulo = titulo,
                                local = documento.getString("local") ?: "",
                                rota = documento.getString("rota") ?: "",
                                paradas = documento.getString("paradas") ?: "",
                                observacoes = documento.getString("observacoes") ?: "",
                                estrelas = (documento.getLong("estrelas") ?: 0L)
                                    .toInt()
                                    .coerceIn(0, 5)
                            )
                        }
                    }
                }
        }

        onDispose {
            listener?.remove()
        }
    }

    val anos = registros
        .map { it.ano }
        .filter { it.isNotBlank() }
        .distinct()
        .sortedDescending()

    var registroSelecionado by remember {
        mutableStateOf<RegistroExemplo?>(null)
    }

    var menuAberto by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BegeFundo)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // TOPO

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        top = 10.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                // MENU

                Box(
                    modifier = Modifier
                        .size(
                            width = 58.dp,
                            height = 46.dp
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(Dourado)
                        .border(
                            width = 1.dp,
                            color = Marrom,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            menuAberto = !menuAberto
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {

                        repeat(3) {

                            Box(
                                modifier = Modifier
                                    .width(26.dp)
                                    .height(3.dp)
                                    .background(
                                        MarromEscuro,
                                        RoundedCornerShape(50)
                                    )
                            )
                        }
                    }
                }

                // SAIR

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Dourado)
                        .border(
                            width = 1.dp,
                            color = Marrom,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .clickable {
                            onSair()
                        }
                        .padding(
                            horizontal = 18.dp,
                            vertical = 11.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "SAIR",
                        color = MarromEscuro,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // LOGO

            ImageLogo()

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            // DIVISÓRIA

            Divisoria()

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // TÍTULO

            Text(
                text = "Diário de $nomeUsuario",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                color = MarromEscuro,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Left
            )

            Text(
                text = "Memórias que encontrei pelo caminho.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 30.dp,
                        vertical = 3.dp
                    ),
                color = Marrom,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            // PÁGINA

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(horizontal = 16.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp
                        )
                    )
                    .background(BegePagina)
            ) {

                // LINHAS

                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {

                    val espacamento = 42.dp.toPx()
                    var y = espacamento

                    while (y < size.height) {

                        drawLine(
                            color = LinhaCaderno,
                            start = Offset(0f, y),
                            end = Offset(size.width, y),
                            strokeWidth = 1f
                        )

                        y += espacamento
                    }
                }

                // LISTA

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = 12.dp,
                            vertical = 12.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // NENHUM REGISTRO

                    if (registros.isEmpty()) {

                        item {

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 80.dp,
                                        start = 20.dp,
                                        end = 20.dp
                                    ),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Ainda não há registros.",
                                    color = MarromEscuro,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )

                                Text(
                                    text = "Crie sua primeira lembrança da viagem.",
                                    color = Marrom,
                                    fontSize = 14.sp,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }

                    // REGISTROS

                    anos.forEach { ano ->

                        item {

                            Text(
                                text = ano,
                                color = MarromEscuro,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 8.dp,
                                        bottom = 2.dp
                                    )
                            )
                        }

                        items(
                            items = registros.filter {
                                it.ano == ano
                            },
                            key = {
                                it.id
                            }
                        ) { registro ->

                            CardRegistro(
                                registro = registro,

                                onClick = {
                                    registroSelecionado = registro
                                },

                                onEditar = {
                                    onEditarRegistro(registro)
                                },

                                onExcluir = {

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

                                                registroSelecionado = null
                                            }
                                            .addOnFailureListener { erro ->

                                                Toast.makeText(
                                                    context,
                                                    "Erro ao excluir: ${erro.message}",
                                                    Toast.LENGTH_LONG
                                                ).show()
                                            }
                                    }
                                }
                            )
                        }
                    }

                    item {

                        Spacer(
                            modifier = Modifier.height(80.dp)
                        )
                    }
                }
            }
        }

        // MENU ABERTO

        if (menuAberto) {

            Box(
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        top = 62.dp
                    )
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp))
                    .background(DouradoMenu)
                    .border(
                        width = 1.dp,
                        color = Marrom,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable {

                        menuAberto = false
                        onNovoRegistro()
                    }
                    .padding(
                        horizontal = 18.dp,
                        vertical = 13.dp
                    )
            ) {

                Text(
                    text = "+  Novo registro",
                    color = MarromEscuro,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // BOTÃO +

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 28.dp,
                    bottom = 24.dp
                )
                .size(60.dp)
                .clip(CircleShape)
                .background(Marrom)
                .clickable {
                    onNovoRegistro()
                },
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "+",
                color = BegeClaro,
                fontSize = 32.sp,
                fontWeight = FontWeight.Light
            )
        }
    }

    // DETALHES

    registroSelecionado?.let { registro ->

        DetalhesRegistro(
            registro = registro,

            onFechar = {
                registroSelecionado = null
            },

            onEditar = {
                registroSelecionado = null
                onEditarRegistro(registro)
            },

            onExcluir = {

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

                            registroSelecionado = null
                        }
                        .addOnFailureListener { erro ->

                            Toast.makeText(
                                context,
                                "Erro ao excluir: ${erro.message}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                }
            }
        )
    }
}


// SEPARAR DATA

private fun separarData(
    data: String
): Triple<String, String, String> {

    if (data.isBlank()) {
        return Triple("", "", "")
    }

    val partes = data.split("/")

    if (partes.size == 3) {

        val dia = partes[0]
        val mesNumero = partes[1]
        val ano = partes[2]

        val mes = when (mesNumero) {
            "01" -> "JAN"
            "02" -> "FEV"
            "03" -> "MAR"
            "04" -> "ABR"
            "05" -> "MAI"
            "06" -> "JUN"
            "07" -> "JUL"
            "08" -> "AGO"
            "09" -> "SET"
            "10" -> "OUT"
            "11" -> "NOV"
            "12" -> "DEZ"
            else -> mesNumero
        }

        return Triple(
            dia,
            mes,
            ano
        )
    }

    return Triple(
        data,
        "",
        ""
    )
}


// LOGO

@Composable
fun ImageLogo() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(
                id = R.drawable.logo_entreaguas
            ),
            contentDescription = "Logo Entre Águas",
            modifier = Modifier.size(220.dp)
        )
    }
}


// DIVISÓRIA

@Composable
fun Divisoria() {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Dourado)
        )

        Text(
            text = "✦",
            modifier = Modifier.padding(horizontal = 12.dp),
            color = Dourado,
            fontSize = 18.sp
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .background(Dourado)
        )
    }
}


// CARD

@Composable
fun CardRegistro(
    registro: RegistroExemplo,
    onClick: () -> Unit,
    onEditar: () -> Unit,
    onExcluir: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(7.dp))
            .background(CorCard)
            .border(
                width = 1.dp,
                color = Dourado,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable {
                onClick()
            }
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 16.dp,
                    top = 14.dp,
                    bottom = 14.dp,
                    end = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = registro.titulo,
                    color = MarromEscuro,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Text(
                    text = if (
                        registro.dia.isNotBlank() &&
                        registro.mes.isNotBlank() &&
                        registro.ano.isNotBlank()
                    ) {
                        "${registro.dia} de ${registro.mes} de ${registro.ano}"
                    } else {
                        "Data não informada"
                    },
                    color = Marrom,
                    fontSize = 13.sp
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    repeat(5) { indice ->

                        Text(
                            text = if (indice < registro.estrelas) {
                                "★"
                            } else {
                                "☆"
                            },
                            color = Dourado,
                            fontSize = 21.sp,
                            modifier = Modifier.padding(end = 2.dp)
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(5.dp)
                )

                Text(
                    text = "Toque para ver os detalhes",
                    color = Marrom.copy(alpha = 0.55f),
                    fontSize = 11.sp
                )
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                // EDITAR

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Dourado)
                        .clickable {
                            onEditar()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "✎",
                        color = MarromEscuro,
                        fontSize = 20.sp
                    )
                }

                // EXCLUIR

                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Dourado)
                        .clickable {
                            onExcluir()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "×",
                        color = MarromEscuro,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// DETALHES

@Composable
fun DetalhesRegistro(
    registro: RegistroExemplo,
    onFechar: () -> Unit,
    onEditar: () -> Unit,
    onExcluir: () -> Unit
) {

    Dialog(
        onDismissRequest = onFechar,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .height(500.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(BegePagina)
                .border(
                    width = 1.dp,
                    color = Dourado,
                    shape = RoundedCornerShape(18.dp)
                )
        ) {

            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {

                val espacamento = 38.dp.toPx()
                var y = espacamento

                while (y < size.height) {

                    drawLine(
                        color = LinhaPapel,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 1f
                    )

                    y += espacamento
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(22.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Detalhes",
                        color = MarromEscuro,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(7.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        // EDITAR

                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(9.dp))
                                .background(Dourado)
                                .clickable {
                                    onEditar()
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "✎",
                                color = MarromEscuro,
                                fontSize = 22.sp
                            )
                        }

                        // EXCLUIR

                        Box(
                            modifier = Modifier
                                .size(38.dp)
                                .clip(RoundedCornerShape(9.dp))
                                .background(Dourado)
                                .clickable {
                                    onExcluir()
                                },
                            contentAlignment = Alignment.Center
                        ) {

                            Text(
                                text = "×",
                                color = MarromEscuro,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // FECHAR

                        Text(
                            text = "×",
                            color = Marrom,
                            fontSize = 30.sp,
                            modifier = Modifier.clickable {
                                onFechar()
                            }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {

                    Text(
                        text = registro.titulo,
                        color = MarromEscuro,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = if (
                            registro.dia.isNotBlank() &&
                            registro.mes.isNotBlank() &&
                            registro.ano.isNotBlank()
                        ) {
                            "${registro.dia} de ${registro.mes} de ${registro.ano}"
                        } else {
                            "Data não informada"
                        },
                        color = Marrom,
                        fontSize = 14.sp
                    )

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        repeat(5) { indice ->

                            Text(
                                text = if (indice < registro.estrelas) {
                                    "★"
                                } else {
                                    "☆"
                                },
                                color = Dourado,
                                fontSize = 24.sp,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )

                    CampoDetalhe(
                        titulo = "Local / Rio",
                        texto = registro.local
                    )

                    CampoDetalhe(
                        titulo = "Rota",
                        texto = registro.rota
                    )

                    CampoDetalhe(
                        titulo = "Paradas",
                        texto = registro.paradas
                    )

                    CampoDetalhe(
                        titulo = "Observações da natureza",
                        texto = registro.observacoes
                    )

                    Spacer(
                        modifier = Modifier.height(15.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Marrom)
                        .clickable {
                            onFechar()
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "FECHAR",
                        color = BegeClaro,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// CAMPO DOS DETALHES

@Composable
fun CampoDetalhe(
    titulo: String,
    texto: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 17.dp)
    ) {

        Text(
            text = titulo,
            color = Marrom,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = texto.ifBlank {
                "Nenhuma informação registrada."
            },
            color = MarromEscuro,
            fontSize = 14.sp
        )
    }
}


// MODELO DO REGISTRO

data class RegistroExemplo(
    val id: String,
    val dia: String,
    val mes: String,
    val ano: String,
    val titulo: String,
    val local: String,
    val rota: String,
    val paradas: String,
    val observacoes: String,
    val estrelas: Int
)