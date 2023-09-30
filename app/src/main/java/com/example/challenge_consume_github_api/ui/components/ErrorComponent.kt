package com.example.challenge_consume_github_api.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorComponent(text: String) {

    Box(
        modifier = Modifier
            .background(Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .height(200.dp)
                .padding(16.dp)
                .background(Color( 0xFFDB3D3D))
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Error Icon",
                modifier = Modifier
                    .size(96.dp)
                    .padding(16.dp),
                tint = Color.White
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                text = text,
                style = MaterialTheme.typography.titleSmall,
                color = Color.White
            )
        }
    }
}