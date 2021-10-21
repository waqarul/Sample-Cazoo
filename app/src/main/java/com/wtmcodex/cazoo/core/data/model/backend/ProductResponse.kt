package com.wtmcodex.cazoo.core.data.model.backend

data class ProductResponse(
    val header: Header,
    val filters: List<String>,
    val products: List<Product>
)