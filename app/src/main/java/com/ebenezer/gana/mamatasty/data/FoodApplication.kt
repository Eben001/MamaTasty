package com.ebenezer.gana.mamatasty.data

import android.app.Application
import com.ebenezer.gana.mamatasty.data.local.FoodRoomDatabase

class FoodApplication : Application() {

    val database: FoodRoomDatabase by lazy {
        FoodRoomDatabase.getDatabase(this)
    }


}