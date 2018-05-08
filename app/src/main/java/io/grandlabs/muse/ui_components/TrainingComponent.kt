package io.grandlabs.muse.ui_components

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintSet.MATCH_CONSTRAINT_WRAP
import android.support.constraint.ConstraintSet.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.graphics.TypefaceCompatUtil
import android.view.Gravity
import android.view.View
import android.view.View.generateViewId
import android.widget.LinearLayout
import io.grandlabs.muse.MuseFragment
import io.grandlabs.muse.NavigationAction
import io.grandlabs.muse.NavigationController
import io.grandlabs.muse.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class TrainingComponent
@Inject constructor(
        private val navigationController: NavigationController
) : AnkoComponent<MuseFragment> {

    private val numberOfTrainingStates = 9

    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        constraintLayout {
            var trainingState = 1

            fun moveToTrainingState(trainingState: Int) {
                if (trainingState in 1..numberOfTrainingStates) {
                    this@constraintLayout.background = getBackgroundForTrainingState(trainingState)
                } else navigationController.navigateTo(NavigationAction.HOME)
            }

            moveToTrainingState(trainingState)

            val titleText = textView {
                id = generateViewId()
                text = "Hello!  \n" +
                        "I’m Mia, your new assistant! It’s a pleasure to meet you. "
                typeface = ResourcesCompat.getFont(ui.ctx, R.font.source_sans_pro_regular)
                textSize = 18f
                textColor = Color.BLACK
                backgroundColor = Color.WHITE
            }.lparams(0, wrapContent)

            val buttons = linearLayout {
                id = generateViewId()

                gravity = Gravity.CENTER

                val backButton = button {
                    text = "Back"
                    onClick {
                        if (trainingState > 1) {
                            trainingState -= 1
                            moveToTrainingState(trainingState)
                        }
                    }
                }.lparams {
                    margin = 50
                }

                button {
                    text = "Next"
                    onClick {
                        trainingState += 1
                        moveToTrainingState(trainingState)
                    }
                }.lparams {
                    margin = 50
                }
            }.lparams(matchParent, wrapContent)

            applyConstraintSet {
                titleText {
                    connect(
                            TOP to TOP of PARENT_ID margin dip(250),
                            LEFT to LEFT of PARENT_ID margin dip(20),
                            RIGHT to RIGHT of PARENT_ID margin dip(20)
                    )

                    defaultHeight = MATCH_CONSTRAINT_WRAP
                    defaultWidth = MATCH_CONSTRAINT_WRAP
                }

                buttons {
                    connect(
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID,
                            BOTTOM to BOTTOM of PARENT_ID margin dip(30)
                    )
                }
            }
        }
    }

    private fun AnkoContext<MuseFragment>.getBackgroundForTrainingState(trainingState: Int): Drawable? {
        return when (trainingState) {
            1 -> resources.getDrawable(R.drawable.ob_1_0_bg)
            2 -> resources.getDrawable(R.drawable.ob_2_0_bg)
            3 -> resources.getDrawable(R.drawable.ob_3_0_bg)
            4 -> resources.getDrawable(R.drawable.ob_4_0_bg)
            5 -> resources.getDrawable(R.drawable.ob_5_0_bg)
            6 -> resources.getDrawable(R.drawable.ob_6_0_bg)
            7 -> resources.getDrawable(R.drawable.ob_7_0_bg)
            8 -> resources.getDrawable(R.drawable.ob_8_0_bg)
            9 -> resources.getDrawable(R.drawable.ob_9_0_bg)
            else -> null
        }
    }
}