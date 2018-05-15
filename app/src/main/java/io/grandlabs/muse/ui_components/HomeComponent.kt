package io.grandlabs.muse.ui_components

import android.graphics.Color
import android.support.constraint.ConstraintLayout.LayoutParams.PARENT_ID
import android.support.v4.content.res.ResourcesCompat
import android.view.Gravity
import android.view.View
import android.view.View.generateViewId
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import io.grandlabs.muse.*
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder
import org.jetbrains.anko.constraint.layout.ConstraintSetBuilder.Side.*
import org.jetbrains.anko.constraint.layout.applyConstraintSet
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.constraint.layout.matchConstraint
import org.jetbrains.anko.custom.style
import org.jetbrains.anko.sdk25.coroutines.onClick
import javax.inject.Inject

class HomeComponent @Inject constructor(
        private val navigationController: NavigationController,
        private val navigationProvider: NavigationProvider
): AnkoComponent<MuseFragment> {

    private lateinit var navBar: ImageView
    private lateinit var progressFrame: ImageView
    private lateinit var progressText: TextView
    private lateinit var miaMessageBox: ImageView
    private lateinit var miaMessage: TextView

    override fun createView(ui: AnkoContext<MuseFragment>): View = with(ui) {
        constraintLayout {
            backgroundDrawable = resources.getDrawable(R.drawable.home_bg)

            navBar = imageView(R.drawable.nav) {
                id = generateViewId()
                this.adjustViewBounds = false
            }.lparams(matchConstraint, wrapContent)

            progressFrame = imageView(R.drawable.progress_foreground) {
                id = generateViewId()
                backgroundDrawable = resources.getDrawable(R.drawable.progress_background)
            }

            progressText = textView("Mia is\nlearning") {
                id = generateViewId()
                typeface = ResourcesCompat.getFont(ui.ctx, R.font.source_sans_pro_regular)
                textSize = 37f
                textColor = Color.rgb(110, 66, 12)
                gravity = Gravity.CENTER
            }

            miaMessageBox = imageView(R.drawable.mia_message_box) {
                id = generateViewId()
                onClick {
                    navigationController.navigateTo(NavigationAction.TRAINING)
                }
            }

            miaMessage = textView("Are you learning too? Click here to review how we work together.") {
                id = generateViewId()
                typeface = ResourcesCompat.getFont(ui.ctx, R.font.source_sans_pro_regular)
                textSize = 18f
                textColor = Color.rgb(110, 66, 12)
//                background = resources.getDrawable(R.drawable.text_background)

                gravity = Gravity.CENTER_VERTICAL
            }.lparams(matchConstraint, wrapContent)

            applyConstraintSet {
                navBar {
                    connect(
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID,
                            BOTTOM to BOTTOM of PARENT_ID
                    )
                }

                progressFrame {
                    connect(
                            TOP to TOP of PARENT_ID margin dip(106),
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID
                    )
                }

                progressText {
                    connect(
                            TOP to TOP of progressFrame,
                            LEFT to LEFT of progressFrame,
                            RIGHT to RIGHT of progressFrame,
                            BOTTOM to BOTTOM of progressFrame
                    )
                }

                miaMessageBox {
                    connect(
                            TOP to TOP of PARENT_ID margin dip(358),
                            LEFT to LEFT of PARENT_ID,
                            RIGHT to RIGHT of PARENT_ID
                    )
                }

                miaMessage {
                    connect(
                            BOTTOM to BOTTOM of miaMessageBox margin dip(20),
                            LEFT to LEFT of miaMessageBox margin dip(15),
                            RIGHT to RIGHT of miaMessageBox margin dip(15)
                    )
                }
            }
        }
    }
}