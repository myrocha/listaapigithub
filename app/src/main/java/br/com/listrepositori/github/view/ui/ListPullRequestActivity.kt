package br.com.listrepositori.github.view.ui


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import br.com.listrepositori.github.R
import br.com.listrepositori.github.extensions.changeVisibility
import br.com.listrepositori.github.extensions.createRecyclerView
import br.com.listrepositori.github.service.repository.StateError
import br.com.listrepositori.github.service.repository.StateLoading
import br.com.listrepositori.github.service.repository.StateSuccess
import br.com.listrepositori.github.view.adapter.ListPullRequestAdapter
import br.com.listrepositori.github.view.listener.PullRequestClickListener
import br.com.listrepositori.github.viewmodel.ListRepositoryViewModel
import kotlinx.android.synthetic.main.activity_list_repository.*
import kotlinx.android.synthetic.main.activity_pull_request.*
import kotlinx.android.synthetic.main.activity_pull_request.llError
import kotlinx.android.synthetic.main.activity_pull_request.pbLoading
import kotlinx.android.synthetic.main.activity_pull_request.toolbar
import org.jetbrains.anko.browse
import javax.inject.Inject

class ListPullRequestActivity : BaseActivity(), PullRequestClickListener {


    @Inject
    lateinit var viewModel: ListRepositoryViewModel
    lateinit var nameRepository: String
    lateinit var owner: String
    private lateinit var adapter: ListPullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pull_request)
        setupToolbar(toolbar, R.drawable.back, true)
        nameRepository = intent?.getStringExtra(ITEM_NAME).toString()
        owner = intent?.getStringExtra(ITEM_OWNER).toString()
        configureAdapter()
        observerListPullRequest()
        fetchListPullRequest()
    }

    private fun fetchListPullRequest() {
        viewModel.fetchListPullRequest2(owner, nameRepository)
    }

    private fun configureAdapter() {
        adapter = ListPullRequestAdapter(this)
        rcPullRequest.createRecyclerView(this, adapter)

    }

    private fun observerListPullRequest() {

        viewModel.getListPullRequest().observe(this, Observer { state ->
            when (state) {
                is StateLoading -> {
                    pbLoading.changeVisibility(true)
                }
                is StateSuccess -> {
                    pbLoading.changeVisibility(false)
                    rcPullRequest.changeVisibility(true)
                    adapter.updateListItem(state.data)
                }
                is StateError -> {
                    pbLoading.changeVisibility(false)
                    llError.changeVisibility(true)
                }
            }

        })

    }


    override fun openWebGitHub(url: String) {
        browse(url)
    }

    fun clickFetchListRepository(view: View) {
        llError.changeVisibility(false)
        fetchListPullRequest()
    }


    companion object {
        const val ITEM_NAME = "item_name"
        const val ITEM_OWNER = "item_owner"
    }
}