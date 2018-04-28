package io.grandlabs.muse.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import io.grandlabs.muse.NavigationController
import io.grandlabs.muse.NavigationProvider
import io.grandlabs.muse.Navigator
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

    private val navigator = Navigator()

    @Provides
    @Singleton
    fun provideNavigationProvider(): NavigationProvider {
        return navigator
    }

    @Provides
    @Singleton
    fun provideNavigationController(): NavigationController {
        return navigator
    }

}