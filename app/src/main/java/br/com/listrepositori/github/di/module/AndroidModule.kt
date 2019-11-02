package br.com.listrepositori.github.di.module

import android.content.Context
import br.com.listrepositori.github.ListRepositoryGitHubApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
internal class AndroidModule {

    @Provides
    @Singleton
    fun provideContext(application: ListRepositoryGitHubApi): Context = application.applicationContext

}
