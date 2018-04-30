package io.grandlabs.muse

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import io.grandlabs.muse.NavigationAction.*
import org.jetbrains.anko.defaultSharedPreferences
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigationProvider: NavigationProvider

    private val SHOULD_SHOW_TRAINING = "SHOULD_SHOW_TRAINING"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MuseApplication.graph.inject(this)

        navigationProvider.actions.subscribe {
            navigateTo(it)
        }

        if (defaultSharedPreferences.getBoolean(SHOULD_SHOW_TRAINING, true)) {
            navigateTo(TRAINING)
        } else {
            navigateTo(HOME)
        }
    }

    private fun navigateTo(action: NavigationAction) {
        val transaction = supportFragmentManager.beginTransaction()
        when (action) {
            TRAINING -> {
            }
            HOME -> {
            }
            SML -> {
            }
            CONTINUUM -> {
            }
            LOG -> {
            }
        }

        transaction.commit()
    }
}
