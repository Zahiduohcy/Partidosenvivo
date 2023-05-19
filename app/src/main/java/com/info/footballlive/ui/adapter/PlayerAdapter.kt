package com.info.footballlive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.info.footballlive.R
import com.info.footballlive.rest.model.Country
import com.info.footballlive.rest.model.Player
import kotlinx.android.synthetic.main.item_country.view.*
import kotlinx.android.synthetic.main.item_player.view.*
import kotlinx.android.synthetic.main.item_player.view.imgPhoto
import kotlinx.android.synthetic.main.item_player.view.txtName

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

            itemView.txtName.text = player.name
            Glide.with(itemView)
                .load(player.photo)
                .apply(
                    RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
                .into(itemView.imgPhoto)
            itemView.setOnClickListener { listener(player) }
        }
    }
}