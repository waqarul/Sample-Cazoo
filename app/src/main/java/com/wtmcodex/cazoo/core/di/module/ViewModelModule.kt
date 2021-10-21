package com.wtmcodex.cazoo.core.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wtmcodex.cazoo.ViewModelFactory
import com.wtmcodex.cazoo.core.di.key.ViewModelKey
import com.wtmcodex.cazoo.features.home.HomeViewModel
import com.wtmcodex.cazoo.features.productdetail.ProductDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @IntoMap
    @Binds
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @IntoMap
    @Binds
    @ViewModelKey(ProductDetailViewModel::class)
    abstract fun bindProductDetailViewModel(viewModel: ProductDetailViewModel): ViewModel
}