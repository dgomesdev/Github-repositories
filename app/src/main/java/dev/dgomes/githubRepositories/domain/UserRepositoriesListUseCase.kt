package dev.dgomes.githubRepositories.domain

import dev.dgomes.githubRepositories.core.UseCase
import dev.dgomes.githubRepositories.data.model.RepositoryData
import dev.dgomes.githubRepositories.data.repositories.RepoRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoriesListUseCase(private val repository: RepoRepository) :
    UseCase<String, List<RepositoryData>>() {

    override suspend fun execute(param: String): Flow<List<RepositoryData>> {
        return repository.listRepositories(param)
    }
}