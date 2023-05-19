package com.envivo.futbolenvivoyendirectopartidos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.rest.model.League
import com.envivo.futbolenvivoyendirectopartidos.utils.getStringDate
import kotlinx.android.synthetic.main.item_league.view.*

class LeagueAdapter(private val mLeagueList: List<League>,
                    private val mListener: (League) -> Unit) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false))
    }

    override fun getItemCount(): Int = mLeagueList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mLeagueList[position], mListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(league: League, listener: (League) -> Unit) {

            itemView.txtName.text = league.league?.name

            val seasonStart: String = league.seasons?.get(league.seasons!!.lastIndex)?.start ?.let { getStringDate(it) } ?: "-"
            itemView.txtSeasonStart.text = seasonStart

            val seasonEnd: String = league.seasons?.get(league.seasons!!.lastIndex)?.end?.let { getStringDate(it) } ?: "-"
            itemView.txtSeasonEnd.text = seasonEnd

            Glide.with(itemView)
                    .load(league.league?.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(itemView.imgLogo)

            itemView.setOnClickListener { listener(league) }
        }
    }
}