package com.example.moedabitcoin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.domain.model.DomainCoinModel
import com.example.moedabitcoin.viewmodelfactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_details.*

    lateinit var iconPathExtra: String
    private lateinit var coinFavouriteViewModel: com.example.favourites.CoinFavouriteViewModel
    private val viewModelFactory: ViewModelFactory by lazy { ViewModelFactory() }

    class CoinDetailsActivity : AppCompatActivity() {

        companion object {
            fun getLaunchIntent(context: Context, domainCoinModel: DomainCoinModel) =
                Intent(context, CoinDetailsActivity::class.java).apply {
                    putExtra("domain_coin_model", domainCoinModel )
                }
        }

        private lateinit var assetId: TextView
        private lateinit var icon: ImageView
        private lateinit var usdValue: TextView
        private lateinit var addButton: Button
        private lateinit var lastHourTransaction: TextView
        private lateinit var lastDayTransaction: TextView
        private lateinit var lastMonthTransaction: TextView
        private lateinit var star: ImageView

        private var domainCoinModel: DomainCoinModel? = null
        var isFavourite: Boolean? = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_details)

            domainCoinModel = intent.getParcelableExtra("domain_coin_model")

            coinFavouriteViewModel =
                ViewModelProviders.of(
                    this,
                    viewModelFactory
                ).get(com.example.favourites.CoinFavouriteViewModel::class.java)

            setupToolbar(tb_toolbar, "Voltar", true)

            assetId = findViewById(R.id.tv_asset_id)
            icon = findViewById(R.id.iv_icon)
            usdValue = findViewById(R.id.tv_price_usd)
            addButton = findViewById(R.id.btn_add_rmv)
            lastHourTransaction = findViewById(R.id.tv_last_hour_transaction)
            lastDayTransaction = findViewById(R.id.tv_last_day_transaction)
            lastMonthTransaction = findViewById(R.id.tv_last_month_transaction)
            star = findViewById(R.id.coin_details_favourite_star)

            populateDetails()
            inputCheckFavourite()
            addButton.setOnClickListener {
                checkButton()
            }
        }

        fun setupToolbar(
            toolbar: androidx.appcompat.widget.Toolbar,
            title: String,
            navigationBack: Boolean
        ) {
            toolbar.title = title
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(navigationBack)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                android.R.id.home -> {
                    this.onBackPressed()
                    return true
                }
            }
            return super.onOptionsItemSelected(item)
        }

        private fun populateDetails() {
            var coinIcon = domainCoinModel?.valueIcon.toString()

            iconPathExtra = coinIcon.replace("-", "")

            Glide.with(this)
                .load("https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_16/$iconPathExtra.png")
                .transform(CenterCrop())
                .into(icon)

            assetId.text = domainCoinModel?.assetId

            if(domainCoinModel?.valueUsd != null) {
                usdValue.text = domainCoinModel?.valueUsd.toString()
            }

            if (domainCoinModel!!.isFavorited) {
                star.visibility = View.VISIBLE
            } else {
                star.visibility = View.GONE
            }

            lastHourTransaction.text = domainCoinModel?.valueLastHour.toString()
            lastDayTransaction.text = domainCoinModel?.valueLastDay.toString()
            lastMonthTransaction.text = domainCoinModel?.valueLastMonth.toString()
            isFavourite = domainCoinModel?.isFavorited
        }

        private fun insertFavouriteToDatabase() {
                val data = (assetId.text.toString())
                coinFavouriteViewModel.addFavourite(data)
                Toast.makeText(this, "Adicionada", Toast.LENGTH_SHORT).show()
                btn_add_rmv.text = "Remover"
                star.visibility = View.VISIBLE

        }

        private fun removeFavouriteFromDatabase() {
                val data = (assetId.text.toString())
                coinFavouriteViewModel.removeFavourite(data)
                Toast.makeText(this, "Removida", Toast.LENGTH_SHORT).show()
                btn_add_rmv.text = "Adicionar"
                star.visibility = View.GONE

        }

        private fun checkButton() = if (inputCheckFavourite()) {
             removeFavouriteFromDatabase()
                true
            } else {
             insertFavouriteToDatabase()
                false
            }

        private fun inputCheckFavourite() = if (domainCoinModel?.isFavorited == true) {
                btn_add_rmv.text = "Remover"
                true
            } else {
                btn_add_rmv.text = "Adicionar"
                false
            }
        }



