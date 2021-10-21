package com.wtmcodex.cazoo.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wtmcodex.cazoo.core.data.source.local.dao.CazooProductDao
import com.wtmcodex.cazoo.core.data.source.local.entity.DBCazooProduct

@Database(entities = [DBCazooProduct::class], version = 1, exportSchema = false)
abstract class CazooProductDatabase : RoomDatabase() {
    abstract fun cazooProductDao(): CazooProductDao

}