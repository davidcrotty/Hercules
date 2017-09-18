package net.davidcrotty.hercules.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import net.davidcrotty.hercules.R
import net.davidcrotty.hercules.model.Plan

/**
 * Created by David Crotty on 17/09/2017.
 *
 * Copyright © 2017 David Crotty - All Rights Reserved
 */
class PlanCardAdapter(private val list: ArrayList<Plan>,
                      private val context: Context,
                      private val listener: View.OnClickListener) : RecyclerView.Adapter<PlanCardAdapter.PlanHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlanHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PlanHolder(layoutInflater.inflate(R.layout.plan_view, parent, false))
    }

    override fun onBindViewHolder(holder: PlanHolder, position: Int) {
        val plan = list[position]
        holder.personAdded.text = plan.owner
        holder.trainingType.text = plan.trainingType
        for(set in plan.setList) {
            val view = LayoutInflater.from(context).inflate(R.layout.rep_view, holder.repList, false)
            holder.repList.addView(view)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class PlanHolder : RecyclerView.ViewHolder {

        val personAdded: TextView
        val trainingType: TextView
        val repList: LinearLayout

        constructor(view: View) : super(view) {
            personAdded = view.findViewById(R.id.person_added)
            trainingType = view.findViewById(R.id.training_type)
            repList = view.findViewById(R.id.rep_list)
            view.setOnClickListener(listener)
        }
    }
}