package com.example.actionlistenerexample.home

import android.app.Application
import androidx.lifecycle.*
import com.example.rotrofitwithcoroutineexampletow.data.model.ImageItem
import com.example.rotrofitwithcoroutineexampletow.data.repository.HomeRepository
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.*
import java.lang.Exception

class HomeViewmodel(private val mainRepository: HomeRepository) : ViewModel() {

    private var _networkData = MutableLiveData<List<ImageItem>>()
    val networkData: LiveData<List<ImageItem>> get() = _networkData

    private val _loading = MutableLiveData<Boolean>(true)
    val loading: LiveData<Boolean> get() = _loading

    private val _exceptionNetwork = MutableLiveData<Boolean>()
    val exceptionNetWork: LiveData<Boolean> get() = _exceptionNetwork

    init {
        getData(1)
    }

    fun getData(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                _loading.value = true
            }
            try {
                delay(3000)
                val response = mainRepository.getPhotos(page)

                if (isActive) {
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                _networkData.value = it.map { ImageItem(it.id, it.urls.thumb!!, it.urls.raw!!) }
                                _loading.value = false
                            }
                        } else {
                            _exceptionNetwork.value = true
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _exceptionNetwork.value = true
                }
            }
        }
    }
}
