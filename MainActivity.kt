package br.usp.iee.chinen.digitaltagbyeduardochinen

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.usp.iee.chinen.digitaltagbyeduardochinen.ui.theme.DigitalTAGByEduardoChinenTheme
import kotlinx.coroutines.delay

val fontFamily = FontFamily(
    Font(R.font.comfortaa_bold, FontWeight.Bold),
    Font(R.font.comfortaa_light, FontWeight.Light),
    Font(R.font.comfortaa_medium, FontWeight.Medium),
    Font(R.font.comfortaa_regular, FontWeight.Normal),
    Font(R.font.comfortaa_semibold, FontWeight.SemiBold)
)

class MainActivity : ComponentActivity() {
    private val myViewModel by viewModels<MainActivityViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DigitalTAGByEduardoChinenTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Navigation(myViewModel = myViewModel)
                }
            }
        }
    }
}

@Composable
fun Navigation(myViewModel: MainActivityViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            MyGui(myViewModel)
        }
    }
}


@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0.1f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1500L)
        navController.navigate("main_screen")
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xffb2fefa),
                        Color(0xff0ed2f7)
                    ),
                )
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_iee_logo),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value)
        )
    }
}


@Composable
fun MyGui(myViewModel: MainActivityViewModel) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xff4b79a1),
                        Color(0xff283e51)
                    ),
                )
            )
    ) {
        val imageIEElogo: Painter = painterResource(id = R.drawable.ic_iee_logo)
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = imageIEElogo,
            contentDescription = "",
            alpha = 0.2f
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                //.fillMaxHeight(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                val ieepainter = painterResource(id = R.drawable.logoiee202x87pxz)
                val ieecontentDescription = "Logo do IEE"
                val levepainter = painterResource(id = R.drawable.logoleve200x87pxz)
                val levecontentDescription = "Logo do LEVe"
                My_LOGO(
                    painter = ieepainter,
                    contentDescription = ieecontentDescription
                )// LOGO do IEE
                Column(
                    modifier = Modifier,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            //.align(Alignment.CenterVertically)
                            .padding(2.dp),
                        fontFamily = fontFamily,
                        fontSize = 22.sp,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        text = "IEE-USP - Serviço Técnico de Altas Potências\nLaboratório de Ensaios de Vestimentas", //myViewModel.testetexto,
                        color = Color.Cyan
                    )
                    Text(
                        modifier = Modifier
                            //.align(Alignment.CenterVertically)
                            .padding(2.dp),
                        fontFamily = fontFamily,
                        fontSize = 10.sp,
                        fontStyle = FontStyle.Italic,
                        textAlign = TextAlign.Center,
                        text = "Etiqueta Eletrônica por Eduardo Chinen @2021", //myViewModel.testetexto,
                        color = Color.Cyan
                    )
                }
                My_LOGO(
                    painter = levepainter,
                    contentDescription = levecontentDescription
                )// LOGO do LEVe

            }
            Text(
                text = myViewModel.txtAmostras,
                fontFamily = fontFamily,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                fontSize = 45.sp,
                color = Color.White)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if(myViewModel.idModoDeOperacao == 2 || (myViewModel.idModoDeOperacao == 3)){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = myViewModel.amostraU.toString(),
                            fontFamily = fontFamily,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.Normal,
                            fontSize = 100.sp,
                            color = Color.White
                        )
                    }
                }else{
                    Text(
                        text = myViewModel.amostraA.toString(),
                        fontFamily = fontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontSize = 100.sp,
                        color = Color.White
                    )
                    Text(
                        text = myViewModel.amostraB.toString(),
                        fontFamily = fontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontSize = 100.sp,
                        color = Color.White
                    )
                    Text(
                        text = myViewModel.amostraC.toString(),
                        fontFamily = fontFamily,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Normal,
                        fontSize = 100.sp,
                        color = Color.White
                    )
                }
            }
            if(myViewModel.flagHabilitaSufixo) {
                Text(
                    text = myViewModel.txtSufixo,
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    color = Color.White
                )
            }else{
                Text(
                    text = " ",
                    fontFamily = fontFamily,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    fontSize = 45.sp,
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.ArrowBack, "") },
                    text = { Text("Anterior") },
                    onClick = {
                        myViewModel.btnAnterior()
                    }
                )
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Refresh, "") },
                    text = { Text("RESET", textAlign = TextAlign.Center) },
                    onClick = {
                        myViewModel.btnReset()
                        // Toast.makeText(context, "Reset", Toast.LENGTH_SHORT).show()
                    },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                    backgroundColor = Color.White,
                    contentColor = Color.Blue
                )
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.Menu, "") },
                    text = { Text("Modo de Operação", textAlign = TextAlign.Center) },
                    onClick = {
                        myViewModel.btnSelecionarModoDeOperacao()
                        Toast.makeText(context, myViewModel.txtModoDeOperacao, Toast.LENGTH_SHORT).show()
                    },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp),
                    backgroundColor = Color.White,
                    contentColor = Color.Blue
                )
                ExtendedFloatingActionButton(
                    icon = { Icon(Icons.Filled.ArrowForward, "") },
                    text = { Text("Próximo") },
                    onClick = {
                        myViewModel.btnProximo()
                    }
                )
            }
        }
    }
}

@Composable
fun My_LOGO(
    painter: Painter,
    contentDescription: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(200.dp),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp,
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .height(90.dp)
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painter,
                contentDescription = contentDescription
            )
        }
    }
}