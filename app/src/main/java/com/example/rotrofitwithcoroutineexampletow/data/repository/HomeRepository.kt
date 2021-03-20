package com.example.rotrofitwithcoroutineexampletow.data.repository

import com.example.rotrofitwithcoroutineexampletow.data.api.ApiHelper

class HomeRepository (private val apiHelper: ApiHelper) {

    suspend fun getPhotos(page: Int) = apiHelper.getPhotos(page)
}