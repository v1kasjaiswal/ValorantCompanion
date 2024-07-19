package com.stealthx.valorantcompanion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.stealthx.valorantcompanion.data.remote.api.AgentsAPI
import com.stealthx.valorantcompanion.navigation.appnavigation.SetupNavigation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var agentsAPI: AgentsAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SetupNavigation()
            
//            AgentDetailScreen(uuid = "e370fa57-4757-3604-3648-499e1f642d3f")
        }
    }
}
