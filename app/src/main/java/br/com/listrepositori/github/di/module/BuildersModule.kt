package br.com.listrepositori.github.di.module

import br.com.listrepositori.github.view.ui.ListRepositoryActivity
import br.com.listrepositori.github.view.ui.ListPullRequestActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [ListRepositoryViewModelModule::class])
    abstract fun bindListRepositoryActivity(): ListRepositoryActivity

    @ContributesAndroidInjector(modules = [ListRepositoryViewModelModule::class])
    abstract fun bindListPullRequestActitivy(): ListPullRequestActivity

}