package br.com.listrepositori.github.di.module

import br.com.listrepositori.github.service.repository.GitHubApi
import br.com.listrepositori.github.service.repository.GitHubService
import br.com.listrepositori.github.service.usecase.ListPullRequestUseCase
import br.com.listrepositori.github.service.usecase.ListRepositoryUseCase
import br.com.listrepositori.github.viewmodel.ListRepositoryViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class ListRepositoryViewModelModule {


    @Provides
    fun providesListRepositoryGitHubViewModel(
        listRepositoryUseCase: ListRepositoryUseCase, listPullRequestUseCase: ListPullRequestUseCase
    ) = ListRepositoryViewModel(
        listRepositoryUseCase,
        listPullRequestUseCase,
        AndroidSchedulers.mainThread()
    )

    @Provides
    fun providesListRepositoryUseCase(repository: GitHubApi) =
        ListRepositoryUseCase(repository, Schedulers.io())

    @Provides
    fun provideListPullRequestUseCase(repository: GitHubApi) =
        ListPullRequestUseCase(repository, Schedulers.io())

    @Provides
    fun providesListRepository(service: GitHubService): GitHubApi =
        GitHubApi(service)

}