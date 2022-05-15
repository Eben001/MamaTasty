package com.ebenezer.gana.mamatasty.ui.foodList

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ebenezer.gana.mamatasty.data.network.Food
import com.ebenezer.gana.mamatasty.data.network.LoadingStatus
import com.ebenezer.gana.mamatasty.data.repository.FoodListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FoodListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FoodListRepository = FoodListRepository(application)

    val foodList: Flow<List<Food>> = repository.getFoodList()

    private val _loadingStatus = MutableLiveData<LoadingStatus?>()
    val loadingStatus: MutableLiveData<LoadingStatus?> = _loadingStatus

    fun fetchFromNetwork() {
        _loadingStatus.value = LoadingStatus.loading()
        viewModelScope.launch {
            _loadingStatus.value = withContext(Dispatchers.IO) {
                repository.fetchFromNetwork()
            }
        }
    }

    fun refreshData(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllData()
        }
        fetchFromNetwork()
    }
}