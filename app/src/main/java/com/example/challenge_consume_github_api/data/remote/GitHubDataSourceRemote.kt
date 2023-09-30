package com.example.challenge_consume_github_api.data.remote

import com.example.challenge_consume_github_api.data.remote.api.GitHubAPI
import com.example.challenge_consume_github_api.data.remote.dto.RepositoriesDTO
import com.example.challenge_consume_github_api.data.remote.dto.UserDetailsDTO
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import javax.inject.Inject

class GitHubDataSourceRemote @Inject constructor(
    private val githubapi: GitHubAPI
) {
    suspend fun getAllUsers(): List<UsersDTO> {
        try {
            return githubapi.getAllUsers()
        } catch (e: Exception) {
            throw Exception(e)
        }
    }

    suspend fun getUserDetail(login: String): UserDetailsDTO {
        try {
            return githubapi.getUserDetail(login)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
    suspend fun getAllRepositories(login: String): List<RepositoriesDTO> {
        try {
            return githubapi.getAllRepositories(login)
        } catch (e: Exception) {
            throw Exception(e)
        }
    }
}