package com.example.personaspokeo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll



private val Background = Color(0xFFFFF7F7)
private val Purple = Color(0xFF515AAA)

private val JapanBlue = Color(0xFF5170FF)
private val GreecePurple = Color(0xFF8D68D9)
private val ItalyYellow = Color(0xFFE7C84A)
private val VeniceOrange = Color(0xFFE88A4A)
private val EgyptGold = Color(0xFFD5A63A)
private val BrazilGreen = Color(0xFF69A878)
private val AfricaBrown = Color(0xFFB8785C)
private val AmericaRed = Color(0xFFE88B8B)

private val Pink = Color(0xFFE1B2D2)
private val LightGray = Color(0xFFD9D9D9)

data class Mask(
    val name: String,
    val description: String,
    val context: String,
    val period: String,
    val image: Int,
    val color: Color
)

data class Culture(
    val title: String,
    val subtitle: String,
    val color: Color,
    val masks: List<Mask>
)

@Composable
fun HomeScreen(
    onLogout: () -> Unit
) {


    val cultures = listOf(


        Culture(
            title = "MÁSCARAS JAPONESAS: TEATRO NÔ",
            subtitle = "Teatro Nô",
            color = JapanBlue,
            masks = listOf(

                Mask(
                    name = "HANNYA",
                    description = "Representa uma mulher transformada em um espírito demoníaco por sentimentos intensos como ciúme, raiva e sofrimento.",
                    context = "A máscara Hannya é utilizada no Teatro Nô para representar espíritos femininos atormentados.",
                    period = "Século XIV (1300–1400), durante o Período Muromachi, no Japão.",
                    image = R.drawable.hannya,
                    color = JapanBlue
                ),

                Mask(
                    name = "OKINA",
                    description = "Representa um ancião sábio e possui forte relação com bênçãos, longevidade e espiritualidade.",
                    context = "A máscara Okina aparece em apresentações tradicionais do Teatro Nô e simboliza sabedoria e proteção.",
                    period = "Período Muromachi, no Japão.",
                    image = R.drawable.okina,
                    color = JapanBlue
                ),

                Mask(
                    name = "KO-OMOTE",
                    description = "Representa uma jovem mulher com uma expressão delicada, serena e elegante.",
                    context = "A Ko-Omote é uma das máscaras femininas mais conhecidas do Teatro Nô.",
                    period = "Período Muromachi, no Japão.",
                    image = R.drawable.ko_omote,
                    color = JapanBlue
                )
            )
        ),



        Culture(
            title = "MÁSCARAS GREGAS: TEATRO",
            subtitle = "Grécia Antiga",
            color = GreecePurple,
            masks = listOf(

                Mask(
                    name = "TRAGÉDIA",
                    description = "Máscara associada aos personagens trágicos do teatro grego, representando sofrimento e emoções intensas.",
                    context = "As máscaras eram fundamentais nas apresentações teatrais da Grécia Antiga." +
                            "Esta representa o papel do primeiro escravo em tragédias antigas, século II a.C., encontrada em Dipylon Gate, Atenas.",
                    period = "Antiguidade Grega, aproximadamente entre os séculos VI e IV a.C.",
                    image = R.drawable.tragedia,
                    color = GreecePurple
                ),

                Mask(
                    name = "COMÉDIA",
                    description = "Representava personagens cômicos e possuía expressões exageradas para facilitar a identificação dos personagens.",
                    context = "O teatro grego utilizava máscaras tanto em comédias quanto em tragédias. Esta é a máscara de teatro da Grécia Antiga usada por atores que desempenharam ",
                    period = "Antiguidade Grega.",
                    image = R.drawable.comedia,
                    color = GreecePurple
                ),

                Mask(
                    name = "DIONÍSO",
                    description = "Máscara de Dionísio com mais de 2.400 anos, encontrada na região da antiga cidade de Aizanoi, na atual Turquia.",
                    context = "A peça representa Dionísio, deus grego do vinho, das festas e do teatro, e faz parte dos vestígios da tradição teatral e religiosa da Antiguidade Grega. Dionísio estava diretamente ligado às celebrações que deram origem às primeiras manifestações do teatro grego. Em sua homenagem eram realizados festivais, nos quais surgiram apresentações que contribuíram para o desenvolvimento da tragédia e da comédia.",
                    period = "Século IV a.C. — Antiguidade Grega.",
                    image = R.drawable.dionisio,
                    color = GreecePurple
                )
            )
        ),


        Culture(
            title = "MÁSCARAS ITALIANAS: COMMEDIA DELL'ARTE",
            subtitle = "Commedia dell'Arte",
            color = ItalyYellow,
            masks = listOf(

                Mask(
                    name = "VOLTO",
                    description = "Máscara de rosto inteiro, geralmente com aparência neutra e elegante, utilizada para ocultar a identidade de quem a vestia.",
                    context = "O Volto é uma máscara tradicional de origem italiana que também se tornou popular nas festividades e no Carnaval de Veneza.",
                    period = "A partir dos séculos XVI e XVII, na Itália.",
                    image = R.drawable.volto,
                    color = ItalyYellow
                ),

                Mask(
                    name = "COLOMBINA",
                    description = "Personagem feminina inteligente, esperta e espirituosa, frequentemente envolvida em situações amorosas e cômicas.",
                    context = "Colombina é uma das personagens mais conhecidas da Commedia dell'Arte, tradição teatral italiana baseada em personagens característicos e situações improvisadas.",
                    period = "Séculos XVI e XVII, na Itália.",
                    image = R.drawable.colombina,
                    color = ItalyYellow
                ),

                Mask(
                    name = "ARLECCHINO",
                    description = "Personagem brincalhão, ágil e astuto, conhecido por suas confusões e por seu comportamento irreverente.",
                    context = "Arlecchino é um dos personagens mais famosos da Commedia dell'Arte e tradicionalmente é representado com uma roupa colorida em formato de losangos.",
                    period = "Séculos XVI e XVII, na Itália.",
                    image = R.drawable.arlecchino,
                    color = ItalyYellow
                )
            )
        ),


        Culture(
            title = "MÁSCARAS DE VENEZA",
            subtitle = "Carnaval de Veneza",
            color = VeniceOrange,
            masks = listOf(

                Mask(
                    name = "MORETTA",
                    description = "Máscara pequena, oval e geralmente escura, que cobre o rosto e destaca principalmente a região dos olhos.",
                    context = "A Moretta era tradicionalmente usada por mulheres em Veneza e ficou associada ao mistério e à elegância durante o Carnaval veneziano.",
                    period = "Popularizada em Veneza entre os séculos XVII e XVIII.",
                    image = R.drawable.moretta,
                    color = VeniceOrange
                ),

                Mask(
                    name = "BAUTA",
                    description = "Máscara branca de formato característico, geralmente acompanhada por uma capa e um chapéu, ocultando a identidade de quem a usava.",
                    context = "A Bauta foi uma das máscaras mais tradicionais de Veneza e era utilizada em diferentes ocasiões sociais, especialmente durante o Carnaval.",
                    period = "Popularizada em Veneza entre os séculos XVII e XVIII.",
                    image = R.drawable.bauta,
                    color = VeniceOrange
                ),

                Mask(
                    name = "MEDICO DELLA PESTE",
                    description = "Máscara com um longo bico, inspirada nas vestimentas utilizadas pelos médicos durante epidemias de peste.",
                    context = "Apesar de hoje ser um dos símbolos mais conhecidos do Carnaval de Veneza, a máscara do Médico da Peste está relacionada originalmente à figura dos médicos que tratavam pacientes durante epidemias.",
                    period = "Associada à tradição veneziana e popularizada no Carnaval de Veneza.",
                    image = R.drawable.medico_della_peste,
                    color = VeniceOrange
                )
            )
        ),


        Culture(
            title = "MÁSCARAS EGÍPCIAS",
            subtitle = "Artefatos fúnebres e religiosos",
            color = EgyptGold,
            masks = listOf(

                Mask(
                    name = "TUTANCÂMON",
                    description = "Máscara funerária de ouro criada para acompanhar o faraó Tutancâmon em seu ritual funerário e em sua passagem para a vida após a morte.",
                    context = "A máscara fazia parte dos ritos funerários do Egito Antigo e era colocada sobre o rosto da múmia para preservar a identidade do faraó e protegê-lo espiritualmente. O rosto idealizado de Tutancâmon reforçava sua condição de faraó e sua ligação com o mundo divino. A peça também apresenta símbolos tradicionais da realeza egípcia, como o nemes e as imagens da cobra e do abutre, associados à proteção e ao poder do faraó.",
                    period = "Novo Império — aproximadamente século XIV a.C., XVIII Dinastia.",
                    image = R.drawable.tutancamon,
                    color = EgyptGold
                ),

                Mask(
                    name = "ANÚBIS",
                    description = "Máscara ritual associada ao deus Anúbis, representado com cabeça de chacal e ligado à mumificação, à proteção dos mortos e à passagem para a vida após a morte.",
                    context = "Anúbis desempenhava um papel importante nos ritos funerários do Egito Antigo. Ele era associado à proteção do corpo durante a mumificação e à condução e proteção do morto em sua passagem para o mundo dos mortos. Sua imagem também estava relacionada ao julgamento do falecido, no qual o coração era pesado diante da deusa Maat para determinar se a pessoa poderia alcançar a vida após a morte. Máscaras e representações de Anúbis podiam ser utilizadas em contextos religiosos e funerários para simbolizar sua proteção sobre o morto.",
                    period = "Egito Antigo — sua veneração ocorreu ao longo de milhares de anos.",
                    image = R.drawable.anubis,
                    color = EgyptGold
                ),

                Mask(
                    name = "PSUSENNES I",
                    description = "Máscara funerária de ouro criada para acompanhar o faraó Psusennes I em seu ritual funerário e em sua passagem para a vida após a morte.",
                    context = "No Egito Antigo, a máscara funerária fazia parte dos ritos religiosos de preparação do morto. Ela ajudava a preservar sua identidade e sua aparência para que pudesse ser reconhecido no mundo dos mortos. Assim como a famosa máscara de Tutancâmon, a máscara de Psusennes I representa o faraó com características idealizadas e elementos ligados à realeza e à proteção divina. Porém, a máscara de Psusennes I se destaca por ter sido confeccionada em ouro e por ter sido encontrada em seu túmulo em Tanis.",
                    period = "Século XI a.C. — XXI Dinastia, Egito Antigo.",
                    image = R.drawable.psusennes,
                    color = EgyptGold
                )
            )
        ),


        Culture(
            title = "MÁSCARAS BRASILEIRAS",
            subtitle = "Povos originários do Brasil",
            color = BrazilGreen,
            masks = listOf(

                Mask(
                    name = "KARAJÁ",
                    description = "Máscaras e objetos cerimoniais produzidos em contextos ritualísticos e culturais do povo Karajá.",
                    context = "As máscaras podem estar relacionadas a rituais, festas e representações de seres espirituais.",
                    period = "Tradições indígenas preservadas ao longo de gerações.",
                    image = R.drawable.brasil1,
                    color = BrazilGreen
                ),

                Mask(
                    name = "TICUNA",
                    description = "Máscaras utilizadas em manifestações culturais e rituais tradicionais do povo Ticuna.",
                    context = "As máscaras fazem parte de práticas culturais que representam personagens e seres relacionados às tradições do povo.",
                    period = "Tradições indígenas amazônicas.",
                    image = R.drawable.brasil2,
                    color = BrazilGreen
                ),

                Mask(
                    name = "WAIÃPI",
                    description = "Representações utilizadas em manifestações culturais indígenas, relacionadas à identidade e às tradições do povo.",
                    context = "As práticas com máscaras e pinturas corporais podem possuir significados cerimoniais e simbólicos.",
                    period = "Tradições indígenas amazônicas.",
                    image = R.drawable.brasil3,
                    color = BrazilGreen
                )
            )
        ),

        Culture(
            title = "MÁSCARAS AFRICANAS",
            subtitle = "Tradições e rituais",
            color = AfricaBrown,
            masks = listOf(

                Mask(
                    name = "DAN",
                    description = "Máscara associada ao povo Dan, conhecida por suas formas delicadas e expressão humana estilizada.",
                    context = "Máscaras Dan podem aparecer em cerimônias e manifestações espirituais.",
                    period = "Tradições da África Ocidental.",
                    image = R.drawable.dan,
                    color = AfricaBrown
                ),

                Mask(
                    name = "DOGON",
                    description = "Máscaras utilizadas em cerimônias tradicionais do povo Dogon, muitas vezes relacionadas à ancestralidade.",
                    context = "As máscaras Dogon possuem importante papel em rituais e cerimônias comunitárias.",
                    period = "Tradições do Mali, África Ocidental.",
                    image = R.drawable.dogon,
                    color = AfricaBrown
                ),

                Mask(
                    name = "YORÙBÁ",
                    description = "Máscaras relacionadas a tradições religiosas e culturais dos povos Yorùbá.",
                    context = "Podem representar divindades, ancestrais e elementos espirituais em cerimônias.",
                    period = "Tradições da África Ocidental.",
                    image = R.drawable.yoruba,
                    color = AfricaBrown
                )
            )
        ),


        Culture(
            title = "MÁSCARAS AMERICANAS PRÉ-COLOMBIANAS",
            subtitle = "Astecas, Maias e Lambayeque",
            color = AmericaRed,
            masks = listOf(

                Mask(
                    name = "TEZCATLIPOCA - ASTECAS",
                    description = "Máscara de pedra associada a Tezcatlipoca, uma das principais divindades da religião asteca, relacionada ao poder, à noite, ao destino e à criação.",
                    context = "As máscaras associadas a Tezcatlipoca faziam parte do universo religioso e simbólico dos povos astecas. Tezcatlipoca era uma divindade de grande importância e aparecia em cerimônias, esculturas e objetos rituais. As máscaras e representações de deuses podiam ser utilizadas em contextos religiosos para personificar ou homenagear divindades, além de reforçar a ligação entre os governantes, os sacerdotes e o mundo sobrenatural.",
                    period = "Civilização Asteca — aproximadamente entre os séculos XIV e XVI.",
                    image = R.drawable.tezcatlipoca,
                    color = AmericaRed
                ),

                Mask(
                    name = "CALAKMUL - MAIAS",
                    description = "Máscara funerária maia produzida em mosaico de jade e outros materiais preciosos, associada à representação de uma pessoa de alta posição social e à transformação simbólica do morto.",
                    context = "Máscaras funerárias como as encontradas em Calakmul eram colocadas sobre os rostos dos mortos durante os rituais de sepultamento, especialmente de membros da elite. O jade possuía grande importância simbólica para os maias, sendo associado à vida, à fertilidade, à água e à renovação. A máscara ajudava a transformar simbolicamente o indivíduo e a relacioná-lo ao mundo dos deuses e à continuidade da vida após a morte.",
                    period = "Período Clássico Maia — aproximadamente entre os séculos V e VII d.C.",
                    image = R.drawable.calakmul,
                    color = AmericaRed
                ),

                Mask(
                    name = "SICÁN - LAMBAYEQUE",
                    description = "Máscara funerária de metal dourado produzida pela cultura Sicán, conhecida por suas representações de personagens de aparência sobrenatural e por seu uso em sepultamentos de membros da elite.",
                    context = "As máscaras Sicán eram colocadas sobre os rostos dos mortos em elaborados rituais funerários. Muitas foram produzidas em metais trabalhados, principalmente ligas de cobre com aparência dourada, e apresentavam olhos marcados e características estilizadas. Essas máscaras estavam relacionadas à transformação simbólica do indivíduo após a morte e à sua ligação com o mundo sobrenatural. A cultura Sicán floresceu no norte do atual Peru antes do domínio Inca.",
                    period = "Cultura Sicán — aproximadamente entre os séculos VIII e XIV d.C.",
                    image = R.drawable.sican,
                    color = AmericaRed
                )
            )
        )
    )


    var selectedMask by remember {
        mutableStateOf<Mask?>(null)
    }

    var showFavorites by remember {
        mutableStateOf(false)
    }

    val favorites = remember {
        mutableStateListOf<String>()
    }


    // ========================================================
    // MÁSCARAS FAVORITAS
    // ========================================================

    val favoriteMasks = cultures
        .flatMap { it.masks }
        .filter { favorites.contains(it.name) }


    // ========================================================
    // TELA PRINCIPAL
    // ========================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
    ) {



        if (!showFavorites) {

            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(
                    bottom = 25.dp
                )
            ) {

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Purple,
                                RoundedCornerShape(
                                    bottomStart = 45.dp,
                                    bottomEnd = 45.dp
                                )
                            )
                            .padding(
                                top = 18.dp,
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 18.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Image(
                            painter = painterResource(
                                R.drawable.personaspokeo_logo
                            ),
                            contentDescription = "Persona Spokeo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Button(
                            onClick = onLogout,
                            modifier = Modifier
                                .width(145.dp)
                                .height(38.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Pink
                            ),
                            contentPadding = PaddingValues(0.dp)
                        ) {

                            Text(
                                text = "Sair",
                                color = Color.White,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }


                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                top = 18.dp,
                                start = 24.dp,
                                end = 24.dp,
                                bottom = 18.dp
                            )
                    ) {

                        Text(
                            text = "Venha e observe.",
                            color = Pink,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text(
                            text = "Uma máscara. Mil Personas.",
                            color = Pink,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 2.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )
                    }
                }

                items(cultures) { culture ->

                    CultureSection(
                        culture = culture,
                        favorites = favorites,
                        onMaskClick = {
                            selectedMask = it
                        },
                        onFavoriteClick = { mask ->

                            if (favorites.contains(mask.name)) {
                                favorites.remove(mask.name)
                            } else {
                                favorites.add(mask.name)
                            }
                        }
                    )
                }
            }

        } else {


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {

                Text(
                    text = "FAVORITOS",
                    color = Purple,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        start = 24.dp,
                        top = 28.dp,
                        bottom = 15.dp
                    )
                )

                if (favoriteMasks.isEmpty()) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(30.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favoritos",
                            tint = Pink,
                            modifier = Modifier.size(70.dp)
                        )

                        Spacer(
                            modifier = Modifier.height(10.dp)
                        )

                        Text(
                            text = "Nenhuma máscara favorita ainda.",
                            color = Color.Gray,
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }

                } else {

                    LazyColumn(
                        contentPadding = PaddingValues(
                            start = 20.dp,
                            end = 20.dp,
                            bottom = 20.dp
                        ),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        items(favoriteMasks) { mask ->

                            FavoriteMaskCard(
                                mask = mask,
                                onClick = {
                                    selectedMask = mask
                                },
                                onFavoriteClick = {
                                    favorites.remove(mask.name)
                                }
                            )
                        }
                    }
                }
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple)
                .navigationBarsPadding()
                .height(72.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickable {
                        showFavorites = false
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = if (!showFavorites) {
                        Pink
                    } else {
                        Color.White
                    },
                    modifier = Modifier.size(27.dp)
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "Home",
                    color = if (!showFavorites) {
                        Pink
                    } else {
                        Color.White
                    },
                    fontSize = 12.sp,
                    fontWeight = if (!showFavorites) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    }
                )
            }


            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .clickable {
                        showFavorites = true
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favoritos",
                    tint = if (showFavorites) {
                        Pink
                    } else {
                        Color.White
                    },
                    modifier = Modifier.size(26.dp)
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "Favoritos",
                    color = if (showFavorites) {
                        Pink
                    } else {
                        Color.White
                    },
                    fontSize = 12.sp,
                    fontWeight = if (showFavorites) {
                        FontWeight.Bold
                    } else {
                        FontWeight.Normal
                    }
                )
            }
        }
    }


    selectedMask?.let { mask ->

        AlertDialog(
            onDismissRequest = {
                selectedMask = null
            },

            title = {

                Text(
                    text = mask.name,
                    color = mask.color,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            },

            text = {



                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(400.dp)
                        .verticalScroll(
                            rememberScrollState()
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    Image(
                        painter = painterResource(
                            mask.image
                        ),
                        contentDescription = mask.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .clip(
                                RoundedCornerShape(18.dp)
                            ),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )


                    Text(
                        text = mask.description,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )


                    Text(
                        text = "CONTEXTO",
                        color = mask.color,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = mask.context,
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )



                    Text(
                        text = "PERÍODO",
                        color = mask.color,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = mask.period,
                        fontSize = 13.sp,
                        lineHeight = 19.sp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )
                }
            },

            confirmButton = {

                TextButton(
                    onClick = {
                        selectedMask = null
                    }
                ) {

                    Text(
                        text = "Fechar",
                        color = mask.color
                    )
                }
            }
        )
    }
}

@Composable
private fun CultureSection(
    culture: Culture,
    favorites: MutableList<String>,
    onMaskClick: (Mask) -> Unit,
    onFavoriteClick: (Mask) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 20.dp,
                vertical = 18.dp
            )
    ) {


        Text(
            text = "• ${culture.title}",
            color = culture.color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "• ${culture.subtitle}",
            color = Color.Gray,
            fontSize = 12.sp,
            modifier = Modifier.padding(
                top = 3.dp,
                bottom = 12.dp
            )
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            culture.masks.forEach { mask ->

                MaskCard(
                    modifier = Modifier.weight(1f),
                    mask = mask,
                    isFavorite = favorites.contains(mask.name),
                    onClick = {
                        onMaskClick(mask)
                    },
                    onFavoriteClick = {
                        onFavoriteClick(mask)
                    }
                )
            }
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(LightGray)
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )
    }
}


@Composable
private fun MaskCard(
    modifier: Modifier = Modifier,
    mask: Mask,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = modifier
            .height(125.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            Image(
                painter = painterResource(
                    mask.image
                ),
                contentDescription = mask.name,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(
                        RoundedCornerShape(15.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favoritar",
                tint = if (isFavorite) {
                    Pink
                } else {
                    Color.White
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(29.dp)
                    .clickable {
                        onFavoriteClick()
                    }
                    .padding(4.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(
                        Color.Black.copy(
                            alpha = 0.45f
                        )
                    )
                    .padding(
                        vertical = 5.dp
                    )
            ) {

                Text(
                    text = mask.name,
                    color = Color.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun FavoriteMaskCard(
    mask: Mask,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(
                    mask.image
                ),
                contentDescription = mask.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            bottomStart = 15.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            ) {

                Text(
                    text = mask.name,
                    color = mask.color,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Toque para saber mais",
                    color = Color.Gray,
                    fontSize = 11.sp,
                    modifier = Modifier.padding(
                        top = 4.dp
                    )
                )
            }

            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Remover dos favoritos",
                tint = Pink,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        onFavoriteClick()
                    }
                    .padding(3.dp)
            )
        }
    }
}

