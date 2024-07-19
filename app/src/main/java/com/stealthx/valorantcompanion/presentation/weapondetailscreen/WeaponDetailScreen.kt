package com.stealthx.valorantcompanion.presentation.weapondetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.weapons.Skin
import com.stealthx.valorantcompanion.models.weapons.Weapon
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.presentation.common.SetupSystemBar
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoBlue
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource

@Composable
fun WeaponDetailScreen(
    weaponDetailViewModel: WeaponDetailViewModel = hiltViewModel(),
    uuid: String
) {
    val weaponDetailState by weaponDetailViewModel.weaponDetailState.collectAsState()

    LaunchedEffect(key1 = uuid) {
        weaponDetailViewModel.rogerWeaponDetails(uuid)
    }

    when (weaponDetailState) {
        is Resource.Error -> ErrorScreen()
        is Resource.Loading -> LoadingScreen()
        is Resource.Success -> WeaponDetailScreenDesign(weaponDetailState)
    }

}

@Composable
fun WeaponDetailScreenDesign(weaponDetailState: Resource<Weapon>) {

    val weaponDetail = weaponDetailState.data

    SetupSystemBar(true)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isSystemInDarkTheme()) ValoBlack else ValoWhite)
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.6f)
                .clip(RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp))
        ) {
            Image(
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
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.slide3),
                colorFilter = ColorFilter.tint(ValoRed, BlendMode.Multiply),
                contentDescription = null,
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(.9f)
                    .rotate(45f)
                    .padding(10.dp),
                model = weaponDetail?.displayIcon,
                contentDescription = null
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(end = 15.dp)
            ) {
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .background(ValoBlue)
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    text = if (weaponDetail?.shopData?.cost == null) "$0" else "$${weaponDetail.shopData.cost}",
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = ValoWhite,
                    style = MaterialTheme.typography.titleMedium
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxWidth(.5f),
                    model = weaponDetail?.killStreamIcon,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = weaponDetail?.displayName ?: "Melee",
                    fontFamily = FontFamily(Font(R.font.valorant)),
                    color = ValoWhite,
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = if (weaponDetail?.shopData?.categoryText != null) "Category: ${weaponDetail.shopData.categoryText}" else "Category: Melee",
                    fontFamily = FontFamily(Font(R.font.aeonik)),
                    style = MaterialTheme.typography.titleMedium,
                    color = ValoWhite,
                    modifier = Modifier.padding(bottom = 5.dp)
                )

            }
        }

        LazyColumn(
            modifier = Modifier.padding(bottom = 5.dp)
        ) {
            if (weaponDetail?.shopData?.categoryText != null) {

                item {

                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSystemInDarkTheme()) ValoBlue else Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 15.dp, vertical = 15.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp),
                                textAlign = TextAlign.Center,
                                text = "// WEAPON STATS",
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                style = MaterialTheme.typography.titleMedium,
                                color = ValoRed
                            )

                            HorizontalDivider(
                                color = ValoRed,
                                modifier = Modifier
                                    .fillMaxWidth(.9f)
                                    .padding(vertical = 5.dp)
                            )

                        }
                        if (weaponDetail.weaponStats?.magazineSize != null) {
                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                fontWeight = FontWeight.Bold,
                                text = "Magazine Size: ${weaponDetail.weaponStats.magazineSize} Bullets",
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            )
                        }

                        if (weaponDetail.weaponStats?.fireRate != null) {
                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                fontWeight = FontWeight.Bold,
                                text = "Fire Rate: ${weaponDetail.weaponStats.fireRate} RPS (Rounds per Second)",
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            )
                        }

                        if (weaponDetail.weaponStats?.equipTimeSeconds != null) {
                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                fontWeight = FontWeight.Bold,
                                text = "Equip Time: ${weaponDetail.weaponStats.equipTimeSeconds} seconds",
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            )
                        }

                        if (weaponDetail.weaponStats?.reloadTimeSeconds != null) {
                            Text(
                                modifier = Modifier.padding(horizontal = 15.dp),
                                fontWeight = FontWeight.Bold,
                                text = "Reload Time: ${weaponDetail.weaponStats.reloadTimeSeconds} seconds",
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            )
                        }

                        if (weaponDetail.weaponStats?.reloadTimeSeconds != null) {
                            Text(
                                modifier = Modifier.padding(start = 15.dp, bottom = 8.dp),
                                text = "Wall Penetration: ${
                                    weaponDetail.weaponStats.wallPenetration.substringAfter(
                                        "::"
                                    )
                                } level",
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                style = MaterialTheme.typography.bodyLarge,
                                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            )
                        }
                    }

                    HorizontalDivider(
                        color = ValoRed,
                        modifier = Modifier.padding(start = 15.dp, bottom = 5.dp, end = 15.dp)
                    )

                    Text(
                        modifier = Modifier.padding(start = 15.dp),
                        text = "// CHROMAS",
                        fontFamily = FontFamily(Font(R.font.poppins)),
                        style = MaterialTheme.typography.titleMedium,
                        color = ValoRed
                    )
                }
            }

            if (weaponDetail != null) {
                items(weaponDetail.skins) { skin ->
                    WeaponSkinCard(skin)
                }
            }

        }
    }
}

@Composable
fun WeaponSkinCard(skin: Skin) {

    val skinModel = ImageRequest.Builder(LocalContext.current)
        .data(skin.displayIcon)
        .crossfade(true)
        .build()

    if (skin.displayIcon != null) {
        Card(
            shape = RoundedCornerShape(25.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .padding(horizontal = 15.dp, vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isSystemInDarkTheme()) ValoBlue else Color.White
            )
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Image(
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
                    contentScale = ContentScale.Crop,
                    colorFilter = ColorFilter.tint(ValoRed, BlendMode.Darken),
                    painter = painterResource(id = R.drawable.gunbg2),
                    contentDescription = null
                )

                AsyncImage(
                    modifier = Modifier.fillMaxSize(.8f).padding(bottom = 25.dp),
                    model = skinModel,
                    contentDescription = null
                )

                Text(
                    text = skin.displayName.uppercase(),
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    color = ValoWhite,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 15.dp).basicMarquee(),
                    maxLines = 1,
                )
            }
        }
    }
}
