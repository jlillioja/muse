package io.grandlabs.muse

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.grandlabs.muse.ui_components.HomeComponent
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import javax.inject.Inject

abstract class MuseFragment: Fragment() {
    var component: AnkoComponent<MuseFragment>? = null

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        UmbyApplication.graph.inject(this)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return component?.createView(AnkoContext.create(ctx, this))
    }
}

class HomeFragment: MuseFragment() {
    @Inject lateinit var homeComponent: HomeComponent
}
class TrainingFragment: MuseFragment()
class SMLFragment: MuseFragment()
class ContinuumFragment: MuseFragment()
class MealLogFragment: MuseFragment()

