package com.ebenezer.gana.mamatasty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ebenezer.gana.mamatasty.data.network.Food

@Dao
interface FoodListDao {

    @Query("SELECT * FROM food ORDER BY title")
    fun getFoodList():LiveData<List<Food>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(food: List<Food>)

    @Query("DELETE FROM food")
    suspend fun deleteAllData()


}