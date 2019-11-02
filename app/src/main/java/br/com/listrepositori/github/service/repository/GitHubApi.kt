package br.com.listrepositori.github.service.repository

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import io.reactivex.Single

class GitHubApi(val gitHub: GitHubService) {

    fun getListRepository()
            : Single<ServerResponse> {
        return gitHub.getRepositoryList("java", 1, "starts").flatMap {
            Single.just(it)
        }
    }

    fun getListPullRequest(owner: String, repository: String) : Single<List<PullRequest>>{
        return gitHub.getPullRequests(owner, repository).flatMap {
            Single.just(it)
        }

    }

}
