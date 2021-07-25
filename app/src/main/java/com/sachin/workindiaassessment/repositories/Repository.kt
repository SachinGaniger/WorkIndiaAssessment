package com.sachin.workindiaassessment.repositories

import com.sachin.workindiaassessment.network.ApiService
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
){

    suspend fun getAllItems() = apiService.getItems()

}