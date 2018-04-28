package io.grandlabs.muse

import android.app.Application
import io.grandlabs.muse.dagger.AndroidModule
import io.grandlabs.muse.dagger.ApplicationComponent
import io.grandlabs.muse.dagger.DaggerApplicationComponent

class MuseApplication : Application() {

    companion object {
        //platformStatic allow access it from java code
        @JvmStatic lateinit var graph: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        graph = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .build()
        graph.inject(this)
    }

}