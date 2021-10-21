package com.wtmcodex.cazoo.core.di.module

import com.wtmcodex.cazoo.core.di.scope.PerActivity
import com.wtmcodex.cazoo.features.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @PerActivity
    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity(): MainActivity
}