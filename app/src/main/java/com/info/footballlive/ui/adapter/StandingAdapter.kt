package com.info.footballlive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.RequestOptions
import com.info.footballlive.R
import com.info.footballlive.rest.model.StandingModel
import com.info.footballlive.utils.GlideApp
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter(private val mStandingList: List<StandingModel.Standings>,
                      private val mListener: (StandingModel.Standings) -> Unit) : RecyclerView.Adapter<StandingAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_standing, parent, false))
    }

    override fun getItemCount(): Int = mStandingList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mStandingList[position], mListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(standing: StandingModel.Standings, listener: (StandingModel.Standings) -> Unit) {

            itemView.txtRank.text = standing.rank.toString()
            itemView.txtTeamName.text = standing.league?.name
            itemView.txtPlayed.text = standing.all?.played?.toString()
            itemView.txtWin.text = standing.all?.win.toString()
            itemView.txtDraw.text = standing.all?.draw.toString()
            itemView.txtLoss.text = standing.all?.lose.toString()
            itemView.txtGoals.text = standing.all?.goals?._for.toString()

            GlideApp.with(itemView)
                    .load(standing.league?.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(itemView.imgLogo)

            itemView.setOnClickListener { listener(standing) }
        }
    }
}