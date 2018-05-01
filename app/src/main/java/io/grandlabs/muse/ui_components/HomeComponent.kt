package io.grandlabs.muse.ui_components

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import io.grandlabs.muse.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class HomeComponent @Inject constructor(
        private val navigationController: NavigationController,
        private val navigationProvider: NavigationProvider
): AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            textView {
                gravity = Gravity.CENTER
                text = "Home"
            }.lparams(matchParent, wrapContent)

            button {
                text = "TRAINING"
                onClick {
                    navigationController.navigateTo(NavigationAction.TRAINING)
                }
            }.lparams(matchParent, wrapContent)

            button {
                text = "LOG MEAL (SML)"
                onClick {
                    navigationController.navigateTo(NavigationAction.SML)
                }
            }.lparams(matchParent, wrapContent)

            button {
                text = "LOG MEAL (CONTINUUM)"
                onClick {
                    navigationController.navigateTo(NavigationAction.CONTINUUM)
                }
            }.lparams(matchParent, wrapContent)

            button {
                text = "MEAL LOG"
                onClick {
                    navigationController.navigateTo(NavigationAction.LOG)
                }
            }.lparams(matchParent, wrapContent)
        }
    }
}