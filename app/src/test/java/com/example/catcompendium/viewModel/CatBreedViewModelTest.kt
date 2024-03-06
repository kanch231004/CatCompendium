package com.example.catcompendium.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.recyclerview.widget.ListUpdateCallback
import com.example.catcompendium.model.listBreed
import com.example.catcompendium.repository.CatBreedRepository
import com.example.catcompendium.ui.CatListDiffCallback
import com.google.common.truth.Truth.assertThat
import com.example.catcompendium.viewmodel.CatBreedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class CatBreedViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: CatBreedViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val mockRepository = Mockito.mock(CatBreedRepository::class.java)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testItem() = runTest {
        val differ = AsyncPagingDataDiffer(
            diffCallback = CatListDiffCallback,
            mainDispatcher = testDispatcher,
            updateCallback = noopListUpdateCallback,
            workerDispatcher = testDispatcher
        )
        val repo = FakeCatRepository()
        viewModel = CatBreedViewModel(repo)
        Mockito.`when`((mockRepository).getCatBreedList()).thenReturn(flowOf(PagingData.from(listBreed.subList(0, 11))))
        val job = launch {
            viewModel.breedList.collectLatest { pagingData ->
                differ.submitData(pagingData)
            }
        }
        advanceUntilIdle()
        assertThat(differ.snapshot()).isEqualTo(listBreed)
        job.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun reset() {
        Dispatchers.resetMain()
    }
}


val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class FakeCatRepository : CatBreedRepository {
    private val pagingData = PagingData.from(listBreed.subList(0, 11))
    override fun getCatBreedList() = flowOf(pagingData)
}
