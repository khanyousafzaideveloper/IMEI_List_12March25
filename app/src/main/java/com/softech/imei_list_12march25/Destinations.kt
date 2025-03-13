package com.softech.imei_list_12march25

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Destinations(navController : NavHostController){
    //val navController : NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.StartScreen.name) {
        composable(route=Screens.StartScreen.name){
            Startscreen(navController)
        }
        composable(Screens.Home.name) {
            HomeScreen(navController)
        }
        composable(Screens.FormOne.name) {
          //  FormOne(navController = navController)
        }
        composable(Screens.FormTwo.name) {
         //   FormTwo(navController = navController)
        }
        composable(Screens.FormThree.name) {
         //   FormThree(navController = navController)
        }
        composable(Screens.SubmitSuccess.name){
          //  SubmitSucccess(navController = navController)
        }
        composable(Screens.AndroidView.name) {
            WebPage_Icloud(navController = navController)
        }
        composable(Screens.SecretCodes.name) {
            SelectBrandListScreen(brands = brandNamesEnum.entries,navController)
        }

        composable(route="brand/{data}"){backStackEntry ->
            val data =  backStackEntry.arguments?.getString("data")
            if (data != null) {
                SecretCodeListScreen(selectedBrand = data, navController = navController)
            }
        }

    }
}

enum class Screens {
                   Home,
    StartScreen,
    AndroidView,
    FormOne,
    FormTwo,
    FormThree,
    SubmitSuccess,
    SecretCodes,
    BrandSelector
}