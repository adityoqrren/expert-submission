package id.interconnect.gamestar

import android.annotation.SuppressLint
import android.app.Application
import id.interconnect.gamestar.core.di.databaseModule
import id.interconnect.gamestar.core.di.networkModule
import id.interconnect.gamestar.core.di.repositoryModule
import id.interconnect.gamestar.di.useCaseModule
import id.interconnect.gamestar.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@SuppressLint
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}