package com.wtmcodex.cazoo.core.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CazooProductModel(
    val name: String,
    val id: Long,
    val color: String = "",
    val imageURL: String,
    val colorCode: String = "",
    val available: Boolean,
    val releaseDate: String,
    val description: String,
    val longDescription: String,
    val rating: Float,
    val price: Double,
    val currency: String,
    var isBookMarked: Boolean
) : Parcelable