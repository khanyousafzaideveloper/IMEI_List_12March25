package com.softech.imei_list_12march25


import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.BatteryManager
import android.os.Build
import android.os.Environment
import android.os.StatFs
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel:ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }
    var imei by mutableStateOf("")
    var phoneModel by mutableStateOf("")
    var selectedService by mutableStateOf("")
    var selectedModel by mutableStateOf("")

    val brand = Build.BRAND
    val model = Build.MODEL
    val osVersion = Build.VERSION.RELEASE
    val sdkVersion = Build.VERSION.SDK_INT

    val statFs = StatFs(Environment.getDataDirectory().absolutePath)
    val totalStorage = statFs.totalBytes / (1024 * 1024 * 1024) // Convert to GB
    val availableStorage = statFs.availableBytes / (1024 * 1024 * 1024) // Convert to GB


    fun getCardItems(navController: NavController, context: Context): List<CardItem> {
        return listOf(
            CardItem(
                CardTitle.UNLOCK_IMEI.title,
                "Unlock your device's IMEI to access network services.",
                R.drawable.group_770,
                onClick = { navController.navigate("WebpageView/${CardTitle.UNLOCK_IMEI.title}") { launchSingleTop = true } }
            ),
            CardItem(
                CardTitle.CHECK_IMEI.title,
                "Verify and retrieve details about your device's IMEI number.",
                R.drawable.group_769,
                onClick = { navController.navigate("WebpageView/${CardTitle.CHECK_IMEI.title}") { launchSingleTop = true } }
            ),
            CardItem(
                CardTitle.FIND_IMEI.title,
                "Locate your device's IMEI number quickly and easily.",
                R.drawable.group_771,
                onClick = { navController.navigate("WebpageView/${CardTitle.FIND_IMEI.title}") { launchSingleTop = true } }
            ),
            CardItem(
                CardTitle.DEVICE_INFO.title,
                "Retrieve complete hardware and software details of your device.",
                R.drawable.group_774,
                onClick = { navController.navigate("WebpageView/${CardTitle.DEVICE_INFO.title}") { launchSingleTop = true } }
            ),
            CardItem(
                CardTitle.DEVICE_UNLOCK.title,
                "Remove restrictions and unlock your device for all networks.",
                R.drawable.group_772,
                onClick = { navController.navigate("WebpageView/${CardTitle.DEVICE_UNLOCK.title}") { launchSingleTop = true } }
            ),
            CardItem(
                CardTitle.ANDROID_SECRET_CODES.title,
                "Discover hidden Android and iOS secret codes for advanced features.",
                R.drawable.group_775,
                onClick = { navController.navigate(Screens.SecretCodes.name) { launchSingleTop = true } }
            ),
            CardItem(
                CardTitle.FREE_IMEI_INSPECTION.title,
                "Get a free IMEI check to verify your device's authenticity.",
                R.drawable.group_773,
                onClick = {
                   // navController.navigate("WebpageView/${CardTitle.FREE_IMEI_INSPECTION.title}") { launchSingleTop = true }
                    navController.navigate(Screens.Inspection.name)
                }
            ),
            CardItem(
                "Share With Friends",
                "Easily share this app with your friends and family.",
                R.drawable.group_776,
                onClick = { shareApp(context) }
            ),
            CardItem(
                "More Apps",
                "Explore and download more useful apps like this.",
                R.drawable.group_777,
                onClick = { moreApps(context) }
            ),
            CardItem(
                "User Feedback",
                "Provide your valuable feedback to help us improve the app.",
                R.drawable.group_778,
                onClick = { navController.navigate(Screens.FormOne.name) { launchSingleTop = true } }
            )
        )
    }

    fun phoneAppShareEMEICode(context: Context, code:String){
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$code")
        context.startActivity(intent)
    }
    fun shareUnlockCode(context: Context, code:String, detail:String){
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,"Code: $code\nDetail: $detail")
        }

        context.startActivity(intent)
    }

    fun customColor(): Brush {
        return Brush.sweepGradient(
            colors = listOf(  Color(0xD50D2941),
                Color(0xD70D2941)
            )
        )
    }

    //0xFF04033C
    //0xFF581F26
    fun backgroundColor(): Brush {
        return Brush.linearGradient(
            colors = listOf(  Color(0x540D2941),
                Color(0x540D2941)
            )
        )
    }

    fun getBatteryInfo(context: Context): String {
        val batteryManager = context.getSystemService(Context.BATTERY_SERVICE) as BatteryManager
        val batteryLevel = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        val isCharging = batteryManager.isCharging()

        return "$batteryLevel%"
    }


    fun getStorageInfo(): String {
        val statFs = StatFs(Environment.getDataDirectory().absolutePath)
        val totalStorage = statFs.totalBytes / (1024 * 1024 * 1024) // Convert to GB
        val availableStorage = statFs.availableBytes / (1024 * 1024 * 1024) // Convert to GB

        return "Total Storage: ${totalStorage}GB, Available Storage: ${availableStorage}GB"
    }

    fun getTotalRamInfo(context: Context): String {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)

        val totalRam = memoryInfo.totalMem / (1024 * 1024) // Convert to MB

        return "${totalRam} MB"
    }

    fun getAvailableRam(context: Context):String{
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        val availableRam = memoryInfo.availMem / (1024 * 1024) // Convert to MB

        return "$availableRam MB"
    }


    fun getDeviceDetails(context: Context): String {
        val batteryInfo = getBatteryInfo(context)
        val storageInfo = getStorageInfo()
      //  val deviceInfo = getDeviceInfo()

        return """
        Device Info:
    
        
        Battery Info:
        $batteryInfo
        
        Storage Info:
        $storageInfo
        
        RAM Info:

    """.trimIndent()
    }

    fun shareApp(context: Context){
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,"Hey check out my app at: https://play.google.com/store/apps/details?id=com.wainnovations.unlockimeideviceunlock")
        }
        context.startActivity(intent)
    }

    fun moreApps(context: Context){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://play.google.com/store/apps/developer?id=Taprave")
        context.startActivity(intent)
    }
}


//
//enum class brandNamesEnum{
//    SAMSUNG,
//    INFINIX,
//    LENOVO,
//    BLACKBERRY,
//    MOTOROLA,
//    HTC,
//    SONY,
//    LG,
//    OPPO,
//    QMOBILE,
//    CHINA,
//    GENERIC,
//    MICROSOFTWINDOW,
//    HUAWEI,
//    VIVO,
//    ACER,
//    XIAOMI,
//    NOKIA,
//    Tecno,
//    ASUS,
//    Honor,
//    IPHONE,
//    RealMe,
//    ZTE,
//    OnePlus,
//    Plam,
//    AMOL,
//    BQ,
//    IMobile,
//    LEECO,
//}
