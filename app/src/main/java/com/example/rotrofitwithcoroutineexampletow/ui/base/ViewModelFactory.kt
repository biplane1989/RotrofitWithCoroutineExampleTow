package com.example.rotrofitwithcoroutineexampletow.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.actionlistenerexample.home.HomeViewmodel
import com.example.rotrofitwithcoroutineexampletow.data.api.ApiHelper
import com.example.rotrofitwithcoroutineexampletow.data.repository.HomeRepository


class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewmodel::class.java)) {
            return HomeViewmodel(HomeRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

