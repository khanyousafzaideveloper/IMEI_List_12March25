package com.softech.imei_list_12march25

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun SelectBrandScreen(brand: brandNamesEnum, navController: NavController, viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    Box(modifier= Modifier
        .padding(start = 20.dp, top = 16.dp, end = 20.dp)
        .fillMaxWidth()
        .clickable { navController.navigate("brand/${brand.name}") }
        .background(
            brush = viewModel.customColor(),
            shape = RoundedCornerShape(8.dp)
        )
        .border(1.dp, viewModel.customColor(), RoundedCornerShape(16.dp))
        ,
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.baseline_phone_iphone_24),
                contentDescription =null,
                modifier = Modifier
                    .size(70.dp)
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
            )
                Text(
                    text = brand.name,
                    modifier = Modifier
                        .padding(end = 16.dp)
                        .fillMaxWidth(),
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight(600),
                    fontSize = 18.sp,
                )
            }
    }

}

@Composable
fun SelectBrandListScreen(brands: List<brandNamesEnum>, navController: NavController) {

    val homeViewModel: HomeViewModel = viewModel()
    Column {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(
//                homeViewModel.customColor(),
//                shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
//            ),
//        verticalAlignment = Alignment.CenterVertically // Align children vertically
//    ) {
//        Icon(
//            Icons.Outlined.ArrowBackIosNew,
//            contentDescription = "",
//            tint = Color.White,
//            modifier = Modifier.padding(18.dp)
//                .clickable { navController.navigate(Screens.Home.name) }
//        )
//
//        Spacer(modifier = Modifier.weight(1f)) // Push the text to the center
//
//        Text(
//            "Secret Codes",
//            modifier = Modifier
//                .padding(vertical = 22.dp),
//            fontWeight = FontWeight.Bold,
//            fontSize = 20.sp,
//            textAlign = TextAlign.Center,
//            color = Color.White
//        )
//
//        Spacer(modifier = Modifier.weight(1.5f)) // Balance the remaining space
//    }
    LazyColumn {
        items(brands) { brand ->
            SelectBrandScreen(brand, navController)
        }
    }
}
}



@Preview
@Composable
fun Selected_Brand_preview() {
    //ImageForBackground()
    SelectBrandListScreen(brands = brandNamesEnum.entries, navController =fakeNavController())
}
@Composable
fun fakeNavController(): NavHostController {
    return rememberNavController() // This can be a simple rememberNavController for preview purposes
}