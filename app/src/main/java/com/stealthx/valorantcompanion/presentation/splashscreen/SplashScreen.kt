package com.stealthx.valorantcompanion.presentation.splashscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToOnBoard: () -> Unit, navigateToMain: () -> Unit) {
    var animationVisibility by remember {
        mutableStateOf(false)
    }

    val splashViewModel: SplashViewModel = hiltViewModel()

    LaunchedEffect(key1 = true) {
        delay(300)
        animationVisibility = true
        delay(1500)
        if (splashViewModel.onBoardPref.value) {
            navigateToMain()
        }
        else {
            navigateToOnBoard()
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ValoBlack)
    ) {
        SplashScreenDesign(animationVisibility)
    }
}

@Composable
fun SplashScreenDesign(textVisibility: Boolean) {

    Image(
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.splash_bg),
        contentDescription = null,
        colorFilter = ColorFilter.tint(ValoBlack, blendMode = BlendMode.Softlight),  //0xFFff4655
        modifier = Modifier
            .fillMaxSize()
            .alpha(.6f)
    )


    AnimatedVisibility(
        visible = textVisibility,
        enter = fadeIn() + slideInHorizontally()
    ) {
        Text(
            text = "vAlorant\nCoMpAnion",
            fontFamily = FontFamily(Font(R.font.valorant)),
            style = MaterialTheme.typography.displaySmall,
            color = ValoRed,
            maxLines = 2,
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp, vertical = 120.dp)
        )
    }

    AnimatedVisibility(
        visible = textVisibility,
        enter = fadeIn()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, bottom = 20.dp)
                .alpha(.6f)
        )
    }

    AnimatedVisibility(
        visible = textVisibility,
        enter = slideInHorizontally()
    ) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(id = R.drawable.agent_jett),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 180.dp, bottom = 20.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ShowPReview() {
    SplashScreenDesign(textVisibility = true)
}