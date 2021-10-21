package com.wtmcodex.cazoo.core.di.module

import com.wtmcodex.cazoo.core.data.source.repository.CazooProductRepository
import com.wtmcodex.cazoo.core.data.source.repository.CazooProductRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindCazooProductRepository(gitHubRepositoryImpl: CazooProductRepositoryImpl): CazooProductRepository
}