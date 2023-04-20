package dev.dgomes.githubRepositories.data.repositories

import dev.dgomes.githubRepositories.data.services.GithubService
import kotlinx.coroutines.flow.flow

class RepoRepositoryImpl(private val service: GithubService) : RepoRepository {
    override suspend fun listRepositories(user: String) = flow {
        val repositoryList = service.repositoryList(user)
        emit(repositoryList)
    }
}