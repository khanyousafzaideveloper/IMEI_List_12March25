package com.softech.imei_list_12march25

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun Startscreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()){
    val context = LocalContext.current
    val activity = context as Activity
    BackHandler {
        activity.finishAffinity()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Image of shoe",
            modifier = Modifier
                .fillMaxWidth(),
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            "Unlock Your Device, get Information about Phone and IMEI",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                navController.navigate(Screens.Home.name)
            },
            modifier = Modifier
                .fillMaxWidth(),
//                .background(
//                    brush = homeViewModel.customColor(),
//                    shape = RoundedCornerShape(8.dp)
//                ),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Start",
               // color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold  // Use FontWeight.Bold instead of specifying a weight directly
            )
        }
    }
}