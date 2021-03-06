package net.davidcrotty.progressview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.view.View
import kotlinx.android.synthetic.main.rep_progress.view.*

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class RepProgressView : FrameLayout {

    private var state: SetState? = null
    private var position: Int = 0
    var progress: Int = 0
        set(value) {
            field = value
            progress_task.progress = value
        }
    var max: Int = 10
        set(value) {
            field = value
            progress_task.max = value
        }

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
            updateStateTo(SetState.from(it))
        }
    }

    fun updatePositionTo(position: Int) {
        this.position = position
        order_text.text = position.toString()
    }

    fun updateStateTo(state: SetState) {
        this.state = state
        when(state) {
            SetState.DONE -> {
                done_icon.visibility = View.VISIBLE
                order_text.visibility = View.GONE
                progress_task.visibility = View.GONE
            }
            SetState.IN_PROGRESS -> {
                progress_task.visibility = View.VISIBLE
                order_text.visibility = View.VISIBLE
                done_icon.visibility = View.GONE
            }
            SetState.PENDING -> {
                pending_task.visibility = View.VISIBLE
                order_text.visibility = View.VISIBLE
                progress_task.visibility = View.GONE
                done_icon.visibility = View.GONE
            }
        }
    }

    private fun init(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.rep_progress, this)
    }
}