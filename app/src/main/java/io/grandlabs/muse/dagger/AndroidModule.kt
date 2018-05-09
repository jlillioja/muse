package io.grandlabs.muse.dagger

import android.app.Application
import android.content.Context
import android.media.projection.MediaProjection
import dagger.Module
import dagger.Provides
import io.grandlabs.muse.*
import io.grandlabs.muse.ui_components.*
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

    @Provides
    fun provideSmlFragment(smlComponent: SMLComponent): SMLFragment {
        return SMLFragment().apply { component = smlComponent }
    }

    @Provides
    fun provideContinuumFragment(continuumComponent: ContinuumComponent): ContinuumFragment {
        return ContinuumFragment().apply { component = continuumComponent }
    }

    @Provides
    fun provideMealLogFragment(mealLogComponent: MealLogComponent): MealLogFragment {
        return MealLogFragment().apply { component = mealLogComponent }
    }

    @Singleton
    @Provides
    fun provideMealManager(): MealManager {
        return MealManager()
    }

    @Provides
    fun provideMealCreator(mealManager: MealManager): MealCreator {
        return mealManager
    }

    @Provides
    fun provideMealLogProvider(mealManager: MealManager): MealLogProvider {
        return mealManager
    }

    @Provides
    fun provideContext(): Context {
        return application
    }

}