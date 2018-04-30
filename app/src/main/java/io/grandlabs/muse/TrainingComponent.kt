package io.grandlabs.muse

import android.view.View
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class TrainingComponent
@Inject constructor(
        private val navigationController: NavigationController
) : AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        linearLayout {
            textView {
                text = "Test"
            }

            button {
                text = "Go Home"
                onClick {
                    navigationController.navigateTo(NavigationAction.HOME)
                }
            }
        }
    }
}