package com.example.myapplication

import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex

@Composable
fun NaviScreen() {
    var navi by remember { mutableIntStateOf(0) }
    val alarmPopUP = remember { mutableStateOf(false) }

    Scaffold(floatingActionButton = {
        if (navi == 1) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .offset(y = (-80).dp)
                    .size(50.dp)
                    .background(
                        Color.Black,
                        RoundedCornerShape(50.dp)
                    )
                    .clickable { alarmPopUP.value = true }
                    .clip(CircleShape)
            ) {
                Icon(
                    tint = Color.White,
                    modifier = Modifier.size(25.dp),
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text("My Health DATA", color = Color.White)
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.MoreVert,
                    modifier = Modifier.size(24.dp),
                    contentDescription = null,
                )
            }
            when (navi) {
                0 -> {
                    HomeScreen("compotitor99,")
                }

                1 -> {
                    AlarmScreen(alarmPopUP)
                }

                2 -> {

                }

                3 -> {

                }
            }
            Spacer(Modifier.weight(1f))
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier.clickable { navi = 0 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.home_1_svgrepo_com),
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("home", color = Color.White)
                }
                Column(
                    Modifier.clickable { navi = 1 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.alarm_clock_svgrepo_com),
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("alarm", color = Color.White)
                }
                Column(
                    Modifier.clickable { navi = 2 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.run_on_treadmill_exercise_work_out_run_svgrepo_com),
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("workout", color = Color.White)
                }
                Column(
                    Modifier.clickable { navi = 3 },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        tint = Color.White,
                        painter = painterResource(R.drawable.user_svgrepo_com),
                        modifier = Modifier.size(30.dp),
                        contentDescription = null,
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("my page", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun HomeScreen(name: String) {
    var foodsPopUP by remember { mutableStateOf(false) }
    var waterPopUP by remember { mutableStateOf(false) }
    var imagePopUP by remember { mutableStateOf(false) }
    var alarmPopUP by remember { mutableStateOf(false) }
    var selectedMeal by remember { mutableIntStateOf(0) }
    var image by remember { mutableStateOf<Uri?>(null) }
    var selectedAlarm by remember { mutableIntStateOf(0) }

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                image = uri
            }
        }
    val context = LocalContext.current
    val bitmap = remember(image) {
        image?.let {
            val inputStream = context.contentResolver.openInputStream(it)
            BitmapFactory.decodeStream(inputStream)
        }
    }

    Column(
        Modifier
            .padding(24.dp)
            .height(IntrinsicSize.Max),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Card1("Hello $name") {
            Icon(
                painter = if (true) painterResource(R.drawable.man_fill0_wght400_grad0_opsz24)
                else painterResource(R.drawable.woman_fill0_wght400_grad0_opsz24),
                modifier = Modifier.size(80.dp),
                contentDescription = null,
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.height(75.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Max)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("182.5")
                        Text("Cm")
                    }
                    VerticalDivider(
                        modifier = Modifier.height(30.dp),
                        1.dp,
                        color = Color.Gray
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("80.5")
                        Text("Kg")
                    }
                    VerticalDivider(
                        modifier = Modifier.height(30.dp),
                        1.dp,
                        color = Color.Gray
                    )
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("24.17")
                        Text("bmi")
                    }
                }
                BmiGradientBar(60f)
            }
        }
        val steps = 3000
        val progress = steps.coerceAtMost(5000) / 5000f

        Card1("Steps") {
            Column {
                Text(steps.toString())
                Text("/ 5,000 Steps")
            }

            Column(
                modifier = Modifier
                    .width(150.dp)
                    .height(40.dp)
            ) {
                Row {
                    Spacer(Modifier.fillMaxWidth(progress))
                    Text(
                        "${(steps / 5000) * 100}",
                        modifier = Modifier
                            .offset(x = (-5).dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(150.dp)
                        .height(10.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.Gray),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .background(Color.Green)
                            .fillMaxWidth(progress)
                            .clip(RoundedCornerShape(8.dp))
                    )
                }
            }
        }
        val minbpm = 24
        val maxbpm = 118
        val minprogress = minbpm / 200f
        val maxprogress = maxbpm / 200f

        Card1("Heart Rate") {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .background(Color.White, RoundedCornerShape(50.dp))
                    .clip(CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.heart_rate_svgrepo_com),
                    contentDescription = null
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("$maxbpm bpm")
                Spacer(Modifier.height(16.dp))
                Box(
                    Modifier
                        .background(Color.Black)
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                ) {
                    Text("Measure", color = Color.White)
                }
            }
            Spacer(Modifier.width(16.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .height(40.dp)
                ) {
                    Row {
                        Spacer(Modifier.fillMaxWidth(((minbpm + maxbpm).toFloat() / 400f)))
                        Text(
                            "${(steps / 5000) * 100}",
                            modifier = Modifier.offset(x = (-5).dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .width(150.dp)
                            .height(10.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color.Gray),
                    ) {
                        Box(
                            modifier = Modifier
                                .offset(x = (minprogress) * 150.dp)
                                .fillMaxHeight()
                                .background(Color.Green)
                                .fillMaxWidth(maxprogress - minprogress)
                                .clip(RoundedCornerShape(8.dp))
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    Text("0")
                    Text("200")
                }
            }
        }

        Card1("Foods") {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .background(Color.White, RoundedCornerShape(50.dp))
                    .clip(CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.food_dinner_svgrepo_com),
                    contentDescription = null
                )
            }
            Icon(
                imageVector = Icons.Filled.MoreVert,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { foodsPopUP = true },
                contentDescription = null,
            )
        }

        var water by remember { mutableIntStateOf(0) }
        val waterprogress = animateDpAsState((water.coerceAtMost(2000) / 2000f) * 31.dp, spring(1f))

        Card1("Water") {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(35.dp)
                    .background(Color.White, RoundedCornerShape(50.dp))
                    .clip(CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(R.drawable.water_drop_fill0_wght400_grad0_opsz24),
                    contentDescription = null
                )
            }
            Column {
                Text("$water / 2,000 Steps")
                Row {
                    Box(
                        Modifier
                            .clickable { water += 100 }
                            .background(Color.Black)
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                    ) {
                        Text("+ 100ml", color = Color.White)
                    }
                    Spacer(Modifier.width(8.dp))
                    Box(
                        Modifier
                            .clickable { water += 250 }
                            .background(Color.Black)
                            .padding(vertical = 4.dp, horizontal = 4.dp)
                    ) {
                        Text("+ 250ml", color = Color.White)
                    }
                }
            }
            Box(contentAlignment = Alignment.BottomStart) {
                Spacer(
                    Modifier
                        .offset(x = 1.dp, y = (-2).dp)
                        .height(waterprogress.value)
                        .width(34.dp)
                        .background(
                            Color.Blue,
                            shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                        )
                )
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(R.drawable.cup_svgrepo_com),
                    contentDescription = null,
                )
            }
            Icon(
                imageVector = Icons.Filled.MoreVert,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { waterPopUP = true },
                contentDescription = null,
            )
        }
    }
    if (foodsPopUP) {
        Dialog(
            properties = DialogProperties(dismissOnClickOutside = true),
            onDismissRequest = { foodsPopUP = false },
        ) {
            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .offset(190.dp, 430.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Add food image", modifier = Modifier.clickable {
                        imagePopUP = true
                        foodsPopUP = false
                    })
                    HorizontalDivider(
                        Modifier.width(IntrinsicSize.Max),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                    Text("Add alarm",
                        Modifier.clickable {
                            selectedAlarm = 1

                            foodsPopUP = false
                            alarmPopUP = true
                        })
                }
            }
        }
    }
    if (imagePopUP) {
        Dialog(
            properties = DialogProperties(dismissOnClickOutside = false),
            onDismissRequest = { imagePopUP = false },
        ) {
            Box(
                Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    Modifier
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Add Food Image")
                    Spacer(Modifier.height(4.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    RoundedCornerShape(16.dp)
                                )
                                .background(
                                    color = if (selectedMeal == 1) Color.Black else Color.White,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable { selectedMeal = if (selectedMeal == 1) 0 else 1 }
                                .padding(8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Breakfast",
                                color = if (selectedMeal == 1) Color.White else Color.Black
                            )
                        }
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    RoundedCornerShape(16.dp)
                                )
                                .background(
                                    color = if (selectedMeal == 2) Color.Black else Color.White,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable { selectedMeal = if (selectedMeal == 2) 0 else 2 }
                                .padding(8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Lunch",
                                color = if (selectedMeal == 2) Color.White else Color.Black
                            )
                        }
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    RoundedCornerShape(16.dp)
                                )
                                .background(
                                    color = if (selectedMeal == 3) Color.Black else Color.White,
                                    RoundedCornerShape(16.dp)
                                )
                                .clickable { selectedMeal = if (selectedMeal == 3) 0 else 3 }
                                .padding(8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Dinner",
                                color = if (selectedMeal == 3) Color.White else Color.Black
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .background(
                                Color.Gray.copy(alpha = 0.5f),
                                RoundedCornerShape(16.dp)
                            )
                            .fillMaxWidth()
                            .height(200.dp)
                            .clickable {
                                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        if (bitmap == null) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null
                            )
                        } else {
                            Image(
                                bitmap = bitmap.asImageBitmap(),
                                contentDescription = "선택된 이미지"
                            )
                        }
                    }
                    Text("그 어쩌구저쩌구 써져있는데 글시를 못읽을거같음")
                    Text("진짜 어쩔구 저쩌구임 그저 어쩌구저쩌구블라블라블라")
                    Spacer(Modifier.height(30.dp))
                    Row {
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    RoundedCornerShape(5.dp)
                                )
                                .background(
                                    color = Color.White,
                                    RoundedCornerShape(5.dp)
                                )
                                .clickable { imagePopUP = false }
                                .padding(8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Cancel",
                                color = Color.Black
                            )
                        }
                        Spacer(Modifier.width(16.dp))
                        Box(
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = Color.Black,
                                    RoundedCornerShape(5.dp)
                                )
                                .background(
                                    color = Color.Black,
                                    RoundedCornerShape(5.dp)
                                )
                                .clickable { TODO() }
                                .padding(8.dp)
                                .weight(1f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "Save",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
    if (waterPopUP) {
        Dialog(
            properties = DialogProperties(dismissOnClickOutside = false),
            onDismissRequest = { waterPopUP = false },
        ) {
            Box(
                Modifier
                    .fillMaxSize()
            ) {
                Column(
                    Modifier
                        .offset(240.dp, 580.dp)
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(12.dp)
                        .clickable {
                            selectedAlarm = 2

                            alarmPopUP = true
                            waterPopUP = false
                        },
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Text("Add alarm")
                }
            }
        }
    }
    if (alarmPopUP) {
        AlarmPopUp(
            toggleIsOpen = { alarmPopUP = it },
            startInt = selectedAlarm,
            onSaveClick = { },
        )
    }
}

@Composable
fun AlarmScreen(onFloatClick: MutableState<Boolean>) {
    val list = listOf("","")

    if (onFloatClick.value) {
        AlarmPopUp(
            toggleIsOpen = { onFloatClick.value = it },
            startInt = 3,
            onSaveClick = { },
        )
    }
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Ararms", color = Color.Black)
        Spacer(Modifier.height(12.dp))
        list.forEach { _ ->
            val h = 12
            val m = 30
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(
                        Color.Gray.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(50.dp)
                            .background(
                                Color.White,
                                RoundedCornerShape(50.dp)
                            )
                            .clip(CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(R.drawable.food_dinner_svgrepo_com),
                            contentDescription = null
                        )
                    }
                    Spacer(Modifier.width(24.dp))
                    Text("$h : $m")


                }
                Switch(checked = false, onCheckedChange = { })
            }
        }
    }
}

@Composable
fun Card1(
    text: String,
    slot: @Composable RowScope.() -> Unit,
) {
    Column(Modifier.fillMaxWidth()) {
        Text(text, color = Color.Black)
        Spacer(Modifier.height(12.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Gray.copy(alpha = 0.4f), shape = RoundedCornerShape(16.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            slot()
        }
    }
}

@Composable
fun BmiGradientBar(bmi: Float) {
    val gradientColors = listOf(Color.Blue, Color.Green, Color.Yellow, Color.Red)

    Box {
        Box(
            modifier = Modifier
                .width(250.dp)
                .height(10.dp)
                .background(
                    brush = Brush.horizontalGradient(gradientColors),
                    shape = RoundedCornerShape(16.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = (bmi / 60f) * 220.dp)
                    .width(1.dp)
                    .height(10.dp)
                    .background(Color.White),
            )
        }
        Text(
            bmi.toString(),
            Modifier
                .offset(y = (5).dp, x = (bmi / 60f) * 220.dp - 15.dp)
                .zIndex(1f),
            color = Color.White
        )
    }
}

@Composable
fun AlarmPopUp(
    toggleIsOpen: (Boolean) -> Unit,
    onSaveClick: () -> Unit,
    startInt: Int,
) {
    var alarm by remember { mutableIntStateOf(startInt) }
    var hour by remember { mutableStateOf("0") }
    var minute by remember { mutableStateOf("0") }

    Dialog(
        properties = DialogProperties(dismissOnClickOutside = true),
        onDismissRequest = { toggleIsOpen(false) },
    ) {

            Column(
                Modifier
                    .padding(12.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("Add alarm")
                Spacer(Modifier.height(4.dp))
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clickable { alarm = if (alarm == 1) 0 else 1 }
                            .size(50.dp)
                            .background(
                                if (alarm == 1) Color.Black else Color.White,
                                RoundedCornerShape(50.dp)
                            )
                            .border(
                                color = if (alarm == 1) Color.Black else Color.Gray,
                                width = 1.dp,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clip(CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            tint = if (alarm == 1) Color.White else Color.Gray,
                            painter = painterResource(R.drawable.food_dinner_svgrepo_com),
                            contentDescription = null
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clickable { alarm = if (alarm == 2) 0 else 2 }
                            .size(50.dp)
                            .background(
                                if (alarm == 2) Color.Black else Color.White,
                                RoundedCornerShape(50.dp)
                            )
                            .border(
                                color = if (alarm == 2) Color.Black else Color.Gray,
                                width = 1.dp,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clip(CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            tint = if (alarm == 2) Color.White else Color.Gray,
                            painter = painterResource(R.drawable.water_drop_fill0_wght400_grad0_opsz24),
                            contentDescription = null
                        )
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clickable { alarm = if (alarm == 3) 0 else 3 }
                            .size(50.dp)
                            .background(
                                if (alarm == 3) Color.Black else Color.White,
                                RoundedCornerShape(50.dp)
                            )
                            .border(
                                color = if (alarm == 3) Color.Black else Color.Gray,
                                width = 1.dp,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .clip(CircleShape)
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(25.dp)
                                .offset(x = 5.dp),
                            tint = if (alarm == 3) Color.White else Color.Gray,
                            painter = painterResource(R.drawable.etc_svgrepo_com),
                            contentDescription = null
                        )
                    }
                }
                Spacer(Modifier.height(16.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    Row {
                        BasicTextField(
                            modifier = Modifier
                                .width(50.dp)
                                .height(30.dp),
                            value = hour,
                            onValueChange = { hour = it },
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("hours")
                    }
                    Spacer(Modifier.width(16.dp))
                    Row {
                        BasicTextField(
                            modifier = Modifier
                                .width(50.dp)
                                .height(30.dp),
                            value = minute,
                            onValueChange = { minute = it },
                        )
                        Spacer(Modifier.width(8.dp))
                        Text("hours")
                    }
                }
                Spacer(Modifier.height(16.dp))
                Row {
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                RoundedCornerShape(5.dp)
                            )
                            .background(
                                color = Color.White,
                                RoundedCornerShape(5.dp)
                            )
                            .clickable { toggleIsOpen(false) }
                            .padding(8.dp)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Cancel",
                            color = Color.Black
                        )
                    }
                    Spacer(Modifier.width(16.dp))
                    Box(
                        modifier = Modifier
                            .border(
                                width = 1.dp,
                                color = Color.Black,
                                RoundedCornerShape(5.dp)
                            )
                            .background(
                                color = Color.Black,
                                RoundedCornerShape(5.dp)
                            )
                            .clickable { onSaveClick() }
                            .padding(8.dp)
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "Save",
                            color = Color.White
                        )
                    }

            }
        }
    }
}