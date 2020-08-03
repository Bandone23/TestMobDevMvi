package com.bancosantander.testmobdevmvi.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancosantander.testmobdevmvi.R
import com.bancosantander.testmobdevmvi.data.remote.net.BreedsHelperImpl
import com.bancosantander.testmobdevmvi.data.remote.net.RetrofitBuilder
import com.bancosantander.testmobdevmvi.presentation.adapter.BreedsAdapter
import com.bancosantander.testmobdevmvi.presentation.adapter.BreedsImgAdapter
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsState
import com.bancosantander.testmobdevmvi.presentation.mvibase.breedsImg.BreedsImgIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.breedsImg.BreedsImgState
import com.bancosantander.testmobdevmvi.presentation.viewmodel.BreedsImgViewModel
import com.bancosantander.testmobdevmvi.presentation.viewmodel.BreedsViewModel
import com.bancosantander.testmobdevmvi.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_breeds.*
import kotlinx.android.synthetic.main.fragment_breeds_img.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BreedsImgFragment : Fragment() {
    private lateinit var breedsImgViewModel: BreedsImgViewModel
    private val breedsImg: ArrayList<String> = ArrayList()
    private lateinit var mBreedsImgAdapter: BreedsImgAdapter

    private var nameBreeds: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nameBreeds = BreedsImgFragmentArgs.fromBundle(requireArguments()).name
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_breeds_img, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        observeViewModel()
        getImgBreeds()

    }

    private fun getImgBreeds() {
        lifecycleScope.launch {
            breedsImgViewModel.breedImgIntent.send(BreedsImgIntent.FetchBreedsImg)
        }
    }

    private fun setupViewModel() {
        breedsImgViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                BreedsHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(BreedsImgViewModel::class.java)
    }


    private fun observeViewModel() {
        lifecycleScope.launch {
            breedsImgViewModel.state.collect {
                when (it) {
                    is BreedsImgState.Idb -> {

                    }
                    is BreedsImgState.Loading -> {
                        progress_circular_img.visibility = View.VISIBLE
                    }
                    is BreedsImgState.BreedImg -> {
                        progress_circular_img.visibility = View.GONE
                        breedsImg.clear()
                        for (item in it.breedListImg.indices) {
                            breedsImg.add(it.breedListImg[item])
                        }
                        renderList(breedsImg)


                    }
                    is BreedsImgState.Error -> {
                        progress_circular_img.visibility = View.GONE
                        Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun renderList(users: ArrayList<String>) {
        rv_animal_img_list.visibility = View.VISIBLE
        mBreedsImgAdapter = BreedsImgAdapter(users)
        rv_animal_img_list.layoutManager = LinearLayoutManager(context)
        rv_animal_img_list.adapter = mBreedsImgAdapter

    }



}