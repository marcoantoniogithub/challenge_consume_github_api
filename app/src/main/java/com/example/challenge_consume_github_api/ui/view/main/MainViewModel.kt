package com.example.challenge_consume_github_api.ui.view.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import com.example.challenge_consume_github_api.domain.usecase.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAllUsers: GetAllUsersUseCase
) : ViewModel() {

    val state = mutableStateOf<MainUiState>(MainUiState.Loading)
    fun getRepositories() {
        viewModelScope.launch {
            delay(1000)
            getAllUsers().collect(::handleResponse)
        }
    }

    private fun handleResponse(it: Resource<List<UsersDTO>>) {
        when (it.status) {
            Resource.Status.LOADING -> state.value = MainUiState.Loading
            Resource.Status.SUCCESS -> state.value = MainUiState.Success(list = it.data!!)
            Resource.Status.ERROR -> state.value =
                MainUiState.Error(error = it.error)
        }
    }

    sealed class MainUiState {
        data class Success(val list: List<UsersDTO>): MainUiState()
        object Loading: MainUiState()

        class Error(val error: String? = null) : MainUiState()
    }

}