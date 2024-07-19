package com.stealthx.valorantcompanion.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.ui.theme.ValoRed

@Composable
fun ErrorScreen() {
    val loadingComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.error))

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ErrorScreenDesign(loadingComposition)
    }
}

@Composable
fun ErrorScreenDesign(loadingComposition: LottieComposition?) {
    LottieAnimation(
        contentScale = ContentScale.Crop,
        modifier = Modifier.size(350.dp),
        composition = loadingComposition,
        iterations = LottieConstants.IterateForever
    )

    Text(
        text = "Oops! Something went wrong!",
        fontFamily = FontFamily(Font(R.font.aeonik)),
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyLarge,
        color = ValoRed
    )
}