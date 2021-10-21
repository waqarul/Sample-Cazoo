package com.wtmcodex.cazoo.core.data.source.local.dao

import androidx.room.*
import com.wtmcodex.cazoo.constants.DatabaseConstants
import com.wtmcodex.cazoo.core.data.source.local.entity.DBCazooProduct

@Dao
interface CazooProductDao : BaseDao<DBCazooProduct> {

    @Insert
    suspend fun insertRepository(repository: DBCazooProduct)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateProduct(repository: DBCazooProduct)

    @Query(DatabaseConstants.QUERY_SELECT_PRODUCT)
    suspend fun getAllProducts(): List<DBCazooProduct>?

    @Query(DatabaseConstants.QUERY_SELECT_PRODUCT_BY_ID)
    suspend fun getProductByID(id: Long): DBCazooProduct?

    @Query(DatabaseConstants.QUERY_DELETE_PRODUCT)
    suspend fun deleteAllProducts()
}