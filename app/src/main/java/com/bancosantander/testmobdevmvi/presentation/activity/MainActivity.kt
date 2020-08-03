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
import com.bancosantander.testmobdevmvi.presentation.fragment.BreedsImgFragment
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsIntent
import com.bancosantander.testmobdevmvi.presentation.mvibase.breeds.BreedsState
import com.bancosantander.testmobdevmvi.presentation.viewmodel.BreedsViewModel
import com.bancosantander.testmobdevmvi.util.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}


