package com.example.dogapi2recycler.model


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface DogAPI {

@GET("/v1/breeds/")
suspend fun getDogs(@Query("limit") limit: Int): Response<ArrayList<Dog>>

}