package dev.dgomes.githubRepositories.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import dev.dgomes.githubRepositories.core.createProgressDialog
import dev.dgomes.githubRepositories.core.hideSoftKeyboard
import dev.dgomes.githubRepositories.databinding.ActivityMainBinding
import dev.dgomes.githubRepositories.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val mainViewModel by viewModel<MainViewModel>()
    private val adapter by lazy { RepoListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.repositoriesListRV.adapter = adapter

        binding.confirmButton.setOnClickListener {
        it.hideSoftKeyboard()
            this.createProgressDialog()
            setListener()
        }

    }

    private fun setListener() {
        val userName = binding.userNameInput.text.toString()
        Glide.with(this).load("https://avatars.githubusercontent.com/${userName}").into(binding.profilePicture)
        mainViewModel.getRepoList(userName)
        mainViewModel.repos.observe(this) {
            when (it) {
                MainViewModel.State.Loading -> {
                    Snackbar.make(binding.root, "Loading", Snackbar.LENGTH_SHORT).show()
                }
                is MainViewModel.State.Error -> {
                    Snackbar.make(binding.root, "User not found!", Snackbar.LENGTH_SHORT).show()
                }
                is MainViewModel.State.Success -> {
                    adapter.submitList(it.list)
                }
            }
        }
    }
}