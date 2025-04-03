package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@Composable
fun Login(toSignUp: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Black),
        ) {
            Text(
                "MY Health DATA",
                color = Color.White,
                style = MaterialTheme.typography.displaySmall,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(50.dp))
            Image(
                painter = painterResource(R.drawable.symbol),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
            )
            Spacer(Modifier.padding(16.dp))
            Text(
                "Please enter your information",
                color = Color.Gray,
                style = MaterialTheme.typography.labelLarge,
            )
            Spacer(Modifier.padding(16.dp))
            Column(
                Modifier
                    .padding(24.dp),
            ) {
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
                        if (name.isEmpty()) {
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
                Spacer (Modifier.height(16.dp))
                MyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                        .padding(
                            vertical = 16.dp,
                        ),
                    textColor = Color.White,
                    onClick = {

                    },
                    text = "Sign in"
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
                        .background(Color.Black, RoundedCornerShape(5.dp))
                        .height(20.dp)
                        .padding(
                            vertical = 10.dp,
                        ),

                    onClick = {},
                    text = "Sign Up",
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
                    onClick = {
                        toSignUp()
                    },
                    text = "Password Reset",
                )
            }
        }
    }
}