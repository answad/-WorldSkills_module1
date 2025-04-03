package com.example.myapplication

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
fun ProfileTarget(name: String, toBack: () -> Unit) {
    var selected by remember { mutableStateOf<Boolean?>(null) }

    var uid by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var birth by remember { mutableStateOf("") }
    var walk by remember { mutableStateOf("") }
    var water by remember { mutableStateOf("") }

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
                "Profile & Target",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                Modifier
                    .padding(24.dp),
            ) {
                Text(
                    "Hi $name",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.displaySmall,
                )
                Spacer(Modifier.height(12.dp))
                Text(
                    "Profile,",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                )
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .weight(1f)
                            .clickable { selected = false }
                            .then(
                                if (selected == false) Modifier.border(
                                    1.dp,
                                    Color.Black,
                                    RoundedCornerShape(10.dp)
                                ) else Modifier
                            )
                            .padding(vertical = 10.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(R.drawable.man_fill0_wght400_grad0_opsz24),
                            contentDescription = null,
                        )
                        Text(
                            "Male",
                            modifier = Modifier,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                    Spacer(Modifier.width(59.dp))
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(Color.Gray.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                            .weight(1f)
                            .clickable { selected = true }
                            .then(
                                if (selected == true) Modifier.border(
                                    1.dp,
                                    Color.Black,
                                    RoundedCornerShape(10.dp)
                                ) else Modifier
                            )
                            .padding(vertical = 10.dp)
                    ) {
                        Image(
                            modifier = Modifier.size(70.dp),
                            painter = painterResource(R.drawable.man_fill0_wght400_grad0_opsz24),
                            contentDescription = null,
                        )
                        Text(
                            "Female",
                            modifier = Modifier,
                            color = Color.Black,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }
                Spacer(Modifier.padding(10.dp))
                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Box {
                        BasicTextField(
                            enabled = true,
                            modifier = Modifier.fillMaxWidth(),
                            value = uid,
                            onValueChange = { uid = it }
                        )
                    }
                }
                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.height_svgrepo_com),
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Box {
                        if (height.isEmpty()) {
                            Text(
                                "Username",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.widthIn(min = 200.dp),
                            value = height,
                            onValueChange = { height = it }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "Cm",

                        modifier = Modifier.width(30.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp),

                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Box {
                        if (weight.isEmpty()) {
                            Text(
                                "Username",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.widthIn(min = 200.dp),
                            value = weight,
                            onValueChange = { weight = it }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "KG",

                        modifier = Modifier.width(30.dp),
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge,
                    )
                }
                Spacer(Modifier.height(10.dp))

                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.cake_svgrepo_com),
                        tint = Color.Gray, modifier = Modifier.size(24.dp),

                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Box {
                        if (birth.isEmpty()) {
                            Text(
                                "Confirm Password",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = birth,
                            onValueChange = { birth = it }
                        )
                    }
                }
                Spacer(Modifier.padding(10.dp))

                Text(
                    "Target,",
                    modifier = Modifier.fillMaxWidth(),
                    color = Color.Black,
                    style = MaterialTheme.typography.titleLarge,
                )
                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.water_drop_fill0_wght400_grad0_opsz24),
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Box {
                        if (name.isEmpty()) {
                            Text(
                                "Confirm Password",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.widthIn(min = 200.dp),
                            value = walk,
                            onValueChange = { walk = it }
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        "Steps",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Spacer(Modifier.padding(10.dp))

                Row(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                        .padding(10.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.footprint_fill0_wght400_grad0_opsz24),
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(10.dp))
                    Box {
                        if (name.isEmpty()) {
                            Text(
                                "Confirm Password",
                                color = Color.Gray,
                                style = MaterialTheme.typography.labelLarge,
                            )
                        }
                        BasicTextField(
                            modifier = Modifier.widthIn(min = 200.dp),
                            value = water,
                            onValueChange = { water = it }
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        "ml",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
                Spacer(Modifier.weight(1f))

                MyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
                        .padding(
                            vertical = 16.dp,
                        ),
                    textColor = Color.White,
                    onClick = {},
                    text = "Complete"
                )
            }
        }
    }
}