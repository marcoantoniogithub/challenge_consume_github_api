package com.example.challenge_consume_github_api.domain.usecase

import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.GitHubRepository
import com.example.challenge_consume_github_api.data.remote.dto.RepositoriesDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllRepositoriesUseCase @Inject constructor(
    private val repository: GitHubRepository
) {

    private var users = listOf<RepositoriesDTO>()
    suspend operator fun invoke(login: String): Flow<Resource<List<RepositoriesDTO>>> =
        flow<Resource<List<RepositoriesDTO>>> {
            try {
                emit(Resource.loading())
                users = repository.getAllRepositories(login)
                emit(Resource.success(users))
            } catch (e: Exception) {
                emit(Resource.error(e))
            }
        }
}