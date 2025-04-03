package com.example.myapplication

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL


@Composable
fun SignUp(toBack: () -> Unit, param: (String) -> Unit) {
    val co = rememberCoroutineScope()
    var uid by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.Gray),
        ) {
            Row(Modifier.fillMaxWidth()) {
                Spacer(Modifier.width(16.dp))
                Icon(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable {
                            toBack()
                        },
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.White,
                    contentDescription = null
                )
            }
            Text(
                "Sign Up",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(24.dp))


            Column(
                Modifier
                    .padding(24.dp),
            ) {
                Text(
                    "Your information,",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.displaySmall,
                )
                Spacer(Modifier.padding(16.dp))
                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(16.dp))
                    Box {
                        if (uid.isEmpty()) {
                            Text(
                                "UserId",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = uid,
                            onValueChange = { uid = it }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccountBox,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(16.dp))
                    Box {
                        if (name.isEmpty()) {
                            Text(
                                "Username",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = name,
                            onValueChange = { name = it }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(16.dp))
                    Box {
                        if (pass.isEmpty()) {
                            Text(
                                "Username",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = pass,
                            onValueChange = { pass = it }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.lock_reset_24dp_fill0_wght400_grad0_opsz24),
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(16.dp))
                    Box {
                        if (confirmPass.isEmpty()) {
                            Text(
                                "Confirm Password",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = confirmPass,
                            onValueChange = { confirmPass = it }
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                MyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(
                            vertical = 16.dp,
                        ),
                    textColor = Color.White,
                    onClick = {
                        if (name.isNotEmpty() && pass.isNotEmpty() && pass == confirmPass && uid.isNotEmpty()) {
                                co.launch {
                                    withContext(Dispatchers.IO) {
                                        val url = URL("${url}/authenticate/signup")
                                        (url.openConnection() as HttpURLConnection).run {
                                            setRequestProperty(
                                                "Content-Type",
                                                "multipart/form-data"
                                            )
                                            connectTimeout = 5000
                                            readTimeout = 5000
                                            requestMethod = "POST"
                                            val body = JSONObject().apply {
                                                put("mberId", uid)
                                                put("mberPassword", confirmPass)
                                                put("mberNm", name)
                                            }
                                            doOutput = true
                                            outputStream.bufferedWriter()
                                                .use { it.write(body.toString()) }
                                            disconnect()
                                        }
                                    }
                                    param(name)
                                }
                        }
                    },
                    text = "Sign Up"
                )
            }

            Spacer(Modifier.weight(1f))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray.copy(alpha = 0.5f))
                    .padding(50.dp),
            ) {
                MyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(5.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                        .padding(
                            vertical = 10.dp,
                        ),
                    onClick = {},
                    text = "Sign In",
                )
                Spacer(Modifier.height(15.dp))
                MyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(5.dp))
                        .border(1.dp, Color.Gray, RoundedCornerShape(5.dp))
                        .padding(
                            vertical = 10.dp,
                        ),
                    onClick = {},
                    text = "Password Reset",
                )
            }
        }
    }
}