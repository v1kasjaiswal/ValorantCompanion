package com.stealthx.valorantcompanion.presentation.agentdetailsscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.presentation.common.SetupSystemBar
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoBlue
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource
import com.webtoonscorp.android.readmore.foundation.ReadMoreTextOverflow
import com.webtoonscorp.android.readmore.foundation.ToggleArea
import com.webtoonscorp.android.readmore.material3.ReadMoreText

@Composable
fun AgentDetailScreen(
    uuid: String,
    agentDetailViewModel: AgentDetailViewModel = hiltViewModel(),
) {
    SetupSystemBar()

    val agentDetailState by agentDetailViewModel.agentDetailState.collectAsState()
    LaunchedEffect(key1 = uuid) {
        agentDetailViewModel.rogerAgentDetails(uuid)
    }

    when (agentDetailState) {
        is Resource.Loading -> LoadingScreen()
        is Resource.Success -> AgentDetailScreenDesign(agentDetailState)
        is Resource.Error -> ErrorScreen()
    }
}

@Composable
fun AgentDetailScreenDesign(agentDetailState: Resource<Agent>) {
    val agentDetailData = agentDetailState.data!!

    val agentModel = ImageRequest.Builder(LocalContext.current)
        .data(agentDetailData.fullPortrait)
        .crossfade(true)
        .build()

    val backgroundModel = ImageRequest.Builder(LocalContext.current)
        .data(agentDetailData.background)
        .crossfade(true)
        .build()

    val (descriptionExpanded, onExpandedChange) = rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) ValoBlack else ValoWhite)
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 80.dp, bottomEnd = 80.dp))
                .fillMaxWidth()
                .height(520.dp)
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.tint(ValoRed, blendMode = BlendMode.Multiply),
                painter = painterResource(id = R.drawable.splash_bg),
                alpha = .5f,
                contentDescription = null
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 80.dp),
                colorFilter = ColorFilter.tint(ValoWhite),
                model = backgroundModel,
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp)
                    .alpha(.7f)
                    .basicMarquee(velocity = 70.dp, iterations = 7, spacing = MarqueeSpacing(0.dp)),
                textAlign = TextAlign.Center,
                text = "${agentDetailData.displayName}•".repeat(3),
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily(Font(R.font.valorant)),
                fontSize = 95.sp,
                maxLines = 1,
                color = ValoBlack,
            )

            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentScale = ContentScale.Crop,
                model = agentModel,
                contentDescription = null,
            )

            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .rotate(-90f)
                    .padding(start = 0.dp, top = 90.dp),
                text = agentDetailData.role.displayName,
                maxLines = 1,
                fontFamily = FontFamily(Font(R.font.valorant)),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Start,
                color = ValoWhite
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = 10.dp)
        ) {

            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "// BIOGRAPHY",
                fontFamily = FontFamily(Font(R.font.poppins)),
                style = MaterialTheme.typography.titleMedium,
                color = ValoRed
            )

            ReadMoreText(
                modifier = Modifier.padding(horizontal = 15.dp),

                text = agentDetailData.description,
                expanded = descriptionExpanded,
                onExpandedChange = onExpandedChange,
                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                fontFamily = FontFamily(Font(R.font.aeonik)),
                style = MaterialTheme.typography.bodyLarge,

                readMoreText = "read more",
                readMoreColor = ValoRed,
                readMoreFontWeight = FontWeight.Bold,
                readMoreFontFamily = FontFamily(Font(R.font.aeonik)),
                readMoreMaxLines = 3,
                readMoreOverflow = ReadMoreTextOverflow.Ellipsis,


                readLessText = "read less",
                readLessColor = ValoRed,
                readLessFontFamily = FontFamily(Font(R.font.aeonik)),
                readLessFontWeight = FontWeight.Bold,

                toggleArea = ToggleArea.More
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                thickness = 1.dp,
                color = ValoRed
            )

            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "// BATTLE ROLE",
                fontFamily = FontFamily(Font(R.font.poppins)),
                style = MaterialTheme.typography.titleMedium,
                color = ValoRed
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(horizontal = 15.dp, vertical = 5.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (isSystemInDarkTheme()) ValoBlue else Color.White
                )
            ) {
                Row(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = ValoRed
                        ),
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(.25f)
                            .padding(9.dp)
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            model = agentDetailData.role.displayIcon,
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(ValoWhite)
                        )
                    }

                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp, end = 10.dp),
                            maxLines = 1,
                            text = agentDetailData.role.displayName.uppercase(),
                            fontFamily = FontFamily(Font(R.font.poppins)),
                            style = MaterialTheme.typography.titleMedium,
                            letterSpacing = 1.5.sp,
                            color = ValoRed
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(end = 5.dp),
                            text = agentDetailData.role.description,
                            fontFamily = FontFamily(Font(R.font.aeonik)),
                            style = MaterialTheme.typography.bodyMedium,
                            color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                thickness = 1.dp,
                color = ValoRed
            )

            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "// ABILITIES",
                fontFamily = FontFamily(Font(R.font.poppins)),
                style = MaterialTheme.typography.titleMedium,
                color = ValoRed
            )

            agentDetailData.abilities.forEach { ability ->
                val abilityModel = ImageRequest.Builder(LocalContext.current)
                    .data(ability.displayIcon)
                    .crossfade(true)
                    .build()

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(horizontal = 15.dp, vertical = 5.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSystemInDarkTheme()) ValoBlue else Color.White
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Card(
                            colors = CardDefaults.cardColors(
                                containerColor = ValoRed
                            ),
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(.25f)
                                .padding(7.dp)
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                model = abilityModel,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(ValoWhite)
                            )
                        }

                        Column {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 5.dp, end = 10.dp),
                                maxLines = 1,
                                text = ability.displayName.uppercase(),
                                fontFamily = FontFamily(Font(R.font.poppins)),
                                style = MaterialTheme.typography.titleMedium,
                                letterSpacing = 1.5.sp,
                                color = ValoRed
                            )

                            Text(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(end = 5.dp),
                                text = ability.description,
                                fontFamily = FontFamily(Font(R.font.aeonik)),
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                }
            }

            if (agentDetailData.characterTags?.isNotEmpty() == true) {
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                    thickness = 1.dp,
                    color = ValoRed
                )

                Text(
                    modifier = Modifier.padding(start = 15.dp),
                    text = "// CHARACTER TAGS",
                    fontFamily = FontFamily(Font(R.font.poppins)),
                    style = MaterialTheme.typography.titleMedium,
                    color = ValoRed
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 8.dp)
                ) {
                    agentDetailData.characterTags.forEach { tag ->
                        Text(
                            modifier = Modifier
                                .clip(RoundedCornerShape(20.dp))
                                .background(if (isSystemInDarkTheme()) ValoBlue else Color.White)
                                .padding(vertical = 5.dp, horizontal = 15.dp),
                            text = "#$tag",
                            color = if (isSystemInDarkTheme()) ValoWhite else ValoBlack,
                            fontFamily = FontFamily(Font(R.font.aeonik)),
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 15.dp),
                thickness = 1.dp,
                color = ValoRed
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp),
                textAlign = TextAlign.Center,
                text = "// CHARACTER DESIGNER: ${agentDetailData.developerName.uppercase()}",
                fontFamily = FontFamily(Font(R.font.poppins)),
                style = MaterialTheme.typography.titleMedium,
                color = ValoRed
            )
        }
    }
}
