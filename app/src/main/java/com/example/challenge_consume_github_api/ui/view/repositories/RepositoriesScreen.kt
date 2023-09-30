package com.example.challenge_consume_github_api.ui.view.repositories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Update
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.challenge_consume_github_api.data.remote.dto.RepositoriesDTO
import com.example.challenge_consume_github_api.ui.components.ErrorComponent
import com.example.challenge_consume_github_api.ui.components.LoadingComponent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun RepositoriesScreen(
    navController: NavController, login: String
) {
    val viewModel = hiltViewModel<RepositoriesViewModel>()

    viewModel.getRepositories(login)

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        viewModel.state.value.let { state ->
            when (state) {
                is RepositoriesViewModel.RepositoriesUiState.Loading -> LoadingComponent()
                is RepositoriesViewModel.RepositoriesUiState.Success -> List(
                    state.list, navController
                )

                is RepositoriesViewModel.RepositoriesUiState.Error -> ErrorComponent(
                    state.error ?: ""
                )
            }
        }
    }

}

@Composable
fun List(list: List<RepositoriesDTO>, navController: NavController) {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        items(list) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterHorizontally),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.W700,
                        text = it.name!!
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                    ) {
                        it.language?.let {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    Icons.Rounded.Language,
                                    contentDescription = "icon language",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.W700,
                                    text = it
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                Icons.Rounded.Star,
                                contentDescription = "icon star",
                                modifier = Modifier.padding(8.dp)
                            )
                            Text(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Normal,
                                text = it.stargazers_count!!.toString()
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        it.created_at?.let {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    Icons.Rounded.Create,
                                    contentDescription = "icon create",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    text = LocalDateTime.parse(it, formatter).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                )
                            }
                        }

                        it.updated_at?.let {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Icon(
                                    Icons.Rounded.Update,
                                    contentDescription = "icon update",
                                    modifier = Modifier.padding(8.dp)
                                )
                                Text(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Normal,
                                    text = LocalDateTime.parse(it, formatter).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}