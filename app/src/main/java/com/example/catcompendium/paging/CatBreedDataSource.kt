package com.example.catcompendium.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.catcompendium.api.APIResult
import com.example.catcompendium.api.BaseDataSource
import com.example.catcompendium.api.CatService
import com.example.catcompendium.api.config.INCREMENT_COUNT
import com.example.catcompendium.api.config.INITIAL_PAGE
import com.example.catcompendium.model.CatBreedItem
import javax.inject.Inject

class CatBreedDataSource @Inject constructor(
    private val catService: CatService,
) : FeedDataPagingSource(), BaseDataSource {

    override fun getRefreshKey(state: PagingState<Int, CatBreedItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
                 state.closestPageToPosition(anchorPosition)?.nextKey?.plus(INCREMENT_COUNT) ?:
                    state.closestPageToPosition(anchorPosition)?.prevKey?.minus(INCREMENT_COUNT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatBreedItem> {
        val page = params.key ?: INITIAL_PAGE
        val limit = params.loadSize

        return when (val pagedResponse = getResult { catService.getCatBreeds(page, limit) }) {
            is APIResult.Success -> LoadResult.Page(
                data = pagedResponse.data.orEmpty(),
                prevKey = if (page == INITIAL_PAGE) null else page - INCREMENT_COUNT,
                nextKey = if (pagedResponse.data.isNullOrEmpty()) null else page + INCREMENT_COUNT
            )

            is APIResult.Error -> LoadResult.Error(
                Throwable(pagedResponse.message.toString())
            )
        }
    }
}

abstract class FeedDataPagingSource : PagingSource<Int, CatBreedItem>()