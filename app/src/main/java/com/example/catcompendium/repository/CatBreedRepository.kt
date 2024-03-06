package com.example.catcompendium.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.catcompendium.api.CatService
import com.example.catcompendium.model.CatBreedItem
import com.example.catcompendium.paging.CatBreedDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CatBreedRepositoryImpl @Inject constructor(
    private val catApiService: CatService,
    private val pagingConfig: PagingConfig
): CatBreedRepository {

    override fun getCatBreedList(): Flow<PagingData<CatBreedItem>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { CatBreedDataSource(catApiService) },
        ).flow
    }
}

interface CatBreedRepository {
     fun getCatBreedList(): Flow<PagingData<CatBreedItem>>
}