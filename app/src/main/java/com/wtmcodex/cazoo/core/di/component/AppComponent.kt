package com.wtmcodex.cazoo.core.di.component

import android.app.Application
import com.wtmcodex.cazoo.CazooApplication
import com.wtmcodex.cazoo.core.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
        AppModule::class, DatabaseModule::class,
        DataSourcesModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DispatcherModule::class,
        ViewModelModule::class]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(application: CazooApplication)
}