package br.com.listrepositori.github.service.usecase

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.listrepositori.github.service.repository.GitHubApi
import io.reactivex.Scheduler
import io.reactivex.Single

class ListPullRequestUseCase(val repository: GitHubApi, val scheduler: Scheduler) {

    fun execute(owner: String, nameRepository: String): Single<List<PullRequest>> {
        return repository.getListPullRequest(owner, nameRepository).subscribeOn(scheduler)
    }
}