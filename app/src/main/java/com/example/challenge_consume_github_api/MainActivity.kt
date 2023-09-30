package com.example.challenge_consume_github_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.challenge_consume_github_api.ui.navigation.NavGraph
import com.example.challenge_consume_github_api.ui.theme.Challenge_consume_github_apiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Challenge_consume_github_apiTheme {
                NavGraph()
            }
        }
    }
}
