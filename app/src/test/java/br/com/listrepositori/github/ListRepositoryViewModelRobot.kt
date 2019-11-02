package br.com.listrepositori.github

import androidx.lifecycle.Observer
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import br.com.listrepositori.github.ListRepositoryViewModelTest.Companion.listPullRequest
import br.com.listrepositori.github.ListRepositoryViewModelTest.Companion.serverResponse
import br.com.listrepositori.github.extensions.assertInstanceOf
import br.com.listrepositori.github.service.repository.StateError
import br.com.listrepositori.github.service.repository.StateResponse
import br.com.listrepositori.github.service.repository.StateSuccess
import br.com.listrepositori.github.service.usecase.ListPullRequestUseCase
import br.com.listrepositori.github.service.usecase.ListRepositoryUseCase
import br.com.listrepositori.github.viewmodel.ListRepositoryViewModel
import io.mockk.every
import io.reactivex.Single

class ListRepositoryViewModelRobotArrange {
    fun mockUseCaseListRepositoryResponseSuccess(useCase: ListRepositoryUseCase) {
        every { useCase.execute() } returns Single.just(serverResponse)
    }

    fun mockUseCaseListRepositoryResponseError(useCase: ListRepositoryUseCase) {
        every { useCase.execute() } returns Single.error(Throwable("Error"))
    }

    fun mockUseCaseListPullRequestSuccess(useCase: ListPullRequestUseCase) {
        //every { useCase.execute(any(), any()) } returns Single.just(listPullRequest)
        every { useCase.execute(any(), any()) } returns Single.just(listPullRequest)
    }

    fun mockUseCaseListPullResquestError(useCase: ListPullRequestUseCase) {
        every { useCase.execute(any(), any()) } returns Single.error(Throwable("Error"))
    }
}

class ListRepositoryViewModelRobotAct {
    fun fetchListRepository(subject: ListRepositoryViewModel, observer: Observer<StateResponse<ServerResponse>>) {
        subject.getData().observeForever(observer)
        subject.fetchListRepository()
        subject.getData().removeObserver(observer)
    }

    fun fetchListPullRequest(subject: ListRepositoryViewModel, observer:Observer<StateResponse<List<PullRequest>>>) {
    /*    subject.getListPullRequest().observeForever(observer)
        subject.fetchListPullRequest2("", "")
        subject.getListPullRequest().removeObserver(observer)*/

        subject.getListPullRequest().observeForever(observer)
        subject.fetchListPullRequest2("", "")
        subject.getListPullRequest().removeObserver(observer)
    }
}

class ListRepositoryViewModelRobotAssert {
    fun isSuccessStateListRepository(subject: ListRepositoryViewModel) {
        subject.getData().value.assertInstanceOf(StateSuccess::class.java)
    }

    fun isErrorStateListRepository(subject: ListRepositoryViewModel) {
        subject.getData().value.assertInstanceOf(StateError::class.java)
    }

    fun isSuccessStateListPullRequest(subject: ListRepositoryViewModel) {
        subject.getListPullRequest().value.assertInstanceOf(StateSuccess::class.java)
    }

    fun isErrorStateListPullRequest(subject: ListRepositoryViewModel) {
        subject.getListPullRequest().value.assertInstanceOf(StateError::class.java)
    }
}


