package com.example.challenge_consume_github_api.data.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoriesDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("node_id") val node_id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("full_name") val full_name: String?,
    @SerializedName("owner") val owner: OwnerDTO?,
    @SerializedName("description") val description: String?,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("updated_at") val updated_at: String?,
    @SerializedName("stargazers_count") val stargazers_count: Int?,
    @SerializedName("watchers_count") val watchers_count: Int?,
    @SerializedName("language") val language: String?,

): Parcelable


@Parcelize
data class OwnerDTO(
    @SerializedName("id") val id: Int?,
    @SerializedName("login") val login: String?,
    @SerializedName("node_id") val node_id: String?,
    @SerializedName("full_name") val full_name: String?,
    @SerializedName("avatar_url") val avatar_url: String?,
): Parcelable

