package com.stealthx.valorantcompanion.presentation.agentscreen

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
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.stealthx.valorantcompanion.R
import com.stealthx.valorantcompanion.models.agents.Agent
import com.stealthx.valorantcompanion.navigation.appnavigation.AgentDetailScreenRoute
import com.stealthx.valorantcompanion.presentation.common.ErrorScreen
import com.stealthx.valorantcompanion.presentation.common.LoadingScreen
import com.stealthx.valorantcompanion.ui.theme.ValoBlack
import com.stealthx.valorantcompanion.ui.theme.ValoGrey
import com.stealthx.valorantcompanion.ui.theme.ValoRed
import com.stealthx.valorantcompanion.ui.theme.ValoWhite
import com.stealthx.valorantcompanion.utils.Resource

@Composable
fun AgentScreen(
    agentViewModel: AgentViewModel = hiltViewModel(),
    appNavHostController: NavHostController,
) {
    val agentsState by agentViewModel.agentsState.collectAsState()

    val searchState = remember {
        mutableStateOf("")
    }

    AgentScreenDesign(agentsState, searchState, appNavHostController)
}

@Composable
fun AgentScreenDesign(
    agentsState: Resource<List<Agent>>,
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
                    color = if (isSystemInDarkTheme()) ValoGrey else  ValoBlack,
                    text = "Find your Favourite Agent!",
                    fontFamily = FontFamily(Font(R.font.aeonik)),
                    fontWeight = FontWeight.Bold
                )
            },
            leadingIcon = {
                IconButton(
                    enabled = false,
                    onClick = {  }) {
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

        when (agentsState) {
            is Resource.Loading -> LoadingScreen()
            is Resource.Success -> {
                val agents = agentsState.data ?: emptyList()
                val filteredAgents = agents.filter { agent ->
                    agent.displayName.contains(searchState.value, ignoreCase = true)
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                ) {
                    items(filteredAgents) { agent ->
                        AgentCard(agent, appNavHostController)
                    }
                }
            }

            is Resource.Error -> ErrorScreen()
        }
    }
}

@Composable
fun AgentCard(agent: Agent, appNavHostController: NavHostController) {
    val backgroundModel = ImageRequest.Builder(LocalContext.current)
        .data(agent.background)
        .crossfade(true)
        .crossfade(100)
        .build()

    val imageModel = ImageRequest.Builder(LocalContext.current)
        .data(agent.fullPortrait)
        .crossfade(true)
        .crossfade(100)
        .build()

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxWidth()
            .height(270.dp)
            .padding(horizontal = 7.dp)
    ) {
        Column (
            modifier = Modifier
                .clickable(
                    onClick = {
                        appNavHostController.navigate(AgentDetailScreenRoute(agent.uuid))
                    }
                )
        ) {
            Text(
                color = ValoRed,
                text = agent.displayName,
                fontFamily = FontFamily(Font(R.font.valorant)),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 5.dp, end = 5.dp),
                textAlign = TextAlign.Justify,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )

            Surface(
                color = ValoRed,
                shape = RoundedCornerShape(topEnd = 25.dp, bottomStart = 25.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.7f)
                    .padding(horizontal = 5.dp)
            ) {
                Image(
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = R.drawable.slide3),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(ValoRed, blendMode = BlendMode.Multiply),
                )

                AsyncImage(
                    colorFilter = ColorFilter.tint(ValoWhite),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 70.dp, end = 10.dp),
                    model = backgroundModel,
                    contentDescription = null
                )
            }
        }

        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            contentScale = ContentScale.Crop,
            model = imageModel,
            contentDescription = null
        )
    }
}