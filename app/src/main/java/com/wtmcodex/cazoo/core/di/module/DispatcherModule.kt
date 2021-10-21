package com.wtmcodex.cazoo.core.di.module

import com.wtmcodex.cazoo.core.di.scope.DefaultDispatcher
import com.wtmcodex.cazoo.core.di.scope.IoDispatcher
import com.wtmcodex.cazoo.core.di.scope.MainDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
object DispatcherModule {

    @Provides
    @DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @IoDispatcher
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
