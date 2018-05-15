package io.grandlabs.muse.ui_components

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.constraint.ConstraintSet.MATCH_CONSTRAINT_WRAP
import android.support.constraint.ConstraintSet.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Space
import android.widget.TextView
import io.grandlabs.muse.*
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject


class TrainingComponent
@Inject constructor(
        private val navigationController: NavigationController,
        private val ctx: Context
) : AnkoComponent<MuseFragment> {

    private val numberOfTrainingStates = 20
    private val configDependentStates = 9..14

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    private lateinit var saveButton: Button
    private lateinit var titleText: TextView
    private lateinit var defaultConstraints: ConstraintSet

    private lateinit var miaLeftAndDown: ImageView
    private lateinit var mealIcon: ImageView
    private lateinit var homeIcon: ImageView

    private lateinit var textBottomSpace: Space

    private lateinit var progressCircle: ImageView
    private lateinit var progressMeter: ImageView
    private lateinit var progressText: TextView
    private lateinit var starStart: Space
    private lateinit var star1: ImageView
    private lateinit var star2: ImageView
    private lateinit var star3: ImageView
    private lateinit var star4: ImageView
    private lateinit var star5: ImageView


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
            }.lparams(dip(120), wrapContent)

            nextButton = button {
                id = generateViewId()
                text = "next"
                textAppearance = R.style.buttonText
                transformationMethod = null
                background = resources.getDrawable(R.drawable.button)
                onClick {
                    moveToTrainingState(trainingState + 1)
                }
            }.lparams(dip(120), wrapContent)

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
            }.lparams(dip(54), dip(54))

            homeIcon = imageView(R.drawable.icon_home) {
                id = generateViewId()
                visibility = GONE

                onClick {
                    moveToTrainingState(trainingState + 1)
                }
            }

            progressCircle = imageView(R.drawable.progress_foreground) {
                id = generateViewId()
                visibility = GONE
                backgroundDrawable = resources.getDrawable(R.drawable.progress_background)
            }.lparams(dip(254), dip(254))

            progressMeter = imageView(R.drawable.progress_1) {
                id = generateViewId()
                visibility = GONE
            }

            starStart = space {
                id = generateViewId()
            }

            star1 = imageView(R.drawable.star) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(58), dip(63))
            star2 = imageView(R.drawable.star) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(58), dip(63))
            star3 = imageView(R.drawable.star) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(58), dip(63))
            star4 = imageView(R.drawable.star) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(58), dip(63))
            star5 = imageView(R.drawable.star) {
                id = generateViewId()
                visibility = GONE
            }.lparams(dip(58), dip(63))

            defaultConstraints = applyConstraintSet {
                titleText {
                    connect(
                            TOP to TOP of PARENT_ID margin dip(276),
                            LEFT to LEFT of PARENT_ID margin dip(20),
                            RIGHT to RIGHT of PARENT_ID margin dip(20)
                    )

                    defaultHeight = MATCH_CONSTRAINT_WRAP
                    defaultWidth = MATCH_CONSTRAINT_WRAP
                }

                textBottomSpace {
                    connect(BOTTOM to BOTTOM of titleText margin ctx.dip(25))
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
                            BOTTOM to BOTTOM of PARENT_ID margin dip(20)
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
                            BOTTOM to BOTTOM of PARENT_ID margin dip(15))
                }

                homeIcon {
                    connect(LEFT to LEFT of PARENT_ID margin dip(34),
                            BOTTOM to BOTTOM of PARENT_ID margin dip(15))
                }

                progressCircle {
                    connect(TOP to TOP of PARENT_ID margin dip(106),
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID)
                }

                progressMeter {
                    connect(TOP to TOP of progressCircle,
                            LEFT to LEFT of progressCircle,
                            RIGHT to RIGHT of progressCircle,
                            BOTTOM to BOTTOM of progressCircle)
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
            homeIcon.visibility = GONE
            progressCircle.visibility = GONE
            progressMeter.visibility = GONE
            star1.visibility = GONE
            star2.visibility = GONE
            star3.visibility = GONE
            star4.visibility = GONE
            star5.visibility = GONE
            defaultConstraints.applyTo(constraintLayout)

            if (trainingState == 1) {
                backButton.visibility = GONE

                constraintLayout.applyConstraintSet {
                    nextButton {
                        connect(LEFT to LEFT of PARENT_ID,
                                RIGHT to RIGHT of PARENT_ID)
                    }
                }
            }
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
                        verticalBias = 0f
                    }
                }
            }
            if ((trainingState in 9..12) and Build.isContinuum) {
                constraintLayout.applyConstraintSet {
                    titleText {
                        connect(TOP to TOP of PARENT_ID margin ctx.dip(115))
                    }
                }
            }
            if ((trainingState == 13) or (trainingState == 14)) {
                if (Build.isSml) {
                    constraintLayout.applyConstraintSet {
                        backButton {
                            connect(TOP to BOTTOM of textBottomSpace,
                                    LEFT to LEFT of PARENT_ID,
                                    RIGHT to LEFT of nextButton)
                            verticalBias = 0f
                        }

                        nextButton {
                            connect(TOP to BOTTOM of textBottomSpace,
                                    LEFT to RIGHT of backButton,
                                    RIGHT to RIGHT of PARENT_ID)
                            verticalBias = 0f
                        }
                    }

                    if (trainingState == 14) {
                        nextButton.visibility = GONE
                        saveButton.visibility = VISIBLE
                    }
                } else {
                    constraintLayout.applyConstraintSet {
                        backButton {
                            connect(TOP to BOTTOM of textBottomSpace,
                                    LEFT to LEFT of PARENT_ID,
                                    RIGHT to LEFT of nextButton)
                            verticalBias = 0f
                        }

                        nextButton {
                            connect(TOP to BOTTOM of textBottomSpace,
                                    LEFT to RIGHT of backButton,
                                    RIGHT to RIGHT of PARENT_ID)
                            verticalBias = 0f
                        }
                    }

                    if (trainingState == 14) {
                        nextButton.visibility = GONE
                        saveButton.visibility = VISIBLE
                    }
                }
            }
            if (trainingState == 15) {
                val starStartPosition = ctx.dip(-200).toFloat()
                val starEndPosition = ctx.dip(210).toFloat()

//                progressCircle.visibility = VISIBLE
//                progressMeter.visibility = VISIBLE
//                progressMeter.alpha = 0f

                star1.visibility = VISIBLE
                star1.translationY = starStartPosition
                star1.translationX = ctx.dip(176).toFloat()

                star2.visibility = VISIBLE
                star2.translationY = starStartPosition
                star2.translationX = ctx.dip(200).toFloat()

                star3.visibility = VISIBLE
                star3.translationY = starStartPosition
                star3.translationX = ctx.dip(150).toFloat()

                star4.visibility = VISIBLE
                star4.translationY = starStartPosition
                star4.translationX = ctx.dip(210).toFloat()

                star5.visibility = VISIBLE
                star5.translationY = starStartPosition
                star5.translationX = ctx.dip(100).toFloat()

                titleText.visibility = GONE
                backButton.visibility = GONE
                nextButton.visibility = GONE

                val animationStartTime: Long = 300
                val animationOffset: Long = 100
                val slideDuration: Long = 750
                val fadeDuration: Long = 750

                val star1SlideInStartTime: Long = animationStartTime
                val star2SlideInStartTime: Long = star1SlideInStartTime + animationOffset
                val star3SlideInStartTime: Long = star2SlideInStartTime + animationOffset
                val star4SlideInStartTime: Long = star3SlideInStartTime + animationOffset
                val star5SlideInStartTime: Long = star4SlideInStartTime + animationOffset

                val star1slideAnimation = ObjectAnimator
                        .ofFloat(star1, View.TRANSLATION_Y, starEndPosition)
                        .setDuration(slideDuration)
                        .apply { startDelay = star1SlideInStartTime }

                val star1fadeAnimation = ObjectAnimator
                        .ofFloat(star1, View.ALPHA, 0f)
                        .setDuration(fadeDuration)

                val star1Animations = AnimatorSet()
                star1Animations.playSequentially(star1slideAnimation, star1fadeAnimation)
                star1Animations.start()

                val star2slideAnimation = ObjectAnimator
                        .ofFloat(star2, View.TRANSLATION_Y, starEndPosition)
                        .setDuration(slideDuration)
                        .apply { startDelay = star2SlideInStartTime }

                val star2fadeAnimation = ObjectAnimator
                        .ofFloat(star2, View.ALPHA, 0f)
                        .setDuration(fadeDuration)

                val star2Animations = AnimatorSet()
                star2Animations.playSequentially(star2slideAnimation, star2fadeAnimation)
                star2Animations.start()

                val star3slideAnimation = ObjectAnimator
                        .ofFloat(star3, View.TRANSLATION_Y, starEndPosition)
                        .setDuration(slideDuration)
                        .apply { startDelay = star3SlideInStartTime }

                val star3fadeAnimation = ObjectAnimator
                        .ofFloat(star3, View.ALPHA, 0f)
                        .setDuration(fadeDuration)

                val star3Animations = AnimatorSet()
                star3Animations.playSequentially(star3slideAnimation, star3fadeAnimation)
                star3Animations.start()

                val star4slideAnimation = ObjectAnimator
                        .ofFloat(star4, View.TRANSLATION_Y, starEndPosition)
                        .setDuration(slideDuration)
                        .apply { startDelay = star4SlideInStartTime }

                val star4fadeAnimation = ObjectAnimator
                        .ofFloat(star4, View.ALPHA, 0f)
                        .setDuration(fadeDuration)

                val star4Animations = AnimatorSet()
                star4Animations.playSequentially(star4slideAnimation, star4fadeAnimation)
                star4Animations.start()

                val star5slideAnimation = ObjectAnimator
                        .ofFloat(star5, View.TRANSLATION_Y, starEndPosition)
                        .setDuration(slideDuration)
                        .apply { startDelay = star5SlideInStartTime }

                val star5fadeAnimation = ObjectAnimator
                        .ofFloat(star5, View.ALPHA, 0f)
                        .setDuration(fadeDuration)

                val star5Animations = AnimatorSet()
                star5Animations.playSequentially(star5slideAnimation, star5fadeAnimation)
                star5Animations.start()
//
//                ObjectAnimator
//                        .ofFloat(progressMeter, View.ALPHA, 1f)
//                        .apply { startDelay = 1000 }
//                        .start()

                constraintLayout.postDelayed({ moveToTrainingState(16) }, 2500)
            }
            if (trainingState == 16) {
                constraintLayout.applyConstraintSet {
                    titleText {
                        connect(TOP to TOP of PARENT_ID margin ctx.dip(400))
                    }

                    backButton {
                        connect(TOP to BOTTOM of textBottomSpace,
                                LEFT to LEFT of PARENT_ID,
                                RIGHT to LEFT of nextButton)
                        verticalBias = 0f
                    }

                    nextButton {
                        connect(TOP to BOTTOM of textBottomSpace,
                                LEFT to RIGHT of backButton,
                                RIGHT to RIGHT of PARENT_ID)
                        verticalBias = 0f
                    }
                }
            }
            if (trainingState == 18) {
                homeIcon.visibility = VISIBLE
                nextButton.visibility = GONE

                constraintLayout.applyConstraintSet {
                    titleText {
                        connect(TOP to TOP of PARENT_ID margin ctx.dip(374))
                    }

                    backButton {
                        connect(TOP to BOTTOM of textBottomSpace,
                                LEFT to LEFT of titleText margin ctx.dip(16))
                        verticalBias = 0f
                        horizontalBias = 0f
                    }


                }
            }
            if ((trainingState == 19) or (trainingState == 20)) {
                constraintLayout.applyConstraintSet {
                    titleText {
                        connect(TOP to TOP of PARENT_ID margin ctx.dip(369))
                    }
                }
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
        9 -> if (Build.isSml) "For a meal that’s about the same as what you usually eat at that time of day, use the middle button. " else "For a meal that’s about the same as what you usually eat at that time of day, leave the fork in the middle."
        10 -> if (Build.isSml) "If you’re having less than usual, use the “smaller” button." else "If you’re having less than usual, touch or slide the fork to the left. All the way over would be a really small snack..."
        11 -> if (Build.isSml) "If you’re eating a larger meal than usual for you, here’s your button." else "...and slide in-between to estimate how much smaller this meal is than your usual."
        12 -> if (Build.isSml) "If you take insulin for snacks, log them with this snack button. If you don't take insulin for snacks, just ignore this button." else "If you’re eating a larger meal than usual for you, slide to the right for about how much bigger."
        13 -> if (Build.isSml) "Remember, this is all about YOU--if it’s what you usually eat it’s the middle button!\n" else "Remember, this is all about YOU--if it’s what you usually eat, slide to the middle!\n"
        14 -> if (Build.isSml) "When you are done entering the meal, save it.\n" else "When you are done entering the meal, save it.\n"
        15 -> ""
        16 -> "We’ll earn stars each time you log a meal, as I learn more about how your body reacts to food and insulin doses. When the bucket's full, we’re ready to fly! Then I’ll start suggesting insulin doses that are unique to you.\n"
        17 -> "The meals you log will be listed here."
        18 -> "Touch \"home\" to return to the nest.\n"
        19 -> "As a lifelong learner, I’ll keep learning as long as you keep logging, so that I can keep recommending safe, personalized insulin doses."
        20 -> "That’s it!\nJust a few stars to go and we can fly together.\nSee you at dinner time!"
        else -> "I've got problems!"

    }

    private fun getBackgroundForTrainingState(trainingState: Int): Drawable? {
        return when (trainingState) {
            1 -> ctx.resources.getDrawable(R.drawable.ob_10_bg)
            2 -> ctx.resources.getDrawable(R.drawable.ob_20_bg)
            3 -> ctx.resources.getDrawable(R.drawable.ob_30_bg)
            4 -> ctx.resources.getDrawable(R.drawable.ob_40_bg)
            5 -> ctx.resources.getDrawable(R.drawable.ob_50_bg)
            6 -> ctx.resources.getDrawable(R.drawable.ob_60_bg)
            7 -> ctx.resources.getDrawable(R.drawable.ob_70_bg)
            8 -> ctx.resources.getDrawable(R.drawable.ob_90_bg)
            9 -> if (Build.isSml) ctx.resources.getDrawable(R.drawable.ob_100_a_bg) else ctx.resources.getDrawable(R.drawable.ob_100_b_bg)
            10 -> if (Build.isSml) ctx.resources.getDrawable(R.drawable.ob_101_a_bg) else ctx.resources.getDrawable(R.drawable.ob_101_b_bg)
            11 -> if (Build.isSml) ctx.resources.getDrawable(R.drawable.ob_102_a_bg) else ctx.resources.getDrawable(R.drawable.ob_102_bg)
            12 -> if (Build.isSml) ctx.resources.getDrawable(R.drawable.ob_103_a_bg) else ctx.resources.getDrawable(R.drawable.ob_103_b_bg)
            13 -> if (Build.isSml) ctx.resources.getDrawable(R.drawable.ob_110_a_bg) else ctx.resources.getDrawable(R.drawable.ob_110_b_bg)
            14 -> if (Build.isSml) ctx.resources.getDrawable(R.drawable.ob_120_a_bg) else ctx.resources.getDrawable(R.drawable.ob_120_b_bg)
            15 -> ctx.resources.getDrawable(R.drawable.ob_prob_anim_bg)
            16 -> ctx.resources.getDrawable(R.drawable.ob_150_bg)
            17 -> ctx.resources.getDrawable(R.drawable.ob_160_a_bg)
            18 -> ctx.resources.getDrawable(R.drawable.ob_170_bg)
            19 -> ctx.resources.getDrawable(R.drawable.ob_180_bg)
            20 -> ctx.resources.getDrawable(R.drawable.ob_190_bg)
            else -> null
        }
    }
}

