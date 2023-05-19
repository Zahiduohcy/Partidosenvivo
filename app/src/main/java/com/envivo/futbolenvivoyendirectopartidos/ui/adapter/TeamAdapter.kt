package com.envivo.futbolenvivoyendirectopartidos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.envivo.futbolenvivoyendirectopartidos.R
import com.envivo.futbolenvivoyendirectopartidos.rest.model.Team
import kotlinx.android.synthetic.main.item_team.view.*

class TeamAdapter(private val mTeamList: List<Team>,
                  private val mListener: (Team) -> Unit) : RecyclerView.Adapter<TeamAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false))
    }

    override fun getItemCount(): Int = mTeamList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mTeamList[position], mListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(team: Team, listener: (Team) -> Unit) {

            itemView.txtName.text = team.name

            Glide.with(itemView)
                    .load(team.logo)
                    .apply(RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image))
                    .into(itemView.imgPhoto)

            itemView.setOnClickListener { listener(team) }
        }
    }
}