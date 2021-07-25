package com.sachin.workindiaassessment.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sachin.workindiaassessment.models.ItemResponse
import com.sachin.workindiaassessment.repositories.Repository
import com.sachin.workindiaassessment.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){

    private val items: MutableLiveData<Resource<ItemResponse>> = MutableLiveData()

    public fun getAllItems() = viewModelScope
        .launch(Dispatchers.IO) {
            items.postValue(Resource.Loading())
            val itemValue =  handleItemResponse(repository.getAllItems())
            items.postValue(itemValue)
        }

    private fun handleItemResponse(response: Response<ItemResponse>): Resource<ItemResponse> {
        if(response.isSuccessful){
            response.body()?.let { itemResponse ->
                return Resource.Success(itemResponse)
            }
        }
        return Resource.Error(response.message())
    }

    public fun getItemData(): LiveData<Resource<ItemResponse>>{
        return items
    }

}