package dev.dgomes.githubRepositories.data.repositories

import dev.dgomes.githubRepositories.data.model.RepositoryData
import kotlinx.coroutines.flow.Flow

interface RepoRepository {

    suspend fun listRepositories(user: String): Flow<List<RepositoryData>>

}