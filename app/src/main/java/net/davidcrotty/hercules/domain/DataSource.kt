package net.davidcrotty.hercules.domain

/**
 * Created by David Crotty on 17/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class DataSource {
    fun titleForDay(index : Int) : String {
        when(index) {
            0 -> {
                return "Yesterday"
            }
            1 -> {
                return "Today"
            }
            2 -> {
                return "Tomorrow"
            }
            else -> {
                return "Today"
            }
        }
    }
}