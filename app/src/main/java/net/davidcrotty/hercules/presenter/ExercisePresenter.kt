package net.davidcrotty.hercules.presenter

import android.content.res.Resources
import net.davidcrotty.hercules.R
import net.davidcrotty.hercules.model.Set
import net.davidcrotty.hercules.view.ExerciseView
import org.joda.time.Period
import org.joda.time.format.PeriodFormatter
import org.joda.time.format.PeriodFormatterBuilder

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class ExercisePresenter(private val view: ExerciseView) {

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

    fun showSetsFrom(setList: ArrayList<Set>) {
        if(setList.isEmpty()) return
        for(i in 0 until setList.size) run {
            view.addSet(setList[i], i + 1)
        }
        val first = setList.first()
        val time = timeFrom(first.timeSeconds)
        view.resetMainProgress(first.repitions, time)
    }

    fun showSubtitleFrom(resources: Resources, setList: ArrayList<Set>) {
        if(setList.isEmpty()) return
        view.updateSubtitle(resources.getString(R.string.plan_subtitle, setList.size, setList.first().repitions))
    }

    private fun timeFrom(seconds: Int) : String {
        val period = Period(seconds.toLong() * MS_SCALAR)
        return timeFormat.print(period)
    }
}