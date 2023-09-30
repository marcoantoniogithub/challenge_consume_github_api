package com.example.challenge_consume_github_api.ui.view.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.challenge_consume_github_api.ui.navigation.Screen
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import com.example.challenge_consume_github_api.ui.components.ErrorComponent
import com.example.challenge_consume_github_api.ui.components.LoadingComponent

@Composable
fun MainScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<MainViewModel>()

    viewModel.getRepositories()

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        viewModel.state.value.let { state ->
            when (state) {
                is MainViewModel.MainUiState.Loading -> LoadingComponent()
                is MainViewModel.MainUiState.Success -> List(state.list, navController)
                is MainViewModel.MainUiState.Error -> ErrorComponent(state.error ?: "")
            }
        }
    }

}

@Composable
fun List(list: List<UsersDTO>, navController: NavController) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(list) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clickable {
                      navController.navigate(
                          Screen.UserDetailsScreen.route.plus(
                          "/${it.login}"
                      ))
                    },
                border = BorderStroke(2.dp, Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    AsyncImage(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxSize(),
                        alignment = Alignment.Center,
                        contentScale = ContentScale.Crop,
                        model = it.avatar_url!!,
                        contentDescription = "image avatar"
                    )
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.W700,
                        text = it.login!!
                    )
                }

            }
        }
    }
}


