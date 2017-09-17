package net.davidcrotty.hercules.presenter

import android.content.res.Resources
import net.davidcrotty.hercules.R
import net.davidcrotty.hercules.model.Set
import net.davidcrotty.hercules.view.ExerciseView
import net.davidcrotty.hercules.view.Skippable
import org.joda.time.Period
import org.joda.time.format.PeriodFormatter
import org.joda.time.format.PeriodFormatterBuilder

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class ExercisePresenter(private val view: ExerciseView) {

    private var currentTrackIndex: Int? = null
    private var currentSetList: ArrayList<Set>? = null
    private val MS_SCALAR = 1000L
    private val timeFormat: PeriodFormatter by lazy {
            PeriodFormatterBuilder()
                    .printZeroAlways()
                    .minimumPrintedDigits(2)
                    .maximumParsedDigits(2)
                    .appendMinutes()
                    .appendSeparator(":")
                    .printZeroAlways()
                    .minimumPrintedDigits(2)
                    .maximumParsedDigits(2)
                    .appendSeconds()
                    .toFormatter() }

    fun initializeSetsFrom(setList: ArrayList<Set>) {
        currentSetList = setList
        if(setList.isEmpty()) return
        for(i in 0 until setList.size) run {
            view.addSet(setList[i], i + 1)
        }
        currentTrackIndex = 0
        val first = setList.first()
        resetUiUsing(first)
    }

    fun showTitlesFrom(resources: Resources, setList: ArrayList<Set>) {
        if(setList.isEmpty()) return
        val index = currentTrackIndex ?: return
        view.updateTitle(setList[index].title)
        view.updateSubtitle(resources.getString(R.string.plan_subtitle, setList.size, setList.first().repitions))
        updateNextUp(resources, index)
    }

    fun nextSet(skippable: Skippable, resources: Resources) {
        var index = currentTrackIndex ?: return
        skippable.next(index)
        index++
        currentTrackIndex = index

        currentSetList?.let {
            if(index >= it.size) return@let
            val nextSet = it[index]
            resetUiUsing(nextSet)
            view.updateTitle(it[index].title)
            updateNextUp(resources, index)
        }
    }

    private fun updateNextUp(resources: Resources, index: Int) {
        val next = currentSetList?.peek(index + 1)
        if(next == null) {
            view.updateNextUp(resources.getString(R.string.plan_done))
        } else {
            view.updateNextUp(resources.getString(R.string.plan_next, next.timeSeconds, next.title))
        }
    }

    private fun timeFrom(seconds: Int) : String {
        val period = Period(seconds.toLong() * MS_SCALAR)
        return timeFormat.print(period)
    }

    private fun resetUiUsing(nextSet: Set) {
        val time = timeFrom(nextSet.timeSeconds)
        view.resetMainProgress(nextSet.repitions, time)

    }
}

private fun java.util.ArrayList<Set>.peek(index: Int) : Set? {
    if(index >= this.size) return null
    return this[index]
}
