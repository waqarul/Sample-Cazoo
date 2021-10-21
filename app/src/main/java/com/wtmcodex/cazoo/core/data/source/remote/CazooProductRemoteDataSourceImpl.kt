package com.wtmcodex.cazoo.core.data.source.remote

import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import com.wtmcodex.cazoo.core.data.source.remote.retrofit.CazooApiService
import com.wtmcodex.cazoo.core.di.scope.IoDispatcher
import com.wtmcodex.cazoo.helpers.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CazooProductRemoteDataSourceImpl @Inject constructor(
    private val apiService: CazooApiService,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CazooProductRemoteDataSource {

    override suspend fun getProducts(): Result<ProductResponse> =
        withContext(ioDispatcher) {
            return@withContext try {
                val result = apiService.getProducts()
                if (result.isSuccessful) {
                    val networkWeather = result.body()
                    Result.Success(networkWeather)
                } else {
                    Result.Success(null)
                }
            } catch (exception: Exception) {
                Result.Error(exception)
            }
        }

}