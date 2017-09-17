package net.davidcrotty.hercules

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_exercise.*
import net.davidcrotty.hercules.model.Set
import net.davidcrotty.hercules.presenter.ExercisePresenter
import net.davidcrotty.hercules.presenter.Updatable
import net.davidcrotty.hercules.view.ExerciseView
import net.davidcrotty.hercules.view.RepProgressView
import net.davidcrotty.hercules.view.SetState
import net.davidcrotty.hercules.view.Skippable

/**
 * Created by David Crotty on 15/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class ExerciseActivity : AppCompatActivity(), ExerciseView, Skippable, Updatable {

    companion object {
        val SET_KEY = "SET_KEY"
    }

    private var currentMax: Int = 0
    private var currentProgress: Int = 0

    private var isCounting: Boolean = false
    private lateinit var presenter: ExercisePresenter
    private lateinit var countDownHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)
        val supportActionbar = supportActionBar
        supportActionbar?.setDisplayHomeAsUpEnabled(true)
        supportActionbar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionbar?.title = ""

        countDownHandler = Handler()
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

    override fun resetMainProgress(repetitions: Int, timeFormatted: String, seconds: Int) {
        currentProgress = 0
        currentMax = seconds
        progress_countdown.max = currentProgress
        progress_countdown.progress = currentMax
        updateReps(repetitions)
        updateTimeRemaining(timeFormatted)
    }

    override fun updateNextUp(text: String) {
        next_up_text.text = text
    }

    override fun next(viewIndex: Int) {
        getRepView(viewIndex)?.updateStateTo(SetState.DONE)
    }

    override fun previous(viewIndex: Int) {
        getRepView(viewIndex)?.updateStateTo(SetState.PENDING)
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
        play_pause_toggle.setOnClickListener {
            if(isCounting) {
                switchToPlayDrawable()
                countDownHandler.removeCallbacksAndMessages(null) //clears timer
            } else {
                play_pause_toggle.setImageResource(R.drawable.pause_icon)
                val index = presenter.currentTrackIndex ?: return@setOnClickListener
                val setItem = presenter.currentSetList?.get(index) ?: return@setOnClickListener
                getRepView(index)?.updateStateTo(SetState.IN_PROGRESS)
                updateTimerComponents(setItem.timeSeconds, setItem.repitions, index)
                startTimer()
            }
            isCounting = !isCounting
        }
    }

    override fun onPause() {
        super.onPause()
        countDownHandler.removeCallbacksAndMessages(null)
    }

    override fun onResume() {
        super.onResume()
        //user returning from explicitly back grounding the app. Intent was to pause, see youtube app behaviour for example.
        switchToPlayDrawable()
    }

    override fun updateTimerComponents(max: Int, maxReps: Int, setIndex: Int) {
        progress_countdown.max = max
        progress_countdown.progress = currentProgress
        val view = getRepView(setIndex)
        view?.max = max
        view?.progress = currentProgress
        currentMax = max
        updateTimeRemaining(presenter.timeFrom(currentMax - currentProgress))
        val repPercentage = currentProgress.toFloat() / currentMax.toFloat()
        val remainingReps = maxReps * repPercentage
        remaining_reps.text = maxReps.minus(remainingReps.toInt()).toString()
    }

    override fun update() {
        countDownHandler.postDelayed({
            currentProgress++
            val viewIndex = presenter.currentTrackIndex
            viewIndex?.let {
                val set = presenter.currentSetList?.get(it) ?: return@let
                updateTimerComponents(set.timeSeconds, set.repitions, viewIndex)
                startTimer()
            }
        }, 1000)
    }

    private fun switchToPlayDrawable() {
        play_pause_toggle.setImageResource(R.drawable.play_icon)
    }

    private fun startTimer() {
        countDownHandler.postDelayed({
            if(currentProgress >= currentMax) {
                countDownHandler.removeCallbacksAndMessages(null)
                presenter.nextSet(skippable = this, resources = resources)
                val viewIndex = presenter.currentTrackIndex
                viewIndex?.let {
                    val set = presenter.currentSetList?.get(it) ?: return@let
                    updateSetFrom(set, viewIndex)
                }
            } else {
                currentProgress++
                val viewIndex = presenter.currentTrackIndex
                viewIndex?.let {
                    val set = presenter.currentSetList?.get(it) ?: return@let
                    updateSetFrom(set, viewIndex)
                }
            }
        }, 1000)
    }

    private fun updateSetFrom(set: Set, viewIndex: Int) {
        updateTimerComponents(set.timeSeconds, set.repitions, viewIndex)
        startTimer()
    }

    private fun getRepView(viewIndex: Int) : RepProgressView? {
        val childCount = rep_host.childCount
        if(viewIndex > childCount || viewIndex < 0) return null
        val view = rep_host.getChildAt(viewIndex)
        if(view is RepProgressView) {
            return view
        }

        return null
    }
}