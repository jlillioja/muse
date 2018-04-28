package io.grandlabs.muse.dagger

import dagger.Component
import io.grandlabs.muse.MainActivity
import io.grandlabs.muse.MuseApplication
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(application: MuseApplication)
}