package com.example.moedabitcoin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.domain.model.DomainCoinModel

lateinit var iconPath: String
class CoinAdapter(
        private val onCoinClick: (coinListDetails: DomainCoinModel) -> Unit
) : RecyclerView.Adapter<CoinAdapter.CoinViewHolder>() {

    private var coinList = emptyList<DomainCoinModel>()

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): CoinViewHolder {
        return CoinViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.activity_coin_item,
                        parent,
                        false))
    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    fun setData(coinListModel: List<DomainCoinModel>) {
        this.coinList = coinListModel
        notifyDataSetChanged()
    }

    inner class CoinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val icon: ImageView = itemView.findViewById(R.id.item_coin_icon)
        private val assetId: TextView = itemView.findViewById(R.id.item_coin_asset_id)
        private val name: TextView = itemView.findViewById(R.id.item_coin_name)
        private val valueUsd: TextView = itemView.findViewById(R.id.item_coin_value_usd)
        private val star: ImageView = itemView.findViewById(R.id.coin_item_favourite_star)

        fun bind(coins: DomainCoinModel) {
            name.text = "${coins.name}"
            assetId.text = "${coins.assetId}"

            if (coins.valueUsd != null) {
                valueUsd.text = "$" + "${coins.valueUsd}"
            } else {
                valueUsd.text = "0,00"
            }

           if (coins.isFavorited) {
               star.visibility = View.VISIBLE
           } else {
               star.visibility = View.GONE
           }

            iconPath = if (coins.valueIcon != null) {
                coins.valueIcon!!.replace("-", "")
            } else {
                "4caf2b16a0174e26a3482cea69c34cba"
            }
            Glide.with(itemView)
                    .load("https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_16/$iconPath.png")
                    .transform(CenterCrop())
                    .into(icon)
            itemView.setOnClickListener {
                onCoinClick.invoke(coins)
            }
        }
    }

}