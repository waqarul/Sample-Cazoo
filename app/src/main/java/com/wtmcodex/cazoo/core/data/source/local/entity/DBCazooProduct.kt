package com.wtmcodex.cazoo.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wtmcodex.cazoo.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_PRODUCT)
class DBCazooProduct(
    @NonNull
    @ColumnInfo(name = "id")
    @PrimaryKey val productID: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageURL") val imageURL: String,
    @ColumnInfo(name = "releaseDate") val releaseDate: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "longDescription") val longDescription: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "is_bookmarked") val bookmarked: Boolean,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "is_available") val available: Boolean,
)