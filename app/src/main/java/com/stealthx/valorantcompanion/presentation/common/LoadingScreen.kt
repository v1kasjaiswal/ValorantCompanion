package com.stealthx.valorantcompanion.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite

@Composable
fun LoadingScreen() {
    val loadingComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LoadingScreenDesign(loadingComposition)
    }
}

@Composable
fun LoadingScreenDesign(loadingComposition: LottieComposition?) {
    LottieAnimation(
        modifier = Modifier.size(50.dp),
        composition = loadingComposition,
        iterations = LottieConstants.IterateForever
    )

    Text(
        text = "Fight till the end! No matter what!",
        fontFamily = FontFamily(Font(R.font.aeonik)),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
        color = ValoRed
    )
}