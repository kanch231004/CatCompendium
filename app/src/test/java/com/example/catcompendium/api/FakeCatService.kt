package com.example.catcompendium.api

import com.example.catcompendium.model.CatBreedItem
import com.example.catcompendium.model.errorMessage
import com.example.catcompendium.model.listBreed
import kotlinx.coroutines.delay
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert.*
import retrofit2.Response
import retrofit2.http.Query

class FakeCatService : CatService {
    override suspend fun getCatBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ArrayList<CatBreedItem>> {
        return try {
            Response.success(
                findBreeds(limit, page, listBreed)
            )
        } catch (e: Exception) {
            Response.error<ArrayList<CatBreedItem>>(401, ResponseBody.create(
                "application/json".toMediaTypeOrNull(),
                errorMessage))
        }
    }

    private suspend fun findBreeds(
        limit: Int?,
        page: Int?,
        items: ArrayList<CatBreedItem>
    ): ArrayList<CatBreedItem> {
        delay(1000)
        if (page == -1) {
            Throwable("Invalid Request")
        }
        if (limit == null && page == null) {
            return items
        }
        if (limit == null) {
            return arrayListOf()
        }
        return ArrayList(items.subList(0, limit))
    }
}