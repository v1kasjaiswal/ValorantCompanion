package com.stealthx.valorantcompanion.presentation.modesscreen

import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.modes.Mode
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoBlue
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource

@Composable
fun ModesScreen(
    modesViewModel: ModesViewModel = hiltViewModel()
) {
    val modesState by modesViewModel.modesState.collectAsState()

    ModesScreenDesign(modesState)
}

@Composable
fun ModesScreenDesign(modesState: Resource<List<Mode>>) {

    when (modesState) {
        is Resource.Loading -> LoadingScreen()
        is Resource.Success -> {
            val modes = modesState.data ?: emptyList()

            val filteredModes = modes.filter {
                it.listViewIconTall != null && it.displayIcon != null
            }

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) ValoBlack else ValoWhite)
            ) {
                items(filteredModes) { mode ->
                    ModesCard(mode)
                }
            }
        }

        is Resource.Error -> ErrorScreen()
    }
}


@Composable
fun ModesCard(mode: Mode) {
    val modeModel = ImageRequest.Builder(LocalContext.current)
        .data(mode.listViewIconTall)
        .crossfade(true)
        .build()

    Card(
        colors = CardDefaults.cardColors(
            containerColor = if (isSystemInDarkTheme()) ValoBlue else Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(430.dp)
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.BottomCenter,
                modifier = Modifier
                    .fillMaxHeight(.70f)
                    .fillMaxWidth()
            ) {
                AsyncImage(
                    colorFilter = ColorFilter.tint(ValoWhite, blendMode = BlendMode.Darken),
                    contentScale = ContentScale.Crop,
                    model = modeModel,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp))
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
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(45.dp),
                        model = mode.displayIcon,
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier
                            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
                            .basicMarquee(
                                velocity = 80.dp,
                                spacing = MarqueeSpacing(50.dp)
                            ),
                        text = mode.displayName.uppercase(),
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        color = ValoWhite,
                        style = MaterialTheme.typography.titleLarge,
                        maxLines = 1
                    )
                }
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                text = mode.duration ?: "Duration Not Defined",
                fontFamily = FontFamily(Font(R.font.poppins)),
                style = MaterialTheme.typography.titleSmall,
                textAlign = TextAlign.Center,
                color = ValoRed
            )

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp, end = 10.dp, bottom = 5.dp),
                text = mode.description ?: "Aah! No Description Found!",
                fontFamily = FontFamily(Font(R.font.aeonik)),
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis,
                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack
            )

        }
    }
}