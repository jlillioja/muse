package io.grandlabs.muse

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import io.grandlabs.muse.NavigationAction.*
import org.jetbrains.anko.defaultSharedPreferences
import org.jetbrains.anko.frameLayout
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    @Inject lateinit var trainingFragment: TrainingFragment
    @Inject lateinit var homeFragment: HomeFragment
    @Inject lateinit var smlFragment: SMLFragment
    @Inject lateinit var continuumFragment: ContinuumFragment
    @Inject lateinit var mealLogFragment: MealLogFragment

    private val shouldShowTrainingKey = "SHOULD_SHOW_TRAINING"

    @IdRes private val frameLayoutId = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        View.generateViewId()
    } else {
        1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MuseApplication.graph.inject(this)

        navigationProvider.actions.subscribe {
            navigateTo(it)
        }

        if (defaultSharedPreferences.getBoolean(shouldShowTrainingKey, true)) {
            navigateTo(TRAINING)
        } else {
            navigateTo(HOME)
        }
        
        frameLayout { 
            id = frameLayoutId
        }
    }

    private fun navigateTo(action: NavigationAction) {
        with(supportFragmentManager.beginTransaction()) {
            when (action) {
                TRAINING -> {
                    replace(frameLayoutId, trainingFragment)
                }
                HOME -> {
                    replace(frameLayoutId, homeFragment)
                }
                SML -> {
                    replace(frameLayoutId, smlFragment)
                }
                CONTINUUM -> {
                    replace(frameLayoutId, continuumFragment)
                }
                LOG -> {
                    replace(frameLayoutId, mealLogFragment)
                }
            }

            commit()
        }
    }
}

fun FragmentTransaction.addOrShow(@IdRes containerViewId: Int, fragment: Fragment) {
    if (fragment.isAdded) {
        show(fragment)
    } else {
        add(containerViewId, fragment)
    }
}