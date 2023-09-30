package com.example.challenge_consume_github_api.data

import com.example.challenge_consume_github_api.data.remote.GitHubDataSourceRemote
import com.example.challenge_consume_github_api.data.remote.dto.RepositoriesDTO
import com.example.challenge_consume_github_api.data.remote.dto.UserDetailsDTO
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class GitHubRepository @Inject constructor(
    val remote: GitHubDataSourceRemote
) {

    suspend fun getAllUsers(): List<UsersDTO> {
        try {
            return remote.getAllUsers()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getUserDetail(login: String): UserDetailsDTO {
        try {
            return remote.getUserDetail(login)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
    suspend fun getAllRepositories(login: String): List<RepositoriesDTO> {
        try {
            return remote.getAllRepositories(login)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}