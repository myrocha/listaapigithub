package br.com.listrepositori.github

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.PullRequest
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.Repository
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.ServerResponse
import br.com.henriqueoliveira.desafioandroidconcrete.service.models.User
import br.com.listrepositori.github.service.repository.StateResponse
import br.com.listrepositori.github.service.usecase.ListPullRequestUseCase
import br.com.listrepositori.github.service.usecase.ListRepositoryUseCase
import br.com.listrepositori.github.viewmodel.ListRepositoryViewModel
import io.mockk.mockk
import io.reactivex.schedulers.Schedulers
import org.junit.Rule
import org.junit.Test

class ListRepositoryViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()
    private val listRepositoryUseCase = mockk<ListRepositoryUseCase>()
    private val listPullRequestUseCase = mockk<ListPullRequestUseCase>()
    private val observer = mockk<Observer<StateResponse<ServerResponse>>>(relaxed = true)
    private val observerPullRequest =
        mockk<Observer<StateResponse<List<PullRequest>>>>(relaxed = true)
    private val subject =
        ListRepositoryViewModel(listRepositoryUseCase, listPullRequestUseCase, Schedulers.io())

    private fun arrange(func: ListRepositoryViewModelRobotArrange.() -> Unit) =
        ListRepositoryViewModelRobotArrange().apply(func)

    private fun act(func: ListRepositoryViewModelRobotAct.() -> Unit) =
        ListRepositoryViewModelRobotAct().apply(func)

    private fun assert(func: ListRepositoryViewModelRobotAssert.() -> Unit) =
        ListRepositoryViewModelRobotAssert().apply(func)


    @Test
    fun `when fetch repository should expose success status`() {
        arrange {
            mockUseCaseListRepositoryResponseSuccess(listRepositoryUseCase)
        }
        act {
            fetchListRepository(subject, observer)
        }
        assert {
            isSuccessStateListRepository(subject)
        }
    }

    @Test
    fun `when fetch repository with error should expose error status`() {
        arrange {
            mockUseCaseListRepositoryResponseError(listRepositoryUseCase)
        }
        act {
            fetchListRepository(subject, observer)
        }
        assert {
            isErrorStateListRepository(subject)
        }
    }

    @Test
    fun `when fetch pull request should expose success status`() {
        arrange {
            mockUseCaseListPullRequestSuccess(listPullRequestUseCase)
        }
        act {
            fetchListPullRequest(subject, observerPullRequest)
        }
        assert {
            isSuccessStateListPullRequest(subject)
        }
    }

    @Test
    fun `when fetch pull request with error should expose error status`() {
        arrange {
            mockUseCaseListPullResquestError(listPullRequestUseCase)
        }
        act {
            fetchListPullRequest(subject, observerPullRequest)
        }
        assert {
            isErrorStateListPullRequest(subject)
        }
    }

    companion object {
        val serverResponse =
            ServerResponse(
                totalCount = 10, items = listOf<Repository>(
                    Repository(
                        repoName = "Java",
                        fullName = "Java para iniciantes",
                        description = "java para iniciantes",
                        owner = User(login = "java", avatarUrl = "http://javaparainiciantes"),
                        forksCount = 2,
                        starsCount = 5
                    )
                )
            )

        val listPullRequest = listOf(
            PullRequest(
                url = "http://javaparainiciante",
                title = "Adiciona o botão de voltar na tela",
                user = User(login = "java", avatarUrl = "http://javaparainiciantes"),
                createdAt = "10/02/2019",
                body = "body do pull request",
                state = "aberto"
            )
        )
    }
}