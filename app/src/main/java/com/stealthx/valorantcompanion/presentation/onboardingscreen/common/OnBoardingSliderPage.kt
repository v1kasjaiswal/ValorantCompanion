package com.stealthx.valorantcompanion.presentation.onboardingscreen.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.presentation.onboardingscreen.data.OnBoardingData
import com.stealthx.valorantcompanion.ui.theme.ValoWhite

@Composable
fun OnBoardingSliderPage(onBoardingData: OnBoardingData) {
    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        OnBoardingSliderPageDesign(onBoardingData = onBoardingData)
    }
}

@Composable
fun OnBoardingSliderPageDesign(onBoardingData: OnBoardingData) {
    Image(
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val gradient = Brush.verticalGradient(
                    colors = listOf(Color.Transparent, Color.Black),
                    startY = size.height / 2,
                    endY = size.height
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(gradient, blendMode = BlendMode.Darken)
                }
            },
        painter = painterResource(id = onBoardingData.image),
        contentDescription = null
    )

    Text(
        text = onBoardingData.title,
        fontFamily = FontFamily(Font(R.font.aeonik)),
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 150.dp),
        textAlign = TextAlign.Center,
        color = ValoWhite,
        fontWeight = FontWeight.Bold
    )
}