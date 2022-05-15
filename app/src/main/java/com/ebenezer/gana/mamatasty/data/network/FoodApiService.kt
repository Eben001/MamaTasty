package com.ebenezer.gana.mamatasty.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

interface FoodApiService {

    companion object {
        private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"



        private val retrofitService by lazy {

            val moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(FoodApiService::class.java)


        }

        fun getInstance(): FoodApiService {
            return retrofitService
        }

    }

    @GET("categories.php")
    suspend fun getFoodCategories():Response<FoodListCategories>
}
