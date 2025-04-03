package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Navigation()
            }
        }
    }
}

val authToken = ""
const val url = "http://api.db.pe.kr:51091/api"

suspend fun http(endpoint: String, body: String?, method: String = "GET"): String {
    val url = URL(url + endpoint)
    return (withContext(Dispatchers.IO) {
        url.openConnection()
    } as HttpURLConnection).run {
        requestMethod = method
        if (body != null) doOutput = true
        setRequestProperty("Accept", "application/json")
        setRequestProperty("Content-Type", "application/json")
        setRequestProperty("Authorization", "Bearer $authToken")
        if (body != null) {
            outputStream.bufferedWriter().use { it.write(body) }
        }
        inputStream.bufferedReader().use { it.readText() }.also { disconnect() }
    }
}

@Composable
fun Navigation() {
    val navcon = rememberNavController()
    NavHost(
        navController = navcon,
        startDestination = "splash"
    ) {
        composable("splash") {
            Splash { navcon.navigate("SignIn") }
        }
        composable("SignIn") {
            Login { navcon.navigate("SignUp") }
        }
        composable("SignUp") {
            SignUp(toBack = navcon::popBackStack) { name -> navcon.navigate("ProfileTarget/${name}") }
        }
        composable("ProfileTarget/{name}") {
            val name = it.arguments?.getString("name") ?: ""
            ProfileTarget(name = name) { navcon.navigate("signIn") }
        }
    }
}

@Composable
fun Splash(function: () -> Unit) {
    val boola = remember { mutableStateOf(false) }
    val boolb = remember { mutableStateOf(false) }
    val a = animateFloatAsState(if (boola.value) 1f else 0f)
    val b = animateFloatAsState(if (boolb.value) 1f else 0f)

    LaunchedEffect(Unit) {
        boola.value = true
        delay(1000)
        boolb.value = true
        delay(1000)
        function()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    alpha = a.value,
                    painter = painterResource(R.drawable.symbol),
                    contentDescription = null,
                    modifier = Modifier.size(240.dp),
                )
                Spacer(Modifier.padding(16.dp))
                Text(
                    "MY Health DATA",
                    modifier = Modifier.alpha(b.value),
                    style = MaterialTheme.typography.displaySmall,
                )
            }
        }
    }
}


@Composable
fun MyButton(
    modifier: Modifier = Modifier,
    textColor: Color = Color.Gray,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text,
            color = textColor,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {

        NaviScreen()
    }
}