package dev.dgomes.githubRepositories.domain.di

import dev.dgomes.githubRepositories.domain.UserRepositoriesListUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {

    fun load() {
        loadKoinModules(useCaseModule())
    }

    private fun useCaseModule(): Module {
        return module {
            factory {
                UserRepositoriesListUseCase(get())
            }
        }
    }

}