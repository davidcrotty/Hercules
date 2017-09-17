package net.davidcrotty.hercules.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_dashboard.*
import net.davidcrotty.hercules.adapter.PlanCardAdapter
import net.davidcrotty.hercules.domain.DataSource
import net.davidcrotty.hercules.R

/**
 * Created by David Crotty on 17/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class PlanFragment : Fragment() {

    companion object {
        val DAY_INDEX_KEY = "DAY_INDEX_KEY"

        fun newInstance(index: Int) : PlanFragment {
            val fragment = PlanFragment()
            val bundle = Bundle()
            bundle.putInt(DAY_INDEX_KEY, index)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataSource = DataSource()

        val index = arguments.getInt(DAY_INDEX_KEY, - 1)
        if(index >= 0) {
            val adapter = PlanCardAdapter(dataSource.planForDay(index))
            plan_list.layoutManager = LinearLayoutManager(activity)
            plan_list.adapter = adapter
        }
    }
}