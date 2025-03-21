package com.softech.imei_list_12march25

import android.graphics.Bitmap
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun WebPage_Icloud(title: String, homeViewModel:HomeViewModel = viewModel(), navController: NavController) {

    val url = when (title) {
        CardTitle.CHECK_IMEI.title -> "https://www.imei.info/"
        CardTitle.FIND_IMEI.title -> "https://www.digitaltrends.com/mobile/how-to-check-your-imei-number/"
        CardTitle.UNLOCK_IMEI.title -> "https://www.doctorsim.com/us-en/unlock-phone/"
        CardTitle.DEVICE_INFO.title -> "https://imeicheck.com/imei-check"
        CardTitle.DEVICE_UNLOCK.title -> "https://directunlocks.com/en_us"
        CardTitle.ANDROID_SECRET_CODES.title -> " "
       // CardTitle.FREE_IMEI_INSPECTION.title -> "https://imei24.com/"
        else -> "https://www.passfab.com/unlock/unlock-phone-free-with-imei-number.html" // Default URL if title doesn't match
    }

    val isLoading = remember { mutableStateOf(true) } // Track the loading state
    Box(modifier = Modifier.fillMaxSize()) {
        // WebView
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = true
                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            isLoading.value = true // Show loading indicator
                        }

                        override fun onPageFinished(view: WebView?, url: String?) {
                            super.onPageFinished(view, url)
                            isLoading.value = false // Hide loading indicator
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxSize(),
            update = { webView ->
                webView.loadUrl(url)
            }
        )

        // Loading Indicator
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue.copy(alpha = 0.4f)), // Optional dim background
                contentAlignment = Alignment.Center
            ) {
                BallClipRotateMultipleIndicator()
            }
        }
    }
}
