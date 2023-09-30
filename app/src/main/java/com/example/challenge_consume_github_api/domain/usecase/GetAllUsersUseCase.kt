package com.example.challenge_consume_github_api.domain.usecase

import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.GitHubRepository
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllUsersUseCase @Inject constructor(
    private val repository: GitHubRepository
) {

    private var users = listOf<UsersDTO>()
    suspend operator fun invoke(): Flow<Resource<List<UsersDTO>>> =
        flow<Resource<List<UsersDTO>>> {
            try {
                emit(Resource.loading())
                users = repository.getAllUsers()
                emit(Resource.success(users))
            } catch (e: Exception) {
                emit(Resource.error(e))
            }
        }
}