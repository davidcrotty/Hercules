package net.davidcrotty.hercules.domain

import net.davidcrotty.hercules.model.Plan
import net.davidcrotty.hercules.model.PlanSet

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

    fun planForDay(index: Int) : ArrayList<Plan> {
        val planlist = ArrayList<Plan>()

        when(index) {
            0 -> {
                val planSetList = ArrayList<PlanSet>()
                planSetList.apply {
                    add(PlanSet("Bicep Curls", 10, 10, 30))
                    add(PlanSet("Bicep Curls", 10, 20, 40))
                    add(PlanSet("Deadlifts", 10, 30, 40))
                    add(PlanSet("Bicep Curls", 5, 10, 30))
                }

                planlist.apply {
                    add(Plan("Tim Added",
                            "Strength Training",
                            planSetList))
                }

                return planlist
            }
            1 -> {
                val planSetList = ArrayList<PlanSet>()
                planSetList.apply {
                    add(PlanSet("Bicep Curls", 10, 10, 30))
                    add(PlanSet("Shoulder Press", 10, 20, 40))
                    add(PlanSet("Deadlifts", 10, 30, 40))
                    add(PlanSet("Bicep Curls", 5, 10, 30))
                }

                planlist.apply {
                    add(Plan("Peter Added",
                            "Strength Training",
                            planSetList))
                    add(Plan("John Added",
                            "Strength Training",
                            planSetList))
                }

                return planlist
            }
            2 -> {
                val planSetList = ArrayList<PlanSet>()
                planSetList.apply {
                    add(PlanSet("Bicep Curls", 10, 10, 30))
                    add(PlanSet("Shoulder Press", 10, 20, 40))
                    add(PlanSet("Deadlifts", 10, 30, 40))
                    add(PlanSet("Bicep Curls", 5, 10, 30))
                }

                planlist.apply {
                    add(Plan("Bob Added",
                            "Strength Training",
                            planSetList))
                }

                return planlist
            }
        }

        return planlist
    }
}