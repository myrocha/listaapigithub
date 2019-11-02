package br.com.listrepositori.github.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.listrepositori.github.R
import br.com.listrepositori.github.extensions.loadUrl
import br.com.listrepositori.github.view.listener.PullRequestClickListener
import kotlinx.android.synthetic.main.item_pull_request.view.*

class ListPullRequestAdapter(val listener: PullRequestClickListener) : RecyclerView.Adapter<ListPullRequestAdapter.ListPullRequestViewHolder>() {

    private var pullRequestList = mutableListOf<PullRequest>()

    fun updateListItem(items: List<PullRequest>){
        this.pullRequestList.run {
            clear()
            addAll(items)
            notifyDataSetChanged()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPullRequestViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pull_request, parent, false)
        return ListPullRequestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return pullRequestList.size
    }

    override fun onBindViewHolder(holder: ListPullRequestViewHolder, position: Int) {
        holder.bindView(pullRequestList[position])
    }


    inner class ListPullRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(item: PullRequest){

            item.let {
                itemView.title.text = item.title
                itemView.body.text = item.body
                itemView.username.text = item.user.login
                itemView.account.loadUrl(item.user.avatarUrl)
                itemView.setOnClickListener { listener.openWebGitHub(item.url) }

            }

        }

    }
}