package com.ebenezer.gana.mamatasty.ui.foodDetail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.ebenezer.gana.mamatasty.data.network.Food
import com.ebenezer.gana.mamatasty.data.repository.FoodDetailRepository
import kotlinx.coroutines.flow.Flow

class FoodDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FoodDetailRepository =
        FoodDetailRepository(application)


    fun getFood(id: String): Flow<Food> = repository.getFood(id)
}