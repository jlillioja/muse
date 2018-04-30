package io.grandlabs.muse

import android.view.View
import io.grandlabs.muse.HomeFragment
import io.grandlabs.muse.NavigationAction
import io.grandlabs.muse.NavigationController
import io.grandlabs.muse.NavigationProvider
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class HomeComponent @Inject constructor(
        private val navigationController: NavigationController,
        private val navigationProvider: NavigationProvider
): AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        linearLayout {
            textView {
                text = "Another test"
            }

            button {
                onClick {
                    navigationController.navigateTo(NavigationAction.TRAINING)
                }
            }
        }
    }
}