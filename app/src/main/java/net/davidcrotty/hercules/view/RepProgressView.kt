package net.davidcrotty.hercules.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import net.davidcrotty.hercules.R
import android.view.View
import kotlinx.android.synthetic.main.rep_progress.view.*


/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class RepProgressView : FrameLayout {

    private var state: Int? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)

        val typedArray = context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.RepProgress,
                0, 0)

        var state: Int? = null

        try {
            state = typedArray.getInteger(R.styleable.RepProgress_state, 0)
        } finally {
            typedArray.recycle()
        }

        state?.let {
            updateStateTo(it)
        }
    }

    fun updateStateTo(state: Int) {
        this.state = state
        when(state) {
            0 -> {
                done_icon.visibility = View.VISIBLE
                order_text.visibility = View.GONE
                progress_task.visibility = View.GONE
            }
            1 -> {
                progress_task.visibility = View.VISIBLE
                order_text.visibility = View.VISIBLE
                done_icon.visibility = View.GONE
            }
            2 -> {
                pending_task.visibility = View.VISIBLE
                order_text.visibility = View.VISIBLE
                progress_task.visibility = View.GONE
            }
        }
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.rep_progress, this)
    }
}