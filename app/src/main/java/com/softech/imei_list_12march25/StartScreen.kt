package com.softech.imei_list_12march25

import android.app.Activity
import androidx.activity.compose.BackHandler
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

// Pre-defined constants
private val SPLASH_TEXT = "Unlock Your Device, get Information about Phone and IMEI"

// Predefined modifiers to avoid recreation during recomposition
private val columnModifier = Modifier
    .fillMaxSize()
    .padding(12.dp)

private val imageModifier = Modifier
    .fillMaxWidth()

private val textModifier = Modifier
    .fillMaxWidth()

private val buttonModifier = Modifier
    .fillMaxWidth()

//private val buttonColors = ButtonDefaults.buttonColors(
//    containerColor = Color.White,
//    contentColor = Color.Black
//)

private val buttonShape = RoundedCornerShape(8.dp)

@Composable
fun StartScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {
    val context = LocalContext.current
    val activity = remember { context as Activity }
    val isLoading by homeViewModel.isLoading.collectAsState()

    // Handle back press
    BackHandler {
        activity.finishAffinity()
    }

    // Remember the navigation action to prevent recreation
    val navigateToHome = remember(navController) {
        {
            navController.navigate(Screens.Home.name) {
                launchSingleTop = true
            }
        }
    }

    Column(
        modifier = columnModifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Use SubcomposeAsyncImage optimized with remembered modifiers
        SubcomposeAsyncImage(
            model = R.drawable.frame,
            contentDescription = "Splash Image",
            loading = {
                CircularProgressIndicator(
                    modifier = imageModifier
                )
            },
            modifier = imageModifier
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = SPLASH_TEXT,
            modifier = textModifier,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
               // homeViewModel.startLoading()
                //CoroutineScope(Dispatchers.Main).launch {
                  //  delay(2400)
                    navigateToHome()
                //  }
                      },
            modifier = buttonModifier,
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            ),
            shape = buttonShape
        ) {
            Text(
                text = "Start",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(isLoading){
            CircularProgressIndicator(color = Color.White)
        }
    }
}