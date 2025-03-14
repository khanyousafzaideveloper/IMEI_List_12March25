package com.softech.imei_list_12march25

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ANIMATION_DURATION = 300
private const val FAST_ANIMATION_DURATION = 200
@Composable
fun Destinations(navController : NavHostController){
    val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = remember {
        {
            slideInHorizontally(
                initialOffsetX = { 1000 },
                animationSpec = tween(ANIMATION_DURATION, easing = EaseOut)
            ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
        }
    }

    val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = remember {
        {
            slideOutHorizontally(
                targetOffsetX = { -1000 },
                animationSpec = tween(ANIMATION_DURATION, easing = EaseIn)
            ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
        }
    }

    val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = remember {
        {
            slideInHorizontally(
                initialOffsetX = { -1000 },
                animationSpec = tween(ANIMATION_DURATION, easing = EaseOut)
            ) + fadeIn(animationSpec = tween(ANIMATION_DURATION))
        }
    }

    val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = remember {
        {
            slideOutHorizontally(
                targetOffsetX = { 1000 },
                animationSpec = tween(ANIMATION_DURATION, easing = EaseIn)
            ) + fadeOut(animationSpec = tween(ANIMATION_DURATION))
        }
    }
    NavHost(navController = navController, startDestination = Screens.StartScreen.name) {
        composable(
            route = Screens.StartScreen.name,
            exitTransition = {
                fadeOut(animationSpec = tween(FAST_ANIMATION_DURATION)) +
                        scaleOut(targetScale = 0.95f, animationSpec = tween(FAST_ANIMATION_DURATION))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(ANIMATION_DURATION)) +
                        scaleIn(initialScale = 0.95f, animationSpec = tween(ANIMATION_DURATION))
            }
        ) {
            StartScreen(navController)
        }

        // Home Screen with standard horizontal transitions
        composable(
            route = Screens.Home.name,
            enterTransition = {
                when (initialState.destination.route) {
                    Screens.StartScreen.name ->
                        fadeIn(animationSpec = tween(FAST_ANIMATION_DURATION)) +
                                scaleIn(initialScale = 1.05f, animationSpec = tween(FAST_ANIMATION_DURATION))
                    else -> enterTransition()
                }
            },
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) {
            HomeScreen(navController)
        }

        composable(
            route = Screens.FormOne.name,
                    exitTransition = {
                fadeOut(animationSpec = tween(FAST_ANIMATION_DURATION)) +
                        scaleOut(targetScale = 0.95f, animationSpec = tween(FAST_ANIMATION_DURATION))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(ANIMATION_DURATION)) +
                        scaleIn(initialScale = 0.95f, animationSpec = tween(ANIMATION_DURATION))
            }) {
            FeedbackScreen(navController)
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
        composable(
            route = "WebpageView/{title}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType }
            ),
            enterTransition = enterTransition,
            exitTransition = exitTransition,
            popEnterTransition = popEnterTransition,
            popExitTransition = popExitTransition
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            WebPage_Icloud(title = title, navController = navController)
        }
        composable(
            Screens.SecretCodes.name,
            exitTransition = {
                fadeOut(animationSpec = tween(FAST_ANIMATION_DURATION)) +
                        scaleOut(targetScale = 0.95f, animationSpec = tween(FAST_ANIMATION_DURATION))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(ANIMATION_DURATION)) +
                        scaleIn(initialScale = 0.95f, animationSpec = tween(ANIMATION_DURATION))
            }) {
            SelectBrandListScreen(brands = brandNamesEnum.entries,navController)
        }

        composable(route="brand/{data}",
            exitTransition = {
                fadeOut(animationSpec = tween(FAST_ANIMATION_DURATION)) +
                        scaleOut(targetScale = 0.95f, animationSpec = tween(FAST_ANIMATION_DURATION))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(ANIMATION_DURATION)) +
                        scaleIn(initialScale = 0.95f, animationSpec = tween(ANIMATION_DURATION))
            }
        ){backStackEntry ->
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