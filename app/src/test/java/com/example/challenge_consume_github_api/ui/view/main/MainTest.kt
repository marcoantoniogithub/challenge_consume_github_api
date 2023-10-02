package com.example.challenge_consume_github_api.ui.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.challenge_consume_github_api.common.Resource
import com.example.challenge_consume_github_api.data.remote.dto.UsersDTO
import com.example.challenge_consume_github_api.domain.usecase.GetAllUsersUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MainTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: MainViewModel

    @Mock
    var useCase: GetAllUsersUseCase = mock()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MainViewModel(useCase)
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun stateLoading() = runTest {
        val flow = flow<Resource<List<UsersDTO>>> {
            emit(Resource.loading())
        }
        given(useCase.invoke()).willReturn(flow)
        viewModel.getRepositories()

        assertEquals(viewModel.state.value, MainViewModel.MainUiState.Loading)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun stateSuccess() = runTest {
        val response = listOf(
            UsersDTO(
                "login",
                1,
                "node_id",
                "avatar_url",
                "gravatar_id",
                "url",
                "html_url",
                "followers_url",
                "following_url",
                "gists_url",
                "starred_url",
                "subscriptions_url",
                "organizations_url",
                "repos_url",
                "events_url",
                "received_events_url",
                "type",
                true
            ),
            UsersDTO(
                "login",
                2,
                "node_id",
                "avatar_url",
                "gravatar_id",
                "url",
                "html_url",
                "followers_url",
                "following_url",
                "gists_url",
                "starred_url",
                "subscriptions_url",
                "organizations_url",
                "repos_url",
                "events_url",
                "received_events_url",
                "type",
                true
            )
        )


        val flow = flow<Resource<List<UsersDTO>>> {
            emit(
                Resource.success(
                    response
                )
            )
        }
        given(useCase.invoke()).willReturn(flow)
        viewModel.getRepositories()

        assertEquals(viewModel.state.value, MainViewModel.MainUiState.Success(response))
    }

    @ExperimentalCoroutinesApi
    @Test(expected = Throwable::class)
    fun stateError() = runTest {
        val error = Resource.error<List<UsersDTO>>(Throwable("test error"))
        val flow = flow<Resource<List<UsersDTO>>> {
            emit(error)
        }
        given(useCase.invoke()).willReturn(flow)

        viewModel.getRepositories()

        assertEquals(viewModel.state.value, MainViewModel.MainUiState.Error("test error"))
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

}