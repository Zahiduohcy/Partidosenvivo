package com.info.footballlive.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.footballlive.R
import com.info.footballlive.rest.model.Fixture
import com.info.footballlive.utils.getStringDate
import kotlinx.android.synthetic.main.item_fixture.view.*

class FixtureAdapter(private val mFixtureList: List<Fixture>,
                     private val mListener: (Fixture) -> Unit) : RecyclerView.Adapter<FixtureAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fixture, parent, false))
    }

    override fun getItemCount(): Int = mFixtureList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mFixtureList[position], mListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(fixture: Fixture, listener: (Fixture) -> Unit) {
            val dateEvent: String = fixture.fixture?.date?.let { getStringDate(it) } ?: "-"
            itemView.txtDateFixture.text = dateEvent

            itemView.txtHomeTeam.text = fixture.teams?.home?.name
            itemView.txtAwayTeam.text = fixture.teams?.away?.name
            itemView.txtHomeScore.text = if (fixture.goals?.home != null ) fixture.goals?.home?.toString() else "0"
            itemView.txtAwayScore.text = if (fixture.goals?.away != null ) fixture.goals?.away?.toString() else "0"

            itemView.setOnClickListener { listener(fixture) }
        }
    }
}