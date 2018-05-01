package io.grandlabs.muse.ui_components

import android.view.View
import android.widget.LinearLayout
import io.grandlabs.muse.MuseFragment
import io.grandlabs.muse.NavigationAction
import io.grandlabs.muse.NavigationController
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class TrainingComponent
@Inject constructor(
        private val navigationController: NavigationController
) : AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL

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