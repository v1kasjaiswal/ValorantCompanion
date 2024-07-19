package com.stealthx.valorantcompanion.presentation.onboardingscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.stealthx.valorantcompanion.presentation.common.CustomButton
import com.stealthx.valorantcompanion.presentation.common.OnBoardingSliderIndicator
import com.stealthx.valorantcompanion.presentation.onboardingscreen.common.OnBoardingSliderPage
import com.stealthx.valorantcompanion.presentation.onboardingscreen.data.getOnBoardingDataList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(navigateToMain: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0) {
        getOnBoardingDataList().size
    }

    val buttonState = remember {
        derivedStateOf {
            when (pagerState.currentPage) {
                0, 1, 2, 3 -> "Next"
                4 -> "Let's Explore!"
                else -> ""
            }
        }
    }

    val onBoardingViewModel: OnBoardingViewModel = hiltViewModel()

    val executionScope = rememberCoroutineScope()

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier.fillMaxSize()
    ) {
        OnBoardingScreenDesign(
            pagerState = pagerState,
            buttonState = buttonState,
            executionScope = executionScope,
            navigateToHome = navigateToMain,
            onBoardViewModel = onBoardingViewModel
        )
    }
}

@Composable
fun OnBoardingScreenDesign(
    pagerState: PagerState,
    buttonState: State<String>,
    executionScope: CoroutineScope,
    navigateToHome: () -> Unit,
    onBoardViewModel: OnBoardingViewModel
) {
    HorizontalPager(state = pagerState) { index ->
        OnBoardingSliderPage(onBoardingData = getOnBoardingDataList()[index])
    }

    OnBoardingSliderIndicator(
        modifier = Modifier.padding(vertical = 130.dp),
        totalPages = getOnBoardingDataList().size, selectedPage = pagerState.currentPage
    )

    CustomButton(
        modifier = Modifier
            .fillMaxWidth(.8f)
            .padding(bottom = 50.dp),
        title = buttonState.value
    ) {
        executionScope.launch {
            if (pagerState.currentPage == 4) {
                onBoardViewModel.saveOnBoardPref()
                navigateToHome()
            }
            else {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
}