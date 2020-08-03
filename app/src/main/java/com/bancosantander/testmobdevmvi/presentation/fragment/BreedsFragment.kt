package com.bancosantander.testmobdevmvi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancosantander.testmobdevmvi.R
import com.bancosantander.testmobdevmvi.data.remote.net.BreedsHelperImpl
import com.bancosantander.testmobdevmvi.data.remote.net.RetrofitBuilder
import com.bancosantander.testmobdevmvi.presentation.adapter.BreedsAdapter
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsState
import com.bancosantander.testmobdevmvi.presentation.viewmodel.BreedsViewModel
import com.bancosantander.testmobdevmvi.util.AppPreferences
import com.bancosantander.testmobdevmvi.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_breeds.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BreedsFragment : Fragment() {
    private lateinit var breedsViewModel: BreedsViewModel
    private lateinit var mBreedsAdapter: BreedsAdapter
    private val breeds: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                        breeds.clear()
                        for (item in it.breedList.indices) {
                            breeds.add(it.breedList[item])
                        }
                        renderList(breeds)


                    }
                    is BreedsState.Error -> {
                        progressBar.visibility = View.GONE
                        buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: ArrayList<String>) {
        recyclerView.visibility = View.VISIBLE
        mBreedsAdapter = BreedsAdapter(users) { animals, viewId: Int ->
            breedsClicked(
                animals,
                viewId
            )
        }
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = mBreedsAdapter

    }

    private fun breedsClicked(breeds: String, viewId: Int) {
        when (viewId) {
            R.id.container_Breeds -> {
                val action = BreedsFragmentDirections.nextBreeds(breeds)
                findNavController().navigate(action)
                AppPreferences.nameBreeds = breeds
            }
        }
    }

}