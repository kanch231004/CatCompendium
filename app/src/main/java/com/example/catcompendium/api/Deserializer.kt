package com.example.catcompendium.api

import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import okhttp3.ResponseBody

inline fun <reified T> ResponseBody.deserialize() : T {
    val builder = GsonBuilder().setPrettyPrinting().serializeNulls()
    val gson = builder.create()
    return gson.fromJson(this.string(),T::class.java)

}

data class ErrorBody(@SerializedName("message") val message: String? = "Something went wrong")