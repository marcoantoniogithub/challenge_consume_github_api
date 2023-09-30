package com.example.challenge_consume_github_api.data.remote.api

import com.example.challenge_consume_github_api.data.remote.dto.RepositoriesDTO
import com.example.challenge_consume_github_api.data.remote.dto.UserDetailsDTO
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubAPI {
    @GET("users")
    suspend fun getAllUsers(): List<UsersDTO>

    @GET("users/{userLogin}")
    suspend fun getUserDetail(
        @Path("userLogin") userLogin: String,
    ): UserDetailsDTO

    @GET("users/{userLogin}/repos")
    suspend fun getAllRepositories(
        @Path("userLogin") userLogin: String,
    ): List<RepositoriesDTO>


}