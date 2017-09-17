package net.davidcrotty.hercules.view

import net.davidcrotty.hercules.model.Set

/**
 * Created by David Crotty on 16/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
interface ExerciseView {
    fun updateTitle(text: String)
    fun updateSubtitle(text: String)
    fun addSet(set: Set, index: Int)
    fun resetMainProgress(repetitions: Int, timeFormatted: String)
    fun updateNextUp(text: String)
    fun updateReps(reps: Int)
}