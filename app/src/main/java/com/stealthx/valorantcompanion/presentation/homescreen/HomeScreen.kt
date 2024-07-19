package com.stealthx.valorantcompanion.presentation.homescreen

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.currencies.Currency
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoBlue
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = hiltViewModel()
) {
    val currencyState by homeScreenViewModel.currencyState.collectAsState()

    HomeScreenDesign(currencyState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDesign(currencyState: Resource<List<Currency>>) {

    val carouselState = rememberCarouselState { 6 }

    Column(
        modifier = Modifier
            .background(if (isSystemInDarkTheme()) ValoBlack else ValoWhite)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "// What's Up Soldier! 😎 ".uppercase(),
            fontFamily = FontFamily(Font(R.font.poppins)),
            color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(10.dp))


        HorizontalUncontainedCarousel(
            modifier = Modifier
                .height(180.dp)
                .padding(horizontal = 15.dp)
                .clip(RoundedCornerShape(25.dp)),
            state = carouselState,
            itemWidth = 320.dp,
            itemSpacing = 10.dp,
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier.fillMaxSize()
            ) {
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
                    painter = when (it) {
                        0 -> painterResource(id = R.drawable.gunbg)
                        1 -> painterResource(id = R.drawable.event1)
                        2 -> painterResource(id = R.drawable.event2)
                        3 -> painterResource(id = R.drawable.carousel1)
                        4 -> painterResource(id = R.drawable.carousel2)
                        5 -> painterResource(id = R.drawable.carousel3)
                        else -> painterResource(id = R.drawable.gunbg)
                    },
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(bottom = 10.dp),
                    text = "ValorAnt",
                    fontFamily = FontFamily(Font(R.font.valorant)),
                    style = MaterialTheme.typography.headlineMedium,
                    color = ValoWhite
                )
            }
        }

        HorizontalDivider(
            color = ValoRed,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp),
        )

        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "// CURRENCIES",
            fontFamily = FontFamily(Font(R.font.poppins)),
            style = MaterialTheme.typography.titleMedium,
            color = ValoRed
        )

        when (currencyState) {
            is Resource.Error -> ErrorScreen()
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> {
                val currencies = currencyState.data ?: emptyList()

                Row (
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 8.dp)
                ) {
                    currencies.forEach{ currency ->
                        CurrencyCard(currency = currency)
                    }
                }

            }
        }

        HorizontalDivider(
            color = ValoRed,
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp),
        )

        Text(
            modifier = Modifier.padding(start = 15.dp),
            text = "// UPCOMING EVENTS",
            fontFamily = FontFamily(Font(R.font.poppins)),
            style = MaterialTheme.typography.titleMedium,
            color = ValoRed
        )

        EventCard(eventImage = R.drawable.event3)
        EventCard(eventImage = R.drawable.event1)
        EventCard(eventImage = R.drawable.event2)
        EventCard(eventImage = R.drawable.event4)

    }
}

@Composable
fun EventCard( @DrawableRes eventImage: Int) {
    Card(
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 15.dp, vertical = 8.dp)
    ) {
        Image(
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize().drawWithCache {
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
            painter = painterResource(id = eventImage),
            contentDescription = null
        )
    }
}

@Composable
fun CurrencyCard(currency: Currency) {
    Card(
        modifier = Modifier
            .width(90.dp)
            .height(75.dp)
            .padding(horizontal = 5.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) ValoBlue else Color.White
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            colorFilter = ColorFilter.tint(
                if (isSystemInDarkTheme()) ValoWhite else ValoBlack
            ),
            model = currency.displayIcon,
            contentDescription = null
        )
    }
}
