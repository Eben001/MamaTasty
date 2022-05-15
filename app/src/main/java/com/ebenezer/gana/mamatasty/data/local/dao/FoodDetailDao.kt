package com.ebenezer.gana.mamatasty.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.ebenezer.gana.mamatasty.data.network.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDetailDao {

    @Query("SELECT * FROM food WHERE id =:id")
    fun getFood(id: String): Flow<Food>

}