package com.orion.todoapp

import android.app.Application
import com.orion.todoapp.di.networkModule
import com.orion.todoapp.di.persistenceModule
import com.orion.todoapp.di.repositoryModule
import com.orion.todoapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApplication)
            modules(persistenceModule)
            modules(networkModule)
            modules(repositoryModule)
            modules(viewModelModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
