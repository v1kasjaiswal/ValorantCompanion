package com.stealthx.valorantcompanion.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.stealthx.valorantcompanion.ui.theme.ValoGrey
import com.stealthx.valorantcompanion.ui.theme.ValoRed

@Composable
fun OnBoardingSliderIndicator(
    modifier: Modifier = Modifier,
    totalPages: Int,
    selectedPage: Int,
    selectedPageColor: Color = ValoRed,
    unselectedPageColor: Color = ValoGrey
) {
    Row(
        modifier = modifier
    ) {
        repeat(totalPages) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 6.dp)
                    .height(5.dp)
                    .width(20.dp),
                shape = RoundedCornerShape(bottomStart = 12.dp, topEnd = 12.dp),
                color = if (selectedPage == it) selectedPageColor else unselectedPageColor
            ) { }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun ShowPreview() {
    OnBoardingSliderIndicator(totalPages = 5, selectedPage = 3)
}