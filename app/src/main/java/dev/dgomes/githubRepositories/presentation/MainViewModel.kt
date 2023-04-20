package dev.dgomes.githubRepositories.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dgomes.githubRepositories.data.model.RepositoryData
import dev.dgomes.githubRepositories.domain.UserRepositoriesListUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainViewModel(private val userRepositoriesListUseCase: UserRepositoriesListUseCase) : ViewModel() {

    private val _repos = MutableLiveData<State>()
    val repos: LiveData<State> = _repos

    fun getRepoList(user: String) {
        viewModelScope.launch {
            userRepositoriesListUseCase(user)
                .onStart {
                    _repos.postValue(State.Loading)
                }.catch {
                    _repos.postValue(State.Error(it))
                }.collect{
                    _repos.postValue(State.Success(it))
                }
        }
    }

    sealed class State {
        object Loading : State()

        data class Success(
            val list: List<RepositoryData>
        ) : State()

        data class Error(
            val error: Throwable
        )        : State()
    }

}