package com.wtmcodex.cazoo.core.data.source.local

import com.wtmcodex.cazoo.core.data.source.local.entity.DBCazooProduct

interface CazooProductLocalDataSource {
    suspend fun insertAll(items: List<DBCazooProduct>)
    suspend fun insertOrUpdate(repository: DBCazooProduct)
    suspend fun getAllProducts(): List<DBCazooProduct>?
    suspend fun getProductByID(id: Long): DBCazooProduct?
    suspend fun deleteAllProducts()
}