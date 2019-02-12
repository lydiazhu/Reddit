package com.example.reddit.grid

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.reddit.RedditApi
import com.example.reddit.RxScheduleRule
import com.example.reddit.testObserver
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class RedditGridViewModelTest {
    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val rxSchedulerRule = RxScheduleRule()

    @Mock
    lateinit var redditApi: RedditApi

    @Mock
    lateinit var view: RedditGridView

    private lateinit var classUnderTest: RedditGridViewModel

    @Test
    fun `when response is successful should setup grid layout and hide progress bar`() {
        val data = RedditPostsResponse(
            RedditDataResponse(
                listOf(
                    RedditChildrenResponse(RedditPostsDataResponse("name", "title", "thumbnail",
                        "permalink"))
                )
            )
        )
        `when`(redditApi.getAllPosts()).thenReturn(Observable.just(data))
        classUnderTest = RedditGridViewModel(redditApi, view)
        val response = classUnderTest.getLiveRedditItems().testObserver()

        if (response.observedValues.size > 0) {
            val f = response.observedValues[0]
            if(f != null) {
                verify(view).setupGridLayout(f)
            }
        }
        verify(view).hideProgressBar()
    }
}