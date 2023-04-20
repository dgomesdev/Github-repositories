package dev.dgomes.githubRepositories.data.model

import com.google.gson.annotations.SerializedName

data class RepositoryData (
    val id: Long,
    val name: String,
    val language: String,
    val description: String,
    @SerializedName("html_url")
    val htmlUrl: String,
        )