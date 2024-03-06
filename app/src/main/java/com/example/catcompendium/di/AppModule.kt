package com.example.catcompendium.di

import androidx.paging.PagingConfig
import com.example.catcompendium.api.CatService
import com.example.catcompendium.api.RetrofitFactory
import com.example.catcompendium.api.config.LIMIT
import com.example.catcompendium.api.config.PREFETCH_DISTANCE
import com.example.catcompendium.repository.CatBreedRepository
import com.example.catcompendium.repository.CatBreedRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {
    @Provides
    @Singleton
    fun providesAPIService(): CatService = RetrofitFactory.getService()

    @Provides
    fun providesPagingConfig(): PagingConfig = PagingConfig(
        pageSize = LIMIT,
        prefetchDistance = PREFETCH_DISTANCE,
        enablePlaceholders = true
    )

    @Provides
    @Singleton
    fun providesCatRepository(pagingConfig: PagingConfig, catService: CatService
    ): CatBreedRepository = CatBreedRepositoryImpl( catService, pagingConfig)
}