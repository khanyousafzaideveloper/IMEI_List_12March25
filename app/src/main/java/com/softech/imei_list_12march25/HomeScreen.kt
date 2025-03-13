package com.softech.imei_list_12march25

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.compose.BackHandler
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel()
){

    val context = LocalContext.current
    val activity = context as? Activity
    BackHandler {
        activity?.finishAffinity()
    }
    val cardList = listOf(
        CardItem(
            "Check IMEI",
            "Verify and retrieve details about your device's IMEI number.",
            R.drawable.group_769,
            onClick = { }
        ),
        CardItem(
            "Find IMEI",
            "Locate your device's IMEI number quickly and easily.",
            R.drawable.group_771,
            onClick = { }
        ),
        CardItem(
            "Unlock IMEI",
            "Unlock your device's IMEI to access network services.",
            R.drawable.group_770,
            onClick = { }
        ),
        CardItem(
            "Device Info",
            "Retrieve complete hardware and software details of your device.",
            R.drawable.group_774,
            onClick = { }
        ),

        CardItem(
            "Device Unlock",
            "Remove restrictions and unlock your device for all networks.",
            R.drawable.group_772,
            onClick = { }
        ),
        CardItem(
            "Android Secret Codes",
            "Discover hidden Android and iOS secret codes for advanced features.",
            R.drawable.group_775,
            onClick = { navController.navigate(Screens.SecretCodes.name)}
        ),
        CardItem(
            "Free IMEI Inspection",
            "Get a free IMEI check to verify your device’s authenticity.",
            R.drawable.group_773,
            onClick = { }
        ),


        CardItem(
            "Share With Friends",
            "Easily share this app with your friends and family.",
            R.drawable.group_776,
            onClick = { homeViewModel.shareApp(context = context) }
        ),
        CardItem(
            "More Apps",
            "Explore and download more useful apps like this.",
            R.drawable.group_777,
            onClick = { homeViewModel.moreApps(context) }
        ),
        CardItem(
            "User Feedback",
            "Provide your valuable feedback to help us improve the app.",
            R.drawable.group_778,
            onClick = { homeViewModel.shareApp(context = context) }
        )
    )
    Column(
        modifier = Modifier.fillMaxSize()
        .padding(vertical = 8.dp)
    ) {
        LazyColumn(modifier = Modifier.weight(8f)) {
            item {
                WelcomeText()
                HeadlineText()
            }
            items(cardList) { card ->
                CustomCard(
                    title = card.title,
                    description = card.description,
                    painter = card.painter,
                    onClick = card.onClick
                )
//                if (cardList.indexOf(card) % 2 == 0 && cardList.indexOf(card) != 0) {
//                    NativeAdMediaView(
//                        nativeAdId = "ca-app-pub-4235516739414575/3052262684",
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(120.dp)
//                           // .padding(8.dp)
//                    )
//                }
            }
        }
    }
}

@Composable
fun WelcomeText(){

    Text(
        text="Welcome",
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .padding(top = 22.dp),
        fontSize = 22.sp,
        fontWeight = FontWeight(700),
        color = Color.White
    )
}

@Composable
fun HeadlineText(){
    Text(
        text="Simple and Magical Ways to unlock imei and bypass devices. ",
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp)
            .padding(bottom = 12.dp),
        lineHeight = 18.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight(500),
        color = Color.White
    )
}
@Composable
fun CustomCard(
    title: String,
    description: String,
    painter: Int,
    onClick: () -> Unit,
    homeViewModel: HomeViewModel = viewModel()
){
    Card (modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(8.dp)
        .border(0.1.dp, Color(0x59FFFFFF), RoundedCornerShape(8.dp))
        .background(homeViewModel.customColor(), shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
            contentColor = Color.White
        ),
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(painter),
                    "",
                    modifier = Modifier
                        .weight(1.2f)
                        .size(65.dp)
                        .padding(8.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(5f)
                ) {
                    Text(
                        title,
                        modifier = Modifier.padding(2.dp)
                            .fillMaxWidth(),
                        fontWeight = FontWeight(700),
                        fontSize = 18.sp
                    )
                    Text(
                        description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
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

data class CardItem(
    val title: String,
    val description: String,
    val painter: Int,
    val onClick:()-> Unit

)


//
//@SuppressLint("InflateParams", "ResourceType")
//@Composable
//fun NativeAdMediaView(
//    nativeAdId: String,
//    modifier: Modifier
//) {
//    val context = LocalContext.current
//    var nativeAd by remember { mutableStateOf<NativeAd?>(null) }
//
//    // Load Native Ad
//    LaunchedEffect(nativeAdId) {
//        val adLoader = AdLoader.Builder(context, nativeAdId)
//            .forNativeAd { ad: NativeAd ->
//                // Show the ad
//                nativeAd = ad
//            }
//            .withNativeAdOptions(
//                NativeAdOptions.Builder().build()
//            )
//            .withAdListener(object : AdListener() {
//                override fun onAdFailedToLoad(adError: LoadAdError) {
//                    Log.d("Ad Error", adError.message)
//                }
//            })
//            .build()
//        adLoader.loadAd(AdRequest.Builder().build())
//    }
//
//    // Display Native Ad
//    Box(
//        modifier
//            .fillMaxSize()
//    ) {
//        nativeAd?.let {
//            AndroidView(
//                factory = { context ->
//                    // Inflate the layout with a valid ViewGroup
//                    val parent = FrameLayout(context)
//                    LayoutInflater.from(context).inflate(R.layout.gnt_small_template_view, parent, true)
//                    parent
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) { view ->
//                val nativeAdView = view.findViewById<NativeAdView>(com.google.android.ads.nativetemplates.R.id.native_ad_view)
//                // val mediaView = view.findViewById<MediaView>(R.id.media_view)
//
//                //  nativeAdView.mediaView = mediaView
//                nativeAdView.setNativeAd(it)
//
//                // Get references to the views defined in the XML
//                val iconView = view.findViewById<ImageView>(com.google.android.ads.nativetemplates.R.id.icon)
//                val primaryTextView = view.findViewById<TextView>(com.google.android.ads.nativetemplates.R.id.primary)
//                val adNotificationView = view.findViewById<TextView>(com.google.android.ads.nativetemplates.R.id.ad_notification_view)
//                val ratingBar = view.findViewById<RatingBar>(com.google.android.ads.nativetemplates.R.id.rating_bar)
//                val callToActionView = view.findViewById<AppCompatButton>(com.google.android.ads.nativetemplates.R.id.cta)
//
//                // Set the views with the ad content
//                primaryTextView.text = it.headline ?: "No headline available"
//                adNotificationView.text = "Ad" // Set static ad notification text
//                callToActionView.text = it.callToAction ?: "No action available"
//
//                // Set the ad's icon, if available
//                it.icon?.let { icon ->
//                    iconView.setImageDrawable(icon.drawable)
//                    iconView.visibility = View.VISIBLE
//                } ?: run {
//                    iconView.visibility = View.GONE
//                }
//
//                // Set the ad's star rating, if available
//                it.starRating?.let { rating ->
//                    ratingBar.rating = rating.toFloat()
//                    ratingBar.visibility = View.VISIBLE
//                } ?: run {
//                    ratingBar.visibility = View.GONE
//                }
//
//                // Set the native ad views to the NativeAdView
//                nativeAdView.iconView = iconView
//                nativeAdView.headlineView = primaryTextView
//                nativeAdView.callToActionView = callToActionView
//                nativeAdView.starRatingView = ratingBar
//            }
//        }
//    }
//}
