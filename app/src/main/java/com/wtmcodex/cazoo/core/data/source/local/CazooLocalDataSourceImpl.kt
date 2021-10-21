package com.wtmcodex.cazoo.core.data.source.local

import com.wtmcodex.cazoo.core.data.source.local.dao.CazooProductDao
import com.wtmcodex.cazoo.core.data.source.local.entity.DBCazooProduct
import com.wtmcodex.cazoo.core.di.scope.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CazooLocalDataSourceImpl @Inject constructor(
    private val repositoryDao: CazooProductDao,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    CazooProductLocalDataSource {
    override suspend fun insertAll(items: List<DBCazooProduct>) = withContext(ioDispatcher) {
        return@withContext repositoryDao.insertAll(items)
    }

    override suspend fun insertOrUpdate(repository: DBCazooProduct) =
        withContext(ioDispatcher) {
            val item = repositoryDao.getProductByID(repository.productID)
            if (item == null) {
                repositoryDao.insertRepository(repository)
            } else {
                repositoryDao.updateProduct(repository)
            }
        }

    override suspend fun getAllProducts(): List<DBCazooProduct>? =
        withContext(ioDispatcher) {
            return@withContext repositoryDao.getAllProducts()
        }

    override suspend fun getProductByID(id: Long): DBCazooProduct? =
        withContext(ioDispatcher) {
            return@withContext repositoryDao.getProductByID(id)
        }

    override suspend fun deleteAllProducts() = withContext(ioDispatcher) {
        return@withContext repositoryDao.deleteAllProducts()
    }
}