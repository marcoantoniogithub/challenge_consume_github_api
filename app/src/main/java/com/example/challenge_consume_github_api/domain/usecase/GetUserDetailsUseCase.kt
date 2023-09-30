package com.example.challenge_consume_github_api.domain.usecase

import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.GitHubRepository
import com.example.challenge_consume_github_api.data.remote.dto.UserDetailsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: GitHubRepository
) {

    private lateinit var users: UserDetailsDTO
    suspend operator fun invoke(login: String): Flow<Resource<UserDetailsDTO>> =
        flow<Resource<UserDetailsDTO>> {
            try {
                emit(Resource.loading())
                users = repository.getUserDetail(login)
                emit(Resource.success(users))
            } catch (e: Exception) {
                emit(Resource.error(e))
            }
        }
}