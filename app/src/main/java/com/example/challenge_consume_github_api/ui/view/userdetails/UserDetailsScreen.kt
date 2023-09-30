package com.example.challenge_consume_github_api.ui.view.userdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LibraryBooks
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.challenge_consume_github_api.data.remote.dto.UserDetailsDTO
import com.example.challenge_consume_github_api.ui.components.ErrorComponent
import com.example.challenge_consume_github_api.ui.components.LoadingComponent
import com.example.challenge_consume_github_api.ui.navigation.Screen

@Composable
fun UserDetailsScreen(navController: NavController, id: String) {

    val viewModel = hiltViewModel<UserDetailsViewModel>()

    viewModel.getDetails(id)

    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
    ) {
        viewModel.state.value.let { state ->
            when (state) {
                is UserDetailsViewModel.UserDetailsUiState.Loading -> LoadingComponent()
                is UserDetailsViewModel.UserDetailsUiState.Success -> Details(
                    state.data, navController
                )

                is UserDetailsViewModel.UserDetailsUiState.Error -> ErrorComponent(
                    state.error ?: ""
                )
            }
        }
    }
}

@Composable
fun Details(data: UserDetailsDTO, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(120.dp)
                    .padding(16.dp)
                    .clip(CircleShape),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
                model = data.avatar_url!!,
                contentDescription = "image avatar"
            )
            Column {
                data.name?.let {
                    Text(
                        modifier = Modifier
                            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
                            .align(Alignment.Start),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.W700,
                        text = it
                    )
                }
                data.login?.let {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
                            .align(Alignment.Start),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Light,
                        text = it
                    )
                }
            }
        }
        data.location?.let {
            Row(
                modifier = Modifier.align(Alignment.Start)
                    .padding(top = 16.dp)
            ) {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = "icon location",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    text = it
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                Icons.Rounded.Person,
                contentDescription = "icon person",
                modifier = Modifier.padding(16.dp)
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                text = data.followers!!.toString().plus(" followers")
            )
            Text(
                modifier = Modifier.padding(start = 8.dp ,top = 16.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                text = data.following!!.toString().plus(" following")
            )
        }
        data.email?.let {
            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    Icons.Rounded.Email,
                    contentDescription = "icon email",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    modifier = Modifier.padding(16.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    text = it
                )
            }
        }

        data.company?.let {
            Row(
                modifier = Modifier.align(Alignment.Start)
            ) {
                Icon(
                    Icons.Filled.Apartment,
                    contentDescription = "icon company",
                    modifier = Modifier.padding(16.dp)
                )
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    text = it
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.Start)
        ) {
            Icon(
                Icons.Rounded.LibraryBooks,
                contentDescription = "icon email",
                modifier = Modifier.padding(16.dp)
            )

            Text(
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                text = data.public_repos.toString().plus(" repositories")
            )
        }

        data.bio?.let {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Start),
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                text = it
            )
        }

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Button(modifier = Modifier
                .padding(top = 16.dp)
                .align(Alignment.Bottom), onClick = {
                navController.navigate(
                    Screen.RepositoriesScreen.route.plus(
                        "/${data.login}"
                    )
                )
            }) {
                Text(text = "see repositories")
            }
        }
    }

}