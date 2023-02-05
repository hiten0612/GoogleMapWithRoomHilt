package com.example.googlemapwithroomhilt

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.googlemapwithroomhilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListeners {
    private lateinit var binding: ActivityMainBinding
    private var mAdapter: LocationListAdapter? = null
    //    private val viewModel  : LocationViewModel by viewModels()
    private val viewModel by viewModels<LocationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.lifecycleOwner = this


        mAdapter = LocationListAdapter(this)
        binding.rvLocation.adapter = mAdapter

        viewModel.getAllLocation.observe(this){
            mAdapter?.setList(it as ArrayList<LocationEntity>)
        }


        binding.btnAdd.setOnClickListener {
            startActivity(Intent(this@MainActivity, MapActivity::class.java))

        }

    }

    override fun onDeleteClick(position: Int, locationEntity: LocationEntity) {

    }

    override fun onEditClick(position: Int, locationEntity: LocationEntity) {

        
    }
}