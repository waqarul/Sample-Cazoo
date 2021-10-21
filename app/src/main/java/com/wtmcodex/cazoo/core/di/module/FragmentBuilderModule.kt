package com.wtmcodex.cazoo.core.di.module

import com.wtmcodex.cazoo.features.home.HomeFragment
import com.wtmcodex.cazoo.features.productdetail.ProductDetailFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeProductDetailFragment(): ProductDetailFragment
}