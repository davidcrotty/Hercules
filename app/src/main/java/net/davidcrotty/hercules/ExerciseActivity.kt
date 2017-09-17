package net.davidcrotty.hercules

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_exercise.*
import net.davidcrotty.hercules.model.Set
import net.davidcrotty.hercules.presenter.ExercisePresenter
import net.davidcrotty.hercules.view.ExerciseView
import net.davidcrotty.hercules.view.RepProgressView
import net.davidcrotty.hercules.view.SetState
import net.davidcrotty.hercules.view.Skippable

/**
 * Created by David Crotty on 15/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class ExerciseActivity : AppCompatActivity(), ExerciseView, Skippable {

    companion object {
        val SET_KEY = "SET_KEY"
    }

    private lateinit var presenter: ExercisePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        val supportActionbar = supportActionBar
        supportActionbar?.setDisplayHomeAsUpEnabled(true)
        supportActionbar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionbar?.title = ""

        presenter = ExercisePresenter(view = this)

        if(intent.hasExtra(SET_KEY)) {
            val setList = intent.getParcelableArrayListExtra<Set>(SET_KEY)
            presenter.initializeSetsFrom(setList)
            presenter.showTitlesFrom(resources, setList)
        }
        bindListeners()
    }

    override fun updateSubtitle(text: String) {
        subtitle.text = text
    }

    override fun addSet(set: Set, index: Int) {
        val repProgressView = RepProgressView(this)
        repProgressView.updateStateTo(set.state)
        repProgressView.updatePositionTo(index)
        val params = LinearLayout.LayoutParams(resources.getDimensionPixelSize(R.dimen.rep_height),
                resources.getDimensionPixelSize(R.dimen.rep_width))
        params.marginStart = resources.getDimensionPixelSize(R.dimen.rep_margin)
        params.marginEnd = resources.getDimensionPixelSize(R.dimen.rep_margin)
        rep_host.addView(repProgressView, params)
    }

    override fun updateTitle(text: String) {
        exercise_title.text = text
    }

    override fun resetMainProgress(repetitions: Int, timeFormatted: String) {
        progress_countdown.max = 10
        progress_countdown.progress = 0
        updateReps(repetitions)
        updateTimeRemaining(timeFormatted)
    }

    override fun updateNextUp(text: String) {
        next_up_text.text = text
    }

    override fun next(viewIndex: Int) {
        val childCount = rep_host.childCount
        if(viewIndex > childCount) return
        val view = rep_host.getChildAt(viewIndex)
        if(view is RepProgressView) {
            view.updateStateTo(SetState.DONE)
        }
    }

    override fun previous(viewIndex: Int) {
        val childCount = rep_host.childCount
        if(viewIndex >= childCount || viewIndex < 0) return
        val view = rep_host.getChildAt(viewIndex)
        if(view is RepProgressView) {
            view.updateStateTo(SetState.PENDING)
        }
    }

    override fun updateReps(reps: Int) {
        remaining_reps.text = reps.toString()
    }

    override fun updateTimeRemaining(text: String) {
        time_remaining_text.text = text
    }

    private fun bindListeners() {
        next_set.setOnClickListener {
            presenter.nextSet(skippable = this, resources = resources)
        }
        previous_set.setOnClickListener {
            presenter.previousSet(skippable = this, resources = resources)
        }
    }
}