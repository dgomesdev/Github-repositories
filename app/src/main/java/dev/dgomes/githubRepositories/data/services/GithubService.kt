package dev.dgomes.githubRepositories.data.services

import dev.dgomes.githubRepositories.data.model.RepositoryData
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun repositoryList(@Path("user") user: String): List<RepositoryData>
}