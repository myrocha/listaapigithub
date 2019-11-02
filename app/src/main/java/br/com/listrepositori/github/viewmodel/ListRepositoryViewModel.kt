package br.com.listrepositori.github.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import br.com.listrepositori.github.service.repository.StateError
import br.com.listrepositori.github.service.repository.StateLoading
import br.com.listrepositori.github.service.repository.StateResponse
import br.com.listrepositori.github.service.repository.StateSuccess
import br.com.listrepositori.github.service.usecase.ListPullRequestUseCase
import br.com.listrepositori.github.service.usecase.ListRepositoryUseCase
import io.reactivex.Scheduler
import javax.inject.Singleton

@Singleton
class ListRepositoryViewModel(
    var useCase: ListRepositoryUseCase,
    var useCasePullRequest: ListPullRequestUseCase,
    var schedule: Scheduler
) : BaseViewModel() {

    private val listRepositoryLiveData = MutableLiveData<StateResponse<ServerResponse>>()
    private val mediator = MediatorLiveData<StateResponse<ServerResponse>>()

    private val listPullRequestLiveData = MutableLiveData<StateResponse<List<PullRequest>>>()
    private val mediatorListPullRequest = MediatorLiveData<StateResponse<List<PullRequest>>>()

    fun getData() = mediator

    //fun getResponseLiveData() = response

    fun getListPullRequest() = listPullRequestLiveData

    init {
        mediator.addSource(listRepositoryLiveData) { data ->
            mediator.value = data?.let { it }

        }
    }



    fun fetchListRepository() {
        listRepositoryLiveData.value = StateLoading()
        useCase.execute().observeOn(schedule).subscribe(
            { list ->
                if (list.items.isNotEmpty()) {
                    listRepositoryLiveData.value = StateSuccess(list)
                } else {
                    listRepositoryLiveData.value = StateError(Throwable(EMPTY_REQUEST))
                }
            },
            { e ->
                listRepositoryLiveData.value = StateError(Throwable(e))
            })
            .let {
                disposables.add(it)
            }
    }

    fun fetchListPullRequest2(owner: String, nameRepository: String) {

        listPullRequestLiveData.value = StateLoading()
        useCasePullRequest.execute(owner, nameRepository).observeOn(schedule).subscribe(
            { list ->
                if (list.isNotEmpty()) {
                    listPullRequestLiveData.value = StateSuccess(list)

                } else {
                    listPullRequestLiveData.value = StateError(Throwable(EMPTY_REQUEST))
                }
            },

            { e ->
                listPullRequestLiveData.value = StateError(Throwable(e))
            })
            .let {
                disposables.add(it)
            }

    }

    fun fetchListPullRequest(owner: String, nameRepository: String) {
        disposables.add(
            useCasePullRequest.execute(owner, nameRepository)
                .doOnSubscribe { mediatorListPullRequest.value = StateLoading() }
                .subscribe(
                    { list->
                        if (list.isNotEmpty()) {
                            listPullRequestLiveData.value = StateSuccess(list)

                        } else {
                            listPullRequestLiveData.value = StateError(Throwable(EMPTY_REQUEST))
                        }
                    },
                    { listPullRequestLiveData.value = StateError(Throwable(it)) }
                )
        )
    }



    companion object {
        const val EMPTY_REQUEST = "empty request"
    }

}