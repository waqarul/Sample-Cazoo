package com.wtmcodex.cazoo.core.data.source.remote

import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import com.wtmcodex.cazoo.helpers.Result

interface CazooProductRemoteDataSource {
    suspend fun getProducts(): Result<ProductResponse>
}