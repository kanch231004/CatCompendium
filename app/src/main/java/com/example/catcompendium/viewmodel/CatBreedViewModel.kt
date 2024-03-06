package com.example.catcompendium.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.catcompendium.model.CatBreedItem
import com.example.catcompendium.repository.CatBreedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CatBreedViewModel @Inject constructor(
    private val catBreedRepository: CatBreedRepository
) : ViewModel() {

    val breedList: Flow<PagingData<CatBreedItem>> =
        catBreedRepository.getCatBreedList().cachedIn(viewModelScope)
}

