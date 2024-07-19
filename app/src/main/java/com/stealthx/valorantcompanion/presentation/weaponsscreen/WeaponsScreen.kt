package com.stealthx.valorantcompanion.presentation.weaponsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.weapons.Weapon
import com.stealthx.valorantcompanion.navigation.appnavigation.WeaponDetailScreenRoute
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoGrey
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource

@Composable
fun WeaponsScreen(
    weaponsViewModel: WeaponsViewModel = hiltViewModel(),
    appNavHostController: NavHostController
) {
    val weaponsState by weaponsViewModel.weaponsState.collectAsState()

    val searchState = remember {
        mutableStateOf("")
    }

    WeaponScreenDesign(weaponsState, searchState, appNavHostController)
}

@Composable
fun WeaponScreenDesign(
    weaponsState: Resource<List<Weapon>>,
    searchState: MutableState<String>,
    appNavHostController: NavHostController
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(
                if (isSystemInDarkTheme()) ValoBlack else ValoWhite
            ),
    ) {
        OutlinedTextField(
            placeholder = {
                Text(
                    color = if (isSystemInDarkTheme()) ValoGrey else ValoBlack,
                    text = "Find your Favourite Weapon!",
                    fontFamily = FontFamily(Font(R.font.aeonik)),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                IconButton(
                    enabled = false,
                    onClick = { }) {
                    Icon(
                        tint = ValoRed,
                        imageVector = Icons.Rounded.Search,
                        contentDescription = null
                    )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ValoRed,
                unfocusedBorderColor = ValoRed
            ),
            textStyle = TextStyle(
                color = ValoRed,
                fontFamily = FontFamily(Font(R.font.aeonik)),
                fontWeight = FontWeight.Bold
            ),
            shape = RoundedCornerShape(20.dp),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(.9f)
                .padding(bottom = 10.dp),
            value = searchState.value,
            onValueChange = {
                searchState.value = it
            }
        )

        when (weaponsState) {
            is Resource.Error -> ErrorScreen()
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize()
            ) {
                val weapons = weaponsState.data ?: emptyList()

                val filteredWeapons = weapons.filter { weapon ->
                    weapon.displayName.contains(searchState.value, ignoreCase = true)
                }

                items(filteredWeapons) { weapon ->
                    WeaponCard(weapon, appNavHostController)
                }
            }
        }
    }
}

@Composable
fun WeaponCard(weapon: Weapon, appNavHostController: NavHostController) {

    val weaponModel = ImageRequest.Builder(LocalContext.current)
        .data(weapon.displayIcon)
        .crossfade(true)
        .build()

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp)
            .padding(horizontal = 10.dp)
            .clickable(
                onClick = {
                    appNavHostController.navigate(WeaponDetailScreenRoute(weapon.uuid))
                }
            )
    ) {
        Column {
            Text(
                text = weapon.displayName,
                fontFamily = FontFamily(Font(R.font.valorant)),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                maxLines = 1
            )

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.65f),
                shape = RoundedCornerShape(bottomStart = 25.dp, topEnd = 25.dp)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
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
                    colorFilter = ColorFilter.tint(ValoRed, BlendMode.Color),
                    painter = painterResource(id = R.drawable.gunbg),
                    contentDescription = null
                )
            }
        }

        AsyncImage(
            modifier = Modifier
                .fillMaxSize(.95f)
                .rotate(45f),
            model = weaponModel,
            contentDescription = null
        )
    }
}
