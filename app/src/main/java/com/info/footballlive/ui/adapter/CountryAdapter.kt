package com.info.footballlive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.info.footballlive.R
import com.info.footballlive.rest.model.Country
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val mCountryList: List<Country>,
                     private val mListener: (Country) -> Unit) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false))
    }

    override fun getItemCount(): Int = mCountryList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCountryList[position], mListener)
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: Country, listener: (Country) -> Unit) {

            itemView.txtName.text = country.name

            if (country.flag == "World") {
                Glide.with(itemView)
                        .load(R.drawable.ic_world)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image))
                        .into(itemView.imgPhoto)
            } else {
                Glide.with(itemView)
                        .load(country.flag)
                        .apply(RequestOptions()
                                .placeholder(R.drawable.loading_animation)
                                .error(R.drawable.ic_broken_image))
                        .into(itemView.imgPhoto)
            }


            itemView.setOnClickListener { listener(country) }
        }
    }
}