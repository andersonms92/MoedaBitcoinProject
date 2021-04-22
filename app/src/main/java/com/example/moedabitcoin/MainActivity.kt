package com.example.moedabitcoin

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.commons.viewModel.CoinListViewModel
import com.example.domain.model.DomainCoinModel
import com.example.moedabitcoin.viewmodelfactory.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var coinListViewModel: CoinListViewModel
    private lateinit var coinViewRecyclerView: RecyclerView
    private lateinit var coinAdapter: CoinAdapter
    private lateinit var coinLayoutManager: LinearLayoutManager
    private val viewModelFactory: ViewModelFactory by lazy { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressbar.max = 100

        val currentProgress = 100
        ObjectAnimator.ofInt(progressbar, "progress", currentProgress)
            .setDuration(4000)
            .start()

        coinViewRecyclerView = findViewById(R.id.rv_coin_currency)
        coinLayoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        coinViewRecyclerView.layoutManager = coinLayoutManager
        coinViewRecyclerView.setHasFixedSize(true)
        coinAdapter = CoinAdapter() { coin -> showCoinDetails(coin) }
        coinViewRecyclerView.adapter = coinAdapter

        coinListViewModel = ViewModelProviders.of(this, viewModelFactory).get(CoinListViewModel::class.java)
        coinListViewModel.mutableListOnSuccess.observe(this, Observer {
            coinAdapter.setData(it)
            resultSearchDisplay(it)
            progressbar.visibility = View.GONE
        })
        coinListViewModel.listAll()

        val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ic_favourites -> {
                    val intent = Intent(this, FavouriteActivity::class.java)
                    startActivity(intent)
                }
                R.id.ic_coins -> {
                    Toast.makeText(this, "Moedas", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }
        item_nav_bar.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private fun searchAdapter(domainCoinModel: List<DomainCoinModel>) {
        coinViewRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        coinViewRecyclerView.adapter = domainCoinModel.let {
            CoinAdapter { coin -> domainCoinModel }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun showCoinDetails(domainCoinModel: DomainCoinModel) {
        val intent = CoinDetailsActivity.getLaunchIntent(this, domainCoinModel)
        startActivity(intent)
    }


  private fun resultSearch(search: String, list: List<DomainCoinModel>){
      val searchResult : MutableList<DomainCoinModel> = arrayListOf()
      for(element in list){
          if(element.name != null ){
              if(element.name!!.contains(search, ignoreCase = true)){
                  searchResult.add(element)
              }
          }
      }
      searchAdapter(searchResult)
  }

  private fun resultSearchDisplay(list: List<DomainCoinModel>) {
      searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
          override fun onQueryTextSubmit(query: String): Boolean {
              resultSearch(query, list)
              return false
          }

          override fun onQueryTextChange(newText: String): Boolean {
              resultSearch(newText, list)
              return false
          }
      })

  }
}