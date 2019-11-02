package br.com.listrepositori.github.di.component

import br.com.listrepositori.github.ListRepositoryGitHubApi
import br.com.listrepositori.github.di.module.AndroidModule
import br.com.listrepositori.github.di.module.BuildersModule
import br.com.listrepositori.github.di.module.ListRepositoryViewModelModule
import br.com.listrepositori.github.di.module.RepositoryModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    RepositoryModule::class,
    ListRepositoryViewModelModule::class,
    BuildersModule::class

])
internal interface AppComponent : AndroidInjector<ListRepositoryGitHubApi> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<ListRepositoryGitHubApi>
}