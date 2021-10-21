package com.wtmcodex.cazoo.core.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {

    /**
     * Insert a list in the database. If the task already exists, replace it.
     *
     * @param list the category to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertAll(List: List<T>)

}