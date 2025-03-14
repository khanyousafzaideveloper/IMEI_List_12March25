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
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel:ViewModel() {

    var name by mutableStateOf("")
    var email by mutableStateOf("")

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
            putExtra(Intent.EXTRA_TEXT,"Hey check out my app at: https://play.google.com/store/apps/details?id=com.faappsmania.checkimiedevice")
        }
        context.startActivity(intent)
    }

    fun moreApps(context: Context){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://play.google.com/store/apps/developer?id=FA+Apps+Mania&hl=en")
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
