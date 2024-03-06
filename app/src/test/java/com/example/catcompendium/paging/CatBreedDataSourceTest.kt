package com.example.catcompendium.paging

import android.annotation.SuppressLint
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catcompendium.api.BaseDataSource
import com.example.catcompendium.api.FakeCatService
import com.example.catcompendium.api.config.LIMIT
import com.example.catcompendium.model.CatBreedItem
import com.example.catcompendium.model.listBreed
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CatBreedDataSourceTest : BaseDataSource {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()
    private val fakeCatAPI = FakeCatService()
    private val pagingSource = CatBreedDataSource(fakeCatAPI)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

    }

    @Test
    fun testLoadMethod() = runTest {
        val limit = LIMIT
        val pagingSource = CatBreedDataSource(fakeCatAPI)
        val actual =  pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = limit,
                loadSize = limit,
                placeholdersEnabled = false
            )
        )
        advanceUntilIdle()
        Truth.assertThat(actual).isEqualTo(
            PagingSource.LoadResult.Page(
                data = listBreed.subList(0, LIMIT),
                prevKey = limit - 1,
                nextKey = limit + 1
            )
        )
    }

    @Test
    fun testErrorScenario() = runTest {
        val limit = -1
        val actual =  pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = limit,
                loadSize = limit,
                placeholdersEnabled = false
            )
        )
        advanceUntilIdle()
        assert(actual is PagingSource.LoadResult.Error)
    }

    @Test
    fun testGetRefreshKey_noAnchorPosition() {
        val state = mockk<PagingState<Int, CatBreedItem>>()
        coEvery { state.anchorPosition } returns null
        val refreshKey = pagingSource.getRefreshKey(state)
        Assert.assertNull(refreshKey)
    }

    @SuppressLint("CheckResult")
    @Test
    fun testGetRefreshKey_withAnchorPosition() {
        val state = mockk<PagingState<Int, CatBreedItem>>()
        coEvery { state.anchorPosition } returns 2
        coEvery { state.closestPageToPosition(2)?.nextKey  } returns 3
        coEvery { state.closestPageToPosition(2)?.prevKey  } returns 1
        val refreshKey = pagingSource.getRefreshKey(state)
        assertThat(refreshKey == 3)

    }

    @After
    fun resetMain() {
        Dispatchers.resetMain()
    }
}