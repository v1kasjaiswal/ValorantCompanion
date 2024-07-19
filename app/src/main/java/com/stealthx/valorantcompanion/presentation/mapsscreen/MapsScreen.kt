package com.stealthx.valorantcompanion.presentation.mapsscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.maps.VMap
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoBlue
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource

@Composable
fun MapsScreen(
    mapsViewModel: MapsViewModel = hiltViewModel()
) {
    val mapsState by mapsViewModel.mapsState.collectAsState()

    MapsScreenDesign(mapsState)
}

@Composable
fun MapsScreenDesign(
    mapsState: Resource<List<VMap>>
) {
    when (mapsState) {
        is Resource.Loading -> LoadingScreen()
        is Resource.Success -> {
            val maps = mapsState.data ?: emptyList()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) ValoBlack else ValoWhite)
            ) {
                items(maps) { map ->
                    MapCard(map)
                }
            }
        }

        is Resource.Error -> ErrorScreen()
    }
}

@Composable
fun MapCard(map: VMap) {
    val mapModel = ImageRequest.Builder(LocalContext.current)
        .data(map.splash)
        .crossfade(true)
        .build()

    Card(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .padding(vertical = 8.dp, horizontal = 15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            AsyncImage(
                contentScale = ContentScale.Crop,
                model = mapModel,
                contentDescription = null,
                modifier = Modifier.drawWithCache {
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

            Column (
                modifier = Modifier.fillMaxSize().padding(vertical = 15.dp, horizontal = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = if (map.coordinates.equals(null)) "Hidden Location" else map.coordinates.toString(),
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    style = MaterialTheme.typography.labelMedium,
                    color = ValoWhite,
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(ValoBlue)
                        .padding(vertical = 5.dp, horizontal = 10.dp)
                )

                Text(
                    modifier = Modifier.padding(end = 5.dp),
                    text = map.displayName,
                    fontFamily = FontFamily(Font(R.font.valorant)),
                    style = MaterialTheme.typography.headlineLarge,
                    color = ValoWhite,
                    textAlign = TextAlign.End,
                )

            }
        }
    }
}