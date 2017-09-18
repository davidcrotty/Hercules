package net.davidcrotty.hercules

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import kotlinx.android.synthetic.main.activity_plan.*


/**
 * Created by David Crotty on 18/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class PlanActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        if(savedInstanceState == null &&
                intent.hasExtra(DashboardActivity.REVEAL_Y) &&
                intent.hasExtra(DashboardActivity.REVEAL_X)) {
            root.visibility = View.INVISIBLE
            val observer = root.viewTreeObserver
            if(observer.isAlive) {
                val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
                    override fun onGlobalLayout() {
                        revealActivity(intent.getIntExtra(DashboardActivity.REVEAL_X, 0),
                                intent.getIntExtra(DashboardActivity.REVEAL_Y, 0))
                    }
                }
                observer.addOnGlobalLayoutListener(listener)
            }
        }

        bindListeners()
    }

    private fun bindListeners() {
        done_button.setOnClickListener({
            finish()
        })
    }

    private fun revealActivity(x: Int, y: Int) {
        val finalRadius = (Math.max(root.width, root.height) * 1.1).toFloat()

        // create the animator for this view (the start radius is zero)
        val circularReveal = ViewAnimationUtils.createCircularReveal(root, x, y, 0f, finalRadius)
        circularReveal.duration = 400
        circularReveal.interpolator = AccelerateInterpolator()

        root.visibility = View.VISIBLE
        circularReveal.start()
    }
}