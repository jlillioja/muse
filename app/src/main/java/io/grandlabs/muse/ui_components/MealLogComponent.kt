package io.grandlabs.muse.ui_components

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import io.grandlabs.muse.MealLogProvider
import io.grandlabs.muse.MuseFragment
import io.grandlabs.muse.NavigationAction
import io.grandlabs.muse.NavigationController
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class MealLogComponent
@Inject constructor(
        private val navigationController: NavigationController,
        private val mealLogProvider: MealLogProvider
) : AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>) = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            scrollView {
                val meals = linearLayout {
                    orientation = LinearLayout.VERTICAL

                    mealLogProvider.meals.forEach {
                        linearLayout {
                            textView {
                                text = it.mealSize.toString()
                            }

                            textView {
                                text = it.date.toString()
                            }
                        }
                    }
                }
            }.lparams(matchParent, wrapContent, 1.0f)
            button {
                gravity = Gravity.CENTER
                text = "DONE"
                onClick {
                    navigationController.navigateTo(NavigationAction.HOME)
                }
            }.lparams(matchParent, wrapContent, 0f)
        }
    }
}