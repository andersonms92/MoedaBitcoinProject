package com.example.favourites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.DomainCoinModel
import kotlinx.android.synthetic.main.*


class FavouriteAdapter(private val onCoinClick: (favouriteListDetails: DomainCoinModel) -> Unit
) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {

    private var iconPath = ""
    private var favouriteList = emptyList<DomainCoinModel>()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): FavouriteViewHolder {
        return FavouriteViewHolder(
                LayoutInflater.from(parent.context).
                inflate(
                    R.layout.activity_favourites_item,
                        parent,
                        false))
    }

    override fun getItemCount(): Int {
        return favouriteList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bind(favouriteList[position])
    }

    fun setData(coinFavouriteModel: List<DomainCoinModel>){
        this.favouriteList = coinFavouriteModel
        notifyDataSetChanged()
    }

    inner class FavouriteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val coinImage: ImageView = itemView.findViewById(R.id.tv_favourite_image_coin)
        private val assetId: TextView = itemView.findViewById(R.id.tv_assetId)
        private val name: TextView = itemView.findViewById(R.id.tv_favourite_name)
        private val valueUSD: TextView = itemView.findViewById(R.id.tv_favourite_value_usd)


        fun bind(favourites: DomainCoinModel) {
            assetId.text = "${favourites.assetId}"
            name.text = "${favourites.assetId}"

            if(favourites.valueUsd != null) {
                valueUSD.text = "${favourites.valueUsd}"
            } else {
                valueUSD.text = "0.000,00"
            }


            iconPath = if (favourites.valueIcon != null) {
                favourites.valueIcon!!.replace("-", "")
            } else {
                "4caf2b16a0174e26a3482cea69c34cba"
            }
            Glide.with(itemView)
                    .load("https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_16/$iconPath.png")
                    .transform()
                    .into(coinImage)
            itemView.setOnClickListener{
                onCoinClick.invoke(favourites)
            }
        }
    }
}