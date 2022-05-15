package com.ebenezer.gana.mamatasty.data.network

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json


@Entity(tableName = "food")
data class Food(
    @PrimaryKey
    @Json(name = "idCategory")
    val id: String,

    @Json(name = "strCategory") val title: String,
    @Json(name = "strCategoryThumb") val imageUrl: String,
    @Json(name = "strCategoryDescription") val description: String,
)