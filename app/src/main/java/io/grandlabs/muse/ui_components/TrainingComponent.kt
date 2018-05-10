package io.grandlabs.muse.ui_components

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintSet.MATCH_CONSTRAINT_WRAP
import android.support.constraint.ConstraintSet.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.transition.TransitionManager
import android.view.Gravity
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Space
import android.widget.TextView
import io.grandlabs.muse.MuseFragment
import io.grandlabs.muse.NavigationAction
import io.grandlabs.muse.NavigationController
import io.grandlabs.muse.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject
import android.view.animation.BounceInterpolator
import android.transition.ChangeBounds
import org.jetbrains.anko.constraint.layout.constraintSet


class TrainingComponent
@Inject constructor(
        private val navigationController: NavigationController,
        private val ctx: Context
) : AnkoComponent<MuseFragment> {

    private val numberOfTrainingStates = 16

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    private lateinit var saveButton: Button
    private lateinit var titleText: TextView
    private lateinit var defaultConstraints: ConstraintSet

    private lateinit var miaLeftAndDown: ImageView
    private lateinit var mealIcon: ImageView

    private lateinit var textBottomSpace: Space

    private lateinit var progressCircle: ImageView
    private lateinit var star1: ImageView


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

            textBottomSpace = space {
                id = generateViewId()
            }

            backButton = button {
                id = generateViewId()
                text = "back"
                textAppearance = R.style.buttonText
                transformationMethod = null
                background = resources.getDrawable(R.drawable.button)

                onClick {
                    if (trainingState > 1) {
                        moveToTrainingState(trainingState - 1)
                    }
                }
            }

            nextButton = button {
                id = generateViewId()
                text = "next"
                textAppearance = R.style.buttonText
                transformationMethod = null
                background = resources.getDrawable(R.drawable.button)
                onClick {
                    moveToTrainingState(trainingState + 1)
                }
            }

            saveButton = button {
                id = generateViewId()
                visibility = GONE
                text = "Save"
                textAppearance = R.style.buttonText
                transformationMethod = null
                background = resources.getDrawable(R.drawable.save_button)
                onClick {
                    moveToTrainingState(trainingState + 1)
                }
            }.lparams(dip(156), wrapContent)

            miaLeftAndDown = imageView(R.drawable.mia_point_down_left) {
                id = generateViewId()
                visibility = GONE
            }

            mealIcon = imageView(R.drawable.icon_meal) {
                id = generateViewId()
                visibility = GONE

                onClick {
                    moveToTrainingState(trainingState + 1)
                }
            }

            progressCircle = imageView(R.drawable.progress_foreground) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(254), dip(254))

            star1 = imageView(R.drawable.star) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(58), dip(63))

            defaultConstraints = applyConstraintSet {
                titleText {
                    connect(
                            TOP to TOP of PARENT_ID margin dip(250),
                            LEFT to LEFT of PARENT_ID margin dip(20),
                            RIGHT to RIGHT of PARENT_ID margin dip(20)
                    )

                    defaultHeight = MATCH_CONSTRAINT_WRAP
                    defaultWidth = MATCH_CONSTRAINT_WRAP
                }

                textBottomSpace {
                    connect(BOTTOM to BOTTOM of titleText margin ctx.dip(50))
                }

                backButton {
                    connect(
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to LEFT of nextButton,
                            BOTTOM to BOTTOM of PARENT_ID margin dip(30)
                    )
                }

                nextButton {
                    connect(
                            LEFT to RIGHT of backButton,
                            RIGHT to RIGHT of PARENT_ID,
                            BOTTOM to BOTTOM of PARENT_ID margin dip(30)
                    )
                }

                saveButton {
                    connect(
                            RIGHT to RIGHT of PARENT_ID margin dip(16),
                            BOTTOM to BOTTOM of PARENT_ID margin dip(40)
                    )
                }

                miaLeftAndDown {
                    connect(
                            RIGHT to RIGHT of PARENT_ID margin dip(18),
                            BOTTOM to BOTTOM of PARENT_ID margin dip(92)
                    )
                }

                mealIcon {
                    connect(LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID,
                            BOTTOM to BOTTOM of PARENT_ID margin dip(51))
                }

                progressCircle {
                    connect(TOP to TOP of PARENT_ID margin dip(106),
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID)
                }

                star1 {
                    connect(
                            BOTTOM to TOP of PARENT_ID,
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID
                    )
                }

            }


        }
        moveToTrainingState(1)
        return constraintLayout
    }

    private var trainingState = 1

    fun moveToTrainingState(trainingState: Int) {
        this.trainingState = trainingState
        if (trainingState in 1..numberOfTrainingStates) {
            constraintLayout.background = getBackgroundForTrainingState(trainingState)
            titleText.text = getTextForTrainingState(trainingState)

            nextButton.visibility = VISIBLE
            miaLeftAndDown.visibility = GONE
            mealIcon.visibility = GONE
            progressCircle.visibility = GONE
            defaultConstraints.applyTo(constraintLayout)

            if (trainingState == 8) {
                nextButton.visibility = GONE
                miaLeftAndDown.visibility = VISIBLE
                mealIcon.visibility = VISIBLE
                constraintLayout.applyConstraintSet {
                    titleText {
                        connect(TOP to TOP of PARENT_ID margin ctx.dip(125))
                    }

                    backButton {
                        connect(
                                TOP to BOTTOM of textBottomSpace
                        )
                    }
                }
            }
            if ((trainingState == 13) or (trainingState == 14)) {
                constraintLayout.applyConstraintSet {
                    backButton {
                        connect(TOP to BOTTOM of textBottomSpace,
                                LEFT to LEFT of PARENT_ID,
                                RIGHT to LEFT of nextButton)
                        verticalBias = 0.1f
                    }

                    nextButton {
                        connect(TOP to BOTTOM of textBottomSpace,
                                LEFT to RIGHT of backButton,
                                RIGHT to RIGHT of PARENT_ID)
                        verticalBias = 0.1f
                    }
                }

                if (trainingState == 14) {
                    nextButton.visibility = GONE
                    saveButton.visibility = VISIBLE
                } else {
                    nextButton.visibility = VISIBLE
                    saveButton.visibility = GONE
                }
            }
            if (trainingState == 15) {
                progressCircle.visibility = VISIBLE
                star1.visibility = VISIBLE

                titleText.visibility = GONE
                backButton.visibility = GONE
                nextButton.visibility = GONE

                val constraints = constraintLayout.constraintSet {
                    connect(TOP of star1 to TOP of PARENT_ID margin ctx.dip(210))
                }

                constraintLayout.postDelayed({
                    val transition = ChangeBounds()
//                    transition.interpolator = BounceInterpolator()
                    transition.duration = 1000
                    TransitionManager.beginDelayedTransition(constraintLayout, transition)
                    constraints.applyTo(constraintLayout)
                }, 10)

//                moveToTrainingState(16)
            }
        } else navigationController.navigateTo(NavigationAction.HOME)
    }

    private fun getTextForTrainingState(trainingState: Int) = when (trainingState) {
        1 -> "Hello!\nI’m Mia, your new assistant! It’s a pleasure to meet you. "
        2 -> "It’s my mission to shoulder—or rather, wing—some of the burden of your diabetes, so that you can focus on other things.\n\nWe’ve got almost everything we need to partner up…"
        3 -> "I’ll use the sensor on your arm to keep a (big, steady) eye on your blood sugar. You’ll see your current reading here."
        4 -> "The smart insulin pen will send signals so I can track the insulin doses you take."
        5 -> "I’ll just need a little training from you to really wise up.\n\nOver the next week,  will you help me learn about YOUR body? Then I’ll be able to automatically suggest insulin doses that are unique to you. "
        6 -> "When it’s time for a mealtime insulin dose, let me know if you’re eating about the same, or more, or less than usual for that time of day.\n\nFor example, is this your usual lunch, or are you eating a bit lighter than the last few days? "
        7 -> "If you are used to thinking about carbs, then tell me if today’s meal has fewer carbs , a similar amount of carbs, or more carbs than what you usually eat at this time of day."
        8 -> "You don’t have to worry about what anyone else would call a lot or a little—what matters is the way YOU usually eat, around that time.\n\n(For me,  breakfast is always after dusk. And it’s sometimes a few beetles; sometimes a hearty mole!)   \n\nTouch the icon at the bottom to tell me about meals."
        9 -> "For a meal that’s about the same as what you usually eat at that time of day, use the middle button. "
        10 -> "If you’re having less than usual, use the “smaller” button."
        11 -> "If you’re eating a larger meal than usual for you, here’s your button."
        12 -> "If you take insulin for snacks, log them with this snack button. If you don't take insulin for snacks, just ignore this button."
        13 -> "Remember, this is all about YOU--if it’s what you usually eat it’s the middle button!\n"
        14 -> "When you are done entering the meal, save it.\n"
        15 -> ""
        16 -> "We’ll earn stars each time you log a meal, as I learn more about how your body reacts to food and insulin doses. When the bucket's full, we’re ready to fly! Then I’ll start suggesting insulin doses that are unique to you.\n"
        else -> "I've got problems!"

    }


    private fun getBackgroundForTrainingState(trainingState: Int): Drawable? {
        return when (trainingState) {
            1 -> ctx.resources.getDrawable(R.drawable.ob_1_0_bg)
            2 -> ctx.resources.getDrawable(R.drawable.ob_2_0_bg)
            3 -> ctx.resources.getDrawable(R.drawable.ob_3_0_bg)
            4 -> ctx.resources.getDrawable(R.drawable.ob_4_0_bg)
            5 -> ctx.resources.getDrawable(R.drawable.ob_5_0_bg)
            6 -> ctx.resources.getDrawable(R.drawable.ob_6_0_bg)
            7 -> ctx.resources.getDrawable(R.drawable.ob_7_0_bg)
            8 -> ctx.resources.getDrawable(R.drawable.ob_9_0_bg)
            9 -> ctx.resources.getDrawable(R.drawable.ob_100_a_bg)
            10 -> ctx.resources.getDrawable(R.drawable.ob_101_a_bg)
            11 -> ctx.resources.getDrawable(R.drawable.ob_102_a_bg)
            12 -> ctx.resources.getDrawable(R.drawable.ob_103_a_bg)
            13 -> ctx.resources.getDrawable(R.drawable.ob_110_a_bg)
            14 -> ctx.resources.getDrawable(R.drawable.ob_120_a_bg)
            15 -> ctx.resources.getDrawable(R.drawable.ob_prob_anim_bg)
            else -> null
        }
    }
}