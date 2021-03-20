package com.example.rotrofitwithcoroutineexampletow.data.api

class ApiHelper (private val apiService: ApiService) {

    suspend fun getPhotos(page: Int) = apiService.getPhotos(page)
}