package com.bancosantander.testmobdevmvi.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancosantander.testmobdevmvi.R
import com.bancosantander.testmobdevmvi.data.remote.net.BreedsHelperImpl
import com.bancosantander.testmobdevmvi.data.remote.net.RetrofitBuilder
import com.bancosantander.testmobdevmvi.presentation.adapter.BreedsAdapter
import com.bancosantander.testmobdevmvi.presentation.mvibase.BreedsIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.BreedsState
import com.bancosantander.testmobdevmvi.presentation.viewmodel.BreedsViewModel
import com.bancosantander.testmobdevmvi.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var breedsViewModel: BreedsViewModel
    private var adapter = BreedsAdapter(arrayListOf())

    private val breeds: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                breedsViewModel.breedIntent.send(BreedsIntent.FetchBreeds)
            }
        }
    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        breedsViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                BreedsHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(BreedsViewModel::class.java)
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            breedsViewModel.state.collect {
                when (it) {
                    is BreedsState.Idb -> {

                    }
                    is BreedsState.Loading -> {
                        buttonFetchUser.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }

                    is BreedsState.Breed -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.GONE
                        for (item in it.breedList.indices) {
                            breeds.add(it.breedList[item])
                        }
                        renderList(breeds)
                    }
                    is BreedsState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: List<String>) {
        recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()

    }
}


