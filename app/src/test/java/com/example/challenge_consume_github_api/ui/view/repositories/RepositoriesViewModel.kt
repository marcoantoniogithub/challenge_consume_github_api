package com.example.challenge_consume_github_api.ui.view.repositories

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.remote.dto.RepositoriesDTO
import com.example.challenge_consume_github_api.domain.usecase.GetAllRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getAllRepositories: GetAllRepositoriesUseCase
) : ViewModel() {

    val state = mutableStateOf<RepositoriesUiState>(RepositoriesUiState.Loading)
    fun getRepositories(login: String) {
        viewModelScope.launch {
            getAllRepositories(login).collect(::handleResponse)
        }
    }

    private fun handleResponse(it: Resource<List<RepositoriesDTO>>) {
        when (it.status) {
            Resource.Status.LOADING -> state.value = RepositoriesUiState.Loading
            Resource.Status.SUCCESS -> state.value = RepositoriesUiState.Success(list = it.data!!)
            Resource.Status.ERROR -> state.value =
                RepositoriesUiState.Error(error = it.error)
        }
    }

    sealed class RepositoriesUiState {
        data class Success(val list: List<RepositoriesDTO>) : RepositoriesUiState()
        object Loading : RepositoriesUiState()

        class Error(val error: String? = null) : RepositoriesUiState()
    }

}