package com.wtmcodex.cazoo.core.data.source.repository

import com.wtmcodex.cazoo.core.data.model.CazooProductModel
import com.wtmcodex.cazoo.core.data.model.backend.ProductResponse
import com.wtmcodex.cazoo.core.data.source.local.CazooProductLocalDataSource
import com.wtmcodex.cazoo.core.data.source.local.entity.DBCazooProduct
import com.wtmcodex.cazoo.core.data.source.remote.CazooProductRemoteDataSource
import com.wtmcodex.cazoo.core.di.scope.IoDispatcher
import com.wtmcodex.cazoo.helpers.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class CazooProductRepositoryImpl @Inject constructor(
    private val localDataSource: CazooProductLocalDataSource,
    private val remoteDataSource: CazooProductRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CazooProductRepository {

    override suspend fun getProducts(): Result<ProductResponse> =
        withContext(Dispatchers.IO) {

            when (val response = remoteDataSource.getProducts()) {
                is Result.Success -> {
                    if (response.data != null) {
                        Result.Success(response.data)
                    } else {
                        Result.Success(null)
                    }
                }
                is Result.Error -> {
                    Result.Error(response.exception)
                }
                else -> Result.Loading
            }
        }

    override suspend fun shouldBookMarkProduct(product: CazooProductModel) =
        withContext(ioDispatcher) {
            localDataSource.insertOrUpdate(
                DBCazooProduct(
                    productID = product.id,
                    name = product.name,
                    imageURL = product.imageURL,
                    releaseDate = product.releaseDate,
                    description = product.description,
                    longDescription = product.longDescription,
                    rating = product.rating,
                    bookmarked = product.isBookMarked,
                    price = product.price,
                    currency = product.currency,
                    available = product.available
                )
            )
        }

    override suspend fun isProductBookMarked(id: Long): Boolean = withContext(ioDispatcher) {
        val product = localDataSource.getProductByID(id)
        return@withContext product?.bookmarked == true
    }

    override suspend fun getProductByID(id: Long): CazooProductModel? =
        withContext(ioDispatcher) {
            val dbProduct = localDataSource.getProductByID(id) ?: return@withContext null
            return@withContext CazooProductModel(
                id = dbProduct.productID,
                name = dbProduct.name,
                imageURL = dbProduct.imageURL,
                releaseDate = dbProduct.releaseDate,
                description = dbProduct.description,
                longDescription = dbProduct.longDescription,
                rating = dbProduct.rating,
                isBookMarked = dbProduct.bookmarked,
                price = dbProduct.price,
                currency = dbProduct.currency,
                available = dbProduct.available
            )
        }
}