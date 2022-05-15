package com.ebenezer.gana.mamatasty.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ebenezer.gana.mamatasty.data.local.FoodRoomDatabase
import com.ebenezer.gana.mamatasty.data.local.dao.FoodListDao
import com.ebenezer.gana.mamatasty.data.network.ErrorCode
import com.ebenezer.gana.mamatasty.data.network.Food
import com.ebenezer.gana.mamatasty.data.network.FoodApiService
import com.ebenezer.gana.mamatasty.data.network.LoadingStatus
import java.net.UnknownHostException

class FoodListRepository(context: Application) {

    private val foodListDao: FoodListDao =
        FoodRoomDatabase.getDatabase(context).foodListDao()

    private val foodApiService by lazy { FoodApiService.getInstance() }


    fun getFoodList(): LiveData<List<Food>> = foodListDao.getFoodList()


    suspend fun fetchFromNetwork() =
        try {
            val result = foodApiService.getFoodCategories()
            if (result.isSuccessful) {
                val foodList = result.body()
                foodList?.let {
                    foodListDao.insertFoodList(it.categories)
                    LoadingStatus.success()
                }
            } else {
                LoadingStatus.error(errorCode = ErrorCode.NO_DATA)
            }
        } catch (ex: UnknownHostException) {
            LoadingStatus.error(errorCode = ErrorCode.NETWORK_ERROR)

        } catch (ex: Exception) {
            LoadingStatus.error(errorCode = ErrorCode.UNKNOWN_ERROR, ex.message)
        }


    suspend fun deleteAllData() {
        foodListDao.deleteAllData()
    }

}