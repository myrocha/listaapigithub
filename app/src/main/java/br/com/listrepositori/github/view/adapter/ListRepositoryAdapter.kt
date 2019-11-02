package br.com.listrepositori.github.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.listrepositori.github.R
import br.com.listrepositori.github.extensions.loadUrl
import br.com.listrepositori.github.view.listener.RepositoryClickListener
import kotlinx.android.synthetic.main.item_repository.view.*

class ListRepositoryAdapter(val listener: RepositoryClickListener) : RecyclerView.Adapter<ListRepositoryAdapter.ViewHolder>() {
    private var repositoryList = mutableListOf<Repository>()

    open fun updateListItems(items: List<Repository>) {
        this.repositoryList.run {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(repositoryList[position])
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(item: Repository) {
            item.let {
                itemView.repositoryName.text = item.fullName
              //  itemView.fullName.text = item.repoName
                itemView.repositoryDescription.text = item.description
                itemView.user.text = item.owner.login
                itemView.forks.text = item.forksCount.toString()
                itemView.starsCount.text = item.starsCount.toString()
                itemView.acount.loadUrl(item.owner.avatarUrl)
                itemView.setOnClickListener { listener.clickListener(item)}

            }
        }
    }

}