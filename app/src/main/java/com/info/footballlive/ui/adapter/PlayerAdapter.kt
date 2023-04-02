package com.info.footballlive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.info.footballlive.R
import com.info.footballlive.rest.model.Country
import com.info.footballlive.rest.model.Player
import kotlinx.android.synthetic.main.item_player.view.*

class PlayerAdapter(private val mPlayerList: List<Player>,
                    private val mListener: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))
    }

    override fun getItemCount(): Int = mPlayerList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mPlayerList[position], mListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: Player, listener: (Player) -> Unit) {

            itemView.txtName.text = player.player_name

            itemView.setOnClickListener { listener(player) }
        }
    }
}