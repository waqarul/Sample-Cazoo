package com.wtmcodex.cazoo.core.data.model.backend

import com.wtmcodex.cazoo.core.data.enum.TypeEnum

data class Product(
    val name: String,
    val type: TypeEnum,
    val id: Long,
    val color: String,
    val imageURL: String,
    val colorCode: String,
    val available: Boolean,
    val releaseDate: Long,
    val description: String,
    val longDescription: String,
    val rating: Double,
    val price: Price
)