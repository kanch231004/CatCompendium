package com.example.catcompendium.repository

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catcompendium.api.FakeCatService
import com.example.catcompendium.api.config.LIMIT
import com.example.catcompendium.api.config.PREFETCH_DISTANCE
import com.example.catcompendium.model.CatBreedItem
import com.example.catcompendium.paging.FeedDataPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class CatBreedRepositoryImplTest {

    private val fakeCatAPI = FakeCatService()
    private val testDispatcher = StandardTestDispatcher()
    private val pagingConfig = PagingConfig(
        pageSize = LIMIT,
        prefetchDistance = PREFETCH_DISTANCE,
        enablePlaceholders = false
    )
    private val catBreedRepositoryImpl = CatBreedRepositoryImpl(
        catApiService = fakeCatAPI,
        pagingConfig = pagingConfig
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test getCatBreedList returns expected flow`() = runTest {
        var resultList: Flow<PagingData<CatBreedItem>>? = null

        val job = launch {
            resultList = catBreedRepositoryImpl.getCatBreedList()
        }
        advanceUntilIdle()
        assertNotNull(resultList)
        job.cancel()
    }
}



