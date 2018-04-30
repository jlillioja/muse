package io.grandlabs.muse.dagger

import android.app.Application
import dagger.Module
import dagger.Provides
import io.grandlabs.muse.*
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

    @Provides
    fun provideTrainingFragment(trainingComponent: TrainingComponent): TrainingFragment {
        return TrainingFragment().apply { component = trainingComponent }
    }

    @Provides
    fun provideHomeFragment(homeComponent: HomeComponent): HomeFragment {
        return HomeFragment().apply { component = homeComponent }
    }

}