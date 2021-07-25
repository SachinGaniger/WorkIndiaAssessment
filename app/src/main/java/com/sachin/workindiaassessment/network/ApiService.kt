package com.sachin.workindiaassessment.network

import com.sachin.workindiaassessment.models.ItemResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("b6a30bb0-140f-4966-8608-1dc35fa1fadc")
    suspend fun getItems(): Response<ItemResponse>

}