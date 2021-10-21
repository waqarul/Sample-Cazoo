package com.wtmcodex.cazoo.core.di.module

import com.wtmcodex.cazoo.core.data.source.local.CazooLocalDataSourceImpl
import com.wtmcodex.cazoo.core.data.source.local.CazooProductLocalDataSource
import com.wtmcodex.cazoo.core.data.source.remote.CazooProductRemoteDataSource
import com.wtmcodex.cazoo.core.data.source.remote.CazooProductRemoteDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourcesModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: CazooLocalDataSourceImpl): CazooProductLocalDataSource

    @Binds
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: CazooProductRemoteDataSourceImpl): CazooProductRemoteDataSource
}