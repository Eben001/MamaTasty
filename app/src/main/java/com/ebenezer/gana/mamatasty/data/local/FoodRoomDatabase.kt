package com.ebenezer.gana.mamatasty.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ebenezer.gana.mamatasty.data.local.dao.FoodDetailDao
import com.ebenezer.gana.mamatasty.data.local.dao.FoodListDao
import com.ebenezer.gana.mamatasty.data.network.Food

@Database(
    entities = [Food::class],
    version = 1, exportSchema = false
)
abstract class FoodRoomDatabase : RoomDatabase() {
    abstract fun foodListDao(): FoodListDao
    abstract fun foodDetailsDao(): FoodDetailDao

    companion object {
        private const val DATABASE_NAME = "food_database"

        /* the value of a volatile variable will never be cached, all writes and reads will be
                 from the the main memory*/
        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null
        fun getDatabase(context: Context): FoodRoomDatabase {


            /* wrapping to get the database inside the synchronized block means
             only one thread of execution at a time can enter this block of code*/
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                INSTANCE = instance

                return instance
            }
        }


    }


}