package com.stealthx.valorantcompanion.presentation.onboardingscreen.data

import androidx.annotation.DrawableRes
import com.stealthx.valorantcompanion.R

data class OnBoardingData (
    val title: String,
    @DrawableRes val image: Int
)

private  val OnBoardingDataList: List<OnBoardingData> = listOf(
    OnBoardingData(
        title = "Discover agent strengths, and unique abilities.",
        image = R.drawable.slide1
    ),
    OnBoardingData(
        title = "Explore detailed maps, stats and key locations.",
        image = R.drawable.slide2
    ),
    OnBoardingData(
        title = "Compare guns, stats, and performance metrics.",
        image = R.drawable.slide3
    ),
    OnBoardingData(
        title = "Customize your favorite loadouts and gear.",
        image = R.drawable.slide4
    ),
    OnBoardingData(
        title = "Access comprehensive game data and insights.",
        image = R.drawable.slide5
    ),
)

fun getOnBoardingDataList(): List<OnBoardingData> {
    return OnBoardingDataList
}