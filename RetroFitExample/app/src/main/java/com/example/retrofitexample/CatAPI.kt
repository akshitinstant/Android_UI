package com.example.retrofitexample

import retrofit2.http.GET
import retrofit2.http.Query

interface CatAPI {

    @GET("images/search")
    suspend fun getCats(@Query("limit") limit: Int=5, @Query("mime_types") mime:String): List<Cat>

}