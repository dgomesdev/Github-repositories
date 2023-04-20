package dev.dgomes.githubRepositories

import android.app.Application
import dev.dgomes.githubRepositories.data.di.DataModule
import dev.dgomes.githubRepositories.domain.di.DomainModule
import dev.dgomes.githubRepositories.presentation.di.PresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@App)
        }

        DataModule.load()
        DomainModule.load()
        PresentationModule.load()
    }
}