package com.wtmcodex.cazoo.core.data.source.remote.retrofit

import com.wtmcodex.cazoo.constants.APIConstants
import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface CazooApiService {
    @GET(APIConstants.GET_PRODUCTS)
    suspend fun getProducts(): Response<ProductResponse>
}