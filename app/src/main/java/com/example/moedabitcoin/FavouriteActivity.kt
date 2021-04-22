package com.example.moedabitcoin

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.DomainCoinModel
import com.example.favourites.CoinFavouriteViewModel
import com.example.favourites.FavouriteAdapter
import com.example.moedabitcoin.viewmodelfactory.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class FavouriteActivity : AppCompatActivity() {

    private lateinit var coinFavouriteViewModel: CoinFavouriteViewModel
    private lateinit var coinFavouriteAdapter: FavouriteAdapter
    private lateinit var coinFavouriteRecyclerView: RecyclerView
    private lateinit var coinFavouriteLayoutManager: GridLayoutManager
    private val viewModelFactory: ViewModelFactory by lazy { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourites)

        val currentProgress = 100
        ObjectAnimator.ofInt(progressbar, "progress", currentProgress)
            .setDuration(4000)
            .start()

        coinFavouriteRecyclerView = findViewById(R.id.rv_favourites)
        coinFavouriteLayoutManager = GridLayoutManager(
            applicationContext,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        coinFavouriteRecyclerView.layoutManager = coinFavouriteLayoutManager
        coinFavouriteRecyclerView.setHasFixedSize(true)
        coinFavouriteAdapter = FavouriteAdapter { coin -> showCoinDetails(coin) }
        coinFavouriteRecyclerView.adapter = coinFavouriteAdapter

        coinFavouriteViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(CoinFavouriteViewModel::class.java)
        coinFavouriteViewModel.mutableListOnSuccess.observe(this, Observer {
            coinFavouriteAdapter.setData(it)
            progressbar.visibility = View.GONE
        })
        coinFavouriteViewModel.listFavourite()

        val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_favourites -> {
                    Toast.makeText(this, "Adicionadas", Toast.LENGTH_SHORT).show()
                }
                R.id.ic_coins -> {
                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
        item_nav_bar.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun showCoinDetails(domainCoinModel: DomainCoinModel) {
        val intent = CoinDetailsActivity.getLaunchIntent(this, domainCoinModel)
        startActivity(intent)
    }
}



