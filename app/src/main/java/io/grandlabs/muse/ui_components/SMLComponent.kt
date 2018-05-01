package io.grandlabs.muse.ui_components

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import io.grandlabs.muse.*
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.button
import org.jetbrains.anko.linearLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class SMLComponent
@Inject constructor(
        private val mealCreator: MealCreator,
        private val navigationController: NavigationController)
    : AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            var selectedMealSize = MealSize.MEDIUM

            linearLayout {
                val small = button {
                    text = "SMALL"
                    onClick {
                        selectedMealSize = MealSize.SMALL
                    }
                }

                val medium = button {
                    text = "MEDIUM"
                    onClick {
                        selectedMealSize = MealSize.MEDIUM
                    }
                }

                val large = button {
                    text = "LARGE"
                    onClick {
                        selectedMealSize = MealSize.LARGE
                    }
                }
            }

            button {
                text = "DONE"
                onClick {
                    mealCreator.logMeal(selectedMealSize)
                    navigationController.navigateTo(NavigationAction.HOME)
                }
            }
        }
    }
}