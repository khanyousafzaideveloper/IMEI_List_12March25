package com.softech.imei_list_12march25

import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("SuspiciousIndentation", "ContextCastToActivity")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThisApp(homeViewModel: HomeViewModel = viewModel()){

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val activity = LocalContext.current as? Activity

    LaunchedEffect(Unit) {
        activity?.let {
            val window = activity.window
            window.statusBarColor = Color(0xFF1D3D73).toArgb()
            WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
                false
        }
    }

    val systemUiController = rememberSystemUiController()

    LaunchedEffect(Unit) {
        systemUiController.setNavigationBarColor(
            color = Color(0xFF1D3D73),
            darkIcons = false
        )
    }

        Scaffold(
//            topBar = {
//                if(showTopAppbar(currentRoute?:"")) {
//                    CenterAlignedTopAppBar(
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = Color(0xFF1D3D73),
//                            titleContentColor = Color.White,
//                            actionIconContentColor = Color.Transparent,
//
//                        ),
//                     //   modifier = Modifier.background(homeViewModel.backgroundColor()),
//                        title = {
//                            Text(
//                                getTitleForRoute(currentRoute),
//                                overflow = TextOverflow.Ellipsis
//                            )
//                        },
//                        navigationIcon = {
//                            if(currentRoute!=Screens.Home.name) {
//                                IconButton(onClick = { navController.navigateUp() }) {
//                                    Icon(
//                                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
//                                        contentDescription = null,
//                                        modifier = Modifier.size(30.dp),
//                                        tint = Color.White
//                                    )
//                                }
//                            }
//                        },
//                    )
//                }
//            }
        ) {innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.bg),
                    contentDescription = "",
                    modifier = Modifier.matchParentSize(),
                    contentScale = ContentScale.Crop
                )

                // Overlay gradient for darkening top and bottom
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Black.copy(alpha = 0.5f), // Dark shade at the top
                                    Color.Transparent, // Transparent in the middle
                                    Color.Black.copy(alpha = 0.5f)  // Dark shade at the bottom
                                )
                            )
                        )
                )
                Destinations(navController)
            }
        }
}


fun showTopAppbar(route:String):Boolean{
    return  when(route){
        Screens.Home.name -> true
        Screens.AndroidView.name -> true
        Screens.FormOne.name -> true
        Screens.FormTwo.name -> true
        Screens.FormThree.name -> true
        Screens.SecretCodes.name -> true
        "brand/{data}" ->true
        else -> false
    }
}

fun getTitleForRoute(route: String?): String {
    return when (route) {
        Screens.Home.name ->"IMEI Unlock - Android Codes"
        Screens.FormOne.name -> "IMEI Inspection Form"
        Screens.FormTwo.name -> "IMEI Inspection Form"
        Screens.FormThree.name -> "IMEI Inspection Form"
        Screens.AndroidView.name -> "Unlock IMEI Techniques"
        Screens.SecretCodes.name ->"Secret Codes"
        "brand/{data}" -> "Secret Codes"
        else -> ""
    }
}
