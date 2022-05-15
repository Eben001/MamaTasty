package com.ebenezer.gana.mamatasty.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ebenezer.gana.mamatasty.data.local.FoodRoomDatabase
import com.ebenezer.gana.mamatasty.data.local.dao.FoodDetailDao
import com.ebenezer.gana.mamatasty.data.network.Food
import kotlinx.coroutines.flow.Flow


class FoodDetailRepository(context: Application) {
    private val foodDetailDao: FoodDetailDao =
        FoodRoomDatabase.getDatabase(context).foodDetailsDao()

    fun getFood(id: String): Flow<Food> = foodDetailDao.getFood(id)

}