package net.davidcrotty.hercules.presenter

import net.davidcrotty.hercules.domain.DataSource
import net.davidcrotty.hercules.view.DashboardView

/**
 * Created by David Crotty on 17/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class DashboardPresenter(val dashboardView: DashboardView, val dataSource: DataSource) {
    fun showTitleFor(index: Int) {
        dashboardView.setTitle(dataSource.titleForDay(index))
    }
}