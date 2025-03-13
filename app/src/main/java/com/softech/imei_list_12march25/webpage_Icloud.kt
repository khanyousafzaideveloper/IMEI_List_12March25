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
fun WebPage_Icloud(homeViewModel:HomeViewModel = viewModel(), navController: NavController) {
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
                webView.loadUrl("https://www.passfab.com/unlock/unlock-phone-free-with-imei-number.html")
            }
        )

        // Loading Indicator
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red.copy(alpha = 0.4f)), // Optional dim background
                contentAlignment = Alignment.Center
            ) {
                BallClipRotateMultipleIndicator()
            }
        }
    }
}
