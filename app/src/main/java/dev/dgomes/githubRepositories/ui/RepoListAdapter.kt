package dev.dgomes.githubRepositories.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.dgomes.githubRepositories.data.model.RepositoryData
import dev.dgomes.githubRepositories.databinding.RepositoryItemBinding

class RepoListAdapter(private val context: Context) : ListAdapter<RepositoryData, RepoListAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RepositoryItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: RepositoryData) {
            binding.repositoryNameText.text = item.name
            binding.repositoryLanguageText.text = item.language
            binding.repositoryDescriptionText.text = item.description

            binding.repositoryCard.setOnClickListener{
                    context.startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(item.htmlUrl)
                        )
                    )
            }

            binding.shareImage.setOnClickListener{

                val shareIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, item.htmlUrl)
                    putExtra(Intent.EXTRA_TITLE, item.name)
                    type = "text/plain"
                }
                context.startActivity(
                    Intent.createChooser(
                        shareIntent,
                        "Share"
                    )
                )
            }
        }


    }
}

class DiffCallBack : DiffUtil.ItemCallback<RepositoryData>() {
    override fun areItemsTheSame(oldItem: RepositoryData, newItem: RepositoryData) = oldItem == newItem
    override fun areContentsTheSame(oldItem: RepositoryData, newItem: RepositoryData) = oldItem.id == newItem.id
}