package com.example.challenge_consume_github_api.ui.view.userdetails

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.remote.dto.UserDetailsDTO
import com.example.challenge_consume_github_api.domain.usecase.GetUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val getUserDetails: GetUserDetailsUseCase
) : ViewModel() {

    val state = mutableStateOf<UserDetailsUiState>(UserDetailsUiState.Loading)
    fun getDetails(login: String) {
        viewModelScope.launch {
            delay(1000)
            getUserDetails(login).collect(::handleResponse)
        }
    }

    private fun handleResponse(it: Resource<UserDetailsDTO>) {
        when (it.status) {
            Resource.Status.LOADING -> state.value = UserDetailsUiState.Loading
            Resource.Status.SUCCESS -> state.value =
                UserDetailsUiState.Success(data = it.data!!)

            Resource.Status.ERROR -> state.value =
                UserDetailsUiState.Error(error = it.error)
        }
    }


    sealed class UserDetailsUiState {
        data class Success(val data: UserDetailsDTO) : UserDetailsUiState()
        object Loading : UserDetailsUiState()
        class Error(val error: String? = null) : UserDetailsUiState()
    }
}