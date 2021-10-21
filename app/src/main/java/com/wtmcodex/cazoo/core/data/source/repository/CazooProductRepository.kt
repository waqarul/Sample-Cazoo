package com.wtmcodex.cazoo.core.data.source.repository

import com.wtmcodex.cazoo.core.data.model.CazooProductModel
import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import com.wtmcodex.cazoo.helpers.Result

interface CazooProductRepository {
    suspend fun getProducts(): Result<ProductResponse>?
    suspend fun shouldBookMarkProduct(product: CazooProductModel)
    suspend fun isProductBookMarked(id: Long): Boolean
    suspend fun getProductByID(id: Long): CazooProductModel?

}