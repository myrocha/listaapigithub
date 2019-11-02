package br.com.listrepositori.github.service.repository

import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("search/repositories")
    fun getRepositoryList(@Query("q") technology: String, @Query("page") page: Int, @Query("sort") sort: String): Single<ServerResponse>

    @GET("repos/{owner}/{repository}/pulls")
    fun getPullRequests(@Path("owner") owner: String, @Path("repository") repository: String): Single<List<PullRequest>>

}