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
                }

                1 -> {
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
