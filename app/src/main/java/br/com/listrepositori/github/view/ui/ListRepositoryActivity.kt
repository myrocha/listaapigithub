package br.com.listrepositori.github.view.ui


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.listrepositori.github.R
import br.com.listrepositori.github.extensions.changeVisibility
import br.com.listrepositori.github.extensions.createRecyclerView
import br.com.listrepositori.github.service.repository.StateError
import br.com.listrepositori.github.service.repository.StateLoading
import br.com.listrepositori.github.service.repository.StateSuccess
import br.com.listrepositori.github.view.adapter.ListRepositoryAdapter
import br.com.listrepositori.github.view.listener.RepositoryClickListener
import br.com.listrepositori.github.view.ui.ListPullRequestActivity.Companion.ITEM_NAME
import br.com.listrepositori.github.view.ui.ListPullRequestActivity.Companion.ITEM_OWNER
import br.com.listrepositori.github.viewmodel.ListRepositoryViewModel
import kotlinx.android.synthetic.main.activity_list_repository.*
import org.jetbrains.anko.intentFor
import javax.inject.Inject

class ListRepositoryActivity : BaseActivity(), RepositoryClickListener {


    @Inject
    lateinit var viewModel: ListRepositoryViewModel
    private lateinit var adapterRepository: ListRepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_repository)
        setupToolbar(toolbar, R.drawable.back, true)
        adapterRepository = ListRepositoryAdapter(this)
        rcRepositories.createRecyclerView(this, adapterRepository)
        observePullRequest()
        fetchRepository()
    }


    private fun fetchRepository() {
        viewModel.fetchListRepository()
    }

    fun clickFetchListRepository(view: View) {
        llError.changeVisibility(false)
        fetchRepository()
    }


    private fun observePullRequest() {
        viewModel.getData().observe(this, Observer { state ->
            when (state) {
                is StateLoading -> {
                    pbLoading.changeVisibility(true)
                }
                is StateSuccess -> {
                    pbLoading.changeVisibility(false)
                    rcRepositories.changeVisibility(true)
                    adapterRepository.updateListItems(state.data.items)
                }
                is StateError -> {
                    pbLoading.changeVisibility(false)
                    llError.changeVisibility(true)
                }
            }
        })

    }

    override fun clickListener(repository: Repository) {
        startActivity(
            intentFor<ListPullRequestActivity>(
                ITEM_NAME to repository.repoName,
                ITEM_OWNER to repository.owner.login
            )
        )
    }
}