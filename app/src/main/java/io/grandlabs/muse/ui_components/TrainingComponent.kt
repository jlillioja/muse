package io.grandlabs.muse.ui_components

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet.MATCH_CONSTRAINT_WRAP
import android.support.constraint.ConstraintSet.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.TextView
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
        private val navigationController: NavigationController,
        private val context: Context
) : AnkoComponent<MuseFragment> {

    private val numberOfTrainingStates = 9

    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var titleText: TextView


    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        constraintLayout = constraintLayout {
            titleText = textView {
                id = generateViewId()
                typeface = ResourcesCompat.getFont(ui.ctx, R.font.source_sans_pro_regular)
                textSize = 18f
                textColor = Color.rgb(110, 66, 12)
                background = resources.getDrawable(R.drawable.text_background)

                gravity = Gravity.CENTER_VERTICAL
            }.lparams(0, wrapContent)

            val buttons = linearLayout {
                id = generateViewId()

                gravity = Gravity.CENTER

                backButton = button {
                    text = "back"
                    textAppearance = R.style.buttonText
                    transformationMethod = null
                    background = resources.getDrawable(R.drawable.button)

                    onClick {
                        if (trainingState > 1) {
                            trainingState -= 1
                            moveToTrainingState(trainingState)
                        }
                    }
                }.lparams {
                    margin = 50
                }

                nextButton = button {
                    text = "next"
                    textAppearance = R.style.buttonText
                    transformationMethod = null
                    background = resources.getDrawable(R.drawable.button)
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
        moveToTrainingState(1)
        return constraintLayout
    }

    private var trainingState = 1
    fun moveToTrainingState() {
        moveToTrainingState(trainingState)
    }

    private fun getTextForTrainingState(trainingState: Int) = when (trainingState){
        1-> "Hello!\nI’m Mia, your new assistant! It’s a pleasure to meet you. "
        2-> "It’s my mission to shoulder—or rather, wing—some of the burden of your diabetes, so that you can focus on other things.\nWe’ve got almost everything we need to partner up…"
        else->"Fuck you!"

    }

    fun moveToTrainingState(trainingState: Int) {
        this.trainingState = trainingState
        if (trainingState in 1..numberOfTrainingStates) {
            constraintLayout.background = getBackgroundForTrainingState(trainingState)
            titleText.text = getTextForTrainingState(trainingState)
        } else navigationController.navigateTo(NavigationAction.HOME)
    }


    private fun getBackgroundForTrainingState(trainingState: Int): Drawable? {
        return when (trainingState) {
            1 -> context.resources.getDrawable(R.drawable.ob_1_0_bg)
            2 -> context.resources.getDrawable(R.drawable.ob_2_0_bg)
            3 -> context.resources.getDrawable(R.drawable.ob_3_0_bg)
            4 -> context.resources.getDrawable(R.drawable.ob_4_0_bg)
            5 -> context.resources.getDrawable(R.drawable.ob_5_0_bg)
            6 -> context.resources.getDrawable(R.drawable.ob_6_0_bg)
            7 -> context.resources.getDrawable(R.drawable.ob_7_0_bg)
            8 -> context.resources.getDrawable(R.drawable.ob_8_0_bg)
            9 -> context.resources.getDrawable(R.drawable.ob_9_0_bg)
            else -> null
        }
    }
}