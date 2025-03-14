package com.softech.imei_list_12march25

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


// Predefined modifiers to avoid recreation
private val columnModifier = Modifier
    .fillMaxSize()
    .padding(vertical = 8.dp)

private val welcomeTextModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp, vertical = 2.dp)
    .padding(top = 22.dp)

private val headlineTextModifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 16.dp, vertical = 2.dp)
    .padding(bottom = 12.dp)

private val cardColumnModifier = Modifier
    .fillMaxWidth()
    .padding(4.dp)

private val cardRowModifier = Modifier
    .fillMaxWidth()

private val cardImageModifier = Modifier
    //.weight(1.2f)
    .size(65.dp)
    .padding(8.dp)

private val cardDescriptionColumnModifier = Modifier
    .fillMaxWidth()
   // .weight(5f)

private val titleTextModifier = Modifier
    .padding(2.dp)
    .fillMaxWidth()

private val descriptionTextModifier = Modifier
    .fillMaxWidth()
    .padding(4.dp)

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
){
    val context = LocalContext.current
    val activity = context as? Activity

    // Handle back press
    BackHandler {
        activity?.finishAffinity()
    }

    // Use remember to prevent list recreation on each recomposition
    val cardList = remember(navController, context, homeViewModel) {
        homeViewModel.getCardItems(navController, context)
    }

    // Remember the customColor from ViewModel to avoid recomposition
    val cardBackgroundColor = remember { homeViewModel.customColor() }

    Column(modifier = columnModifier) {
        LazyColumn(modifier = Modifier.weight(8f)) {
            item(key = "headers") {
                WelcomeText()
                HeadlineText()
            }
            items(
                items = cardList,
                key = { it.title } // Add key for better diffing
            ) { card ->
                CustomCard(
                    title = card.title,
                    description = card.description,
                    painter = card.painter,
                    onClick = card.onClick,
                    backgroundColor = cardBackgroundColor
                )
            }
        }
    }
}

@Composable
fun WelcomeText(){
    Text(
        text = "Welcome",
        modifier = welcomeTextModifier,
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        color = Color.White
    )
}

@Composable
fun HeadlineText(){
    Text(
        text = "Simple and Magical Ways to unlock imei and bypass devices. ",
        modifier = headlineTextModifier,
        lineHeight = 18.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        color = Color.White
    )
}

// Move the homeViewModel parameter and pass backgroundColor directly
@Composable
fun CustomCard(
    title: String,
    description: String,
    painter: Int,
    onClick: () -> Unit,
    backgroundColor: Brush
){
    // Create a non-changing border shape
    val cardShape = remember { RoundedCornerShape(8.dp) }

    // Create a non-changing card border
    val borderModifier = remember {
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick) // Pass onClick function directly
            .padding(8.dp)
            .border(0.1.dp, Color(0x59FFFFFF), cardShape)
            .background(backgroundColor, shape = cardShape)
    }

    Card(
        modifier = borderModifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
    ){
        Column(modifier = cardColumnModifier) {
            Row(
                modifier = cardRowModifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(painter),
                    contentDescription = "",
                    modifier = cardImageModifier
                        .weight(1.2f)
                )
                Column(modifier = cardDescriptionColumnModifier .weight(5f)) {
                    Text(
                        text = title,
                        modifier = titleTextModifier,
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp
                    )
                    Text(
                        text = description,
                        modifier = descriptionTextModifier,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight(400),
                        fontSize = 12.sp,
                        lineHeight = 15.sp
                    )
                }
            }
        }
    }
}

@Immutable
data class CardItem(
    val title: String,
    val description: String,
    val painter: Int,
    val onClick: () -> Unit
)

enum class CardTitle(val title: String) {
    CHECK_IMEI("Check IMEI"),
    FIND_IMEI("Find IMEI"),
    UNLOCK_IMEI("Unlock IMEI"),
    DEVICE_INFO("Device Info"),
    DEVICE_UNLOCK("Device Unlock"),
    ANDROID_SECRET_CODES("Android Secret Codes"),
    FREE_IMEI_INSPECTION("Free IMEI Inspection"),
    SHARE_WITH_FRIENDS("Share With Friends"),
    MORE_APPS("More Apps"),
    USER_FEEDBACK("User Feedback");

    companion object {
        fun fromTitle(title: String): CardTitle? {
            return entries.find { it.title == title }
        }
    }
}