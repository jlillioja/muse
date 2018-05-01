package io.grandlabs.muse.ui_components

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import io.grandlabs.muse.MealCreator
import io.grandlabs.muse.MuseFragment
import io.grandlabs.muse.NavigationAction
import io.grandlabs.muse.NavigationController
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class ContinuumComponent
@Inject constructor(val mealCreator: MealCreator, val navigationController: NavigationController)
    : AnkoComponent<MuseFragment> {
    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            var mealSize = 0 // Ranging from 0 to 100

            seekBar {
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}

                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        mealSize = progress
                    }
                })
            }

            button {
                text = "DONE"
                onClick {
                    mealCreator.logMeal(mealSize)
                    navigationController.navigateTo(NavigationAction.HOME)
                }
            }
        }
    }
}