package br.com.listrepositori.github.service.usecase

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import br.com.listrepositori.github.service.repository.GitHubApi
import io.reactivex.Scheduler
import io.reactivex.Single

class ListRepositoryUseCase (val repository: GitHubApi,  val scheduler: Scheduler){

    fun execute(): Single<ServerResponse>{
        return repository.getListRepository().subscribeOn(scheduler)
    }
}