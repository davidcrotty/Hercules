package net.davidcrotty.hercules.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_dashboard.*
import net.davidcrotty.hercules.ExerciseActivity
import net.davidcrotty.hercules.adapter.PlanCardAdapter
import net.davidcrotty.hercules.domain.DataSource
import net.davidcrotty.hercules.R
import net.davidcrotty.hercules.model.Set
import net.davidcrotty.progressview.SetState

/**
 * Created by David Crotty on 17/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class PlanFragment : Fragment(), View.OnClickListener {

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
            val adapter = PlanCardAdapter(dataSource.planForDay(index), activity, this)
            plan_list.layoutManager = LinearLayoutManager(activity)
            plan_list.adapter = adapter
        }
    }

    override fun onClick(p0: View?) {
        val intent = Intent(activity, ExerciseActivity::class.java)
        val setList = generateSet()
        intent.putParcelableArrayListExtra(ExerciseActivity.SET_KEY, setList)
        startActivity(intent)
    }

    private fun generateSet() : ArrayList<Set> {
        val first = Set("Biceps curl", 10, 30, SetState.PENDING)
        val second = Set("Shoulder press", 8, 30, SetState.PENDING)
        val third = Set("Dead lifts", 7, 30, SetState.PENDING)
        val fourth = Set("Press ups", 6, 30, SetState.PENDING)

        return ArrayList<Set>().apply {
            add(first)
            add(second)
            add(third)
            add(fourth)
        }
    }
}