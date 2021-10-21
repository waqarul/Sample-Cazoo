package com.wtmcodex.cazoo.core.di.module

import android.content.Context
import androidx.room.Room
import com.wtmcodex.cazoo.constants.DatabaseConstants
import com.wtmcodex.cazoo.core.data.source.local.CazooProductDatabase
import com.wtmcodex.cazoo.core.data.source.local.dao.CazooProductDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): CazooProductDatabase {
        return Room.databaseBuilder(
            context,
            CazooProductDatabase::class.java,
            DatabaseConstants.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideGitHubRepositoryDao(database: CazooProductDatabase): CazooProductDao {
        return database.cazooProductDao()
    }
}