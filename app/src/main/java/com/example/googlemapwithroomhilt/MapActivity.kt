package com.example.googlemapwithroomhilt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.googlemapwithroomhilt.databinding.ActivityMapBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var mMap: GoogleMap
    private val apiKey = "AIzaSyBSNyp6GQnnKlrMr7hD2HGiyF365tFlK5U"
    var placesClient: PlacesClient? = null
    var address: String? = null
    var name: String? = null
    private val viewModel by viewModels<LocationViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }
        placesClient = Places.createClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val autocompleteFragment =
            supportFragmentManager.findFragmentById(R.id.place_autocomplete_fragment) as AutocompleteSupportFragment

        autocompleteFragment.setPlaceFields(
            listOf(
                Place.Field.NAME, Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG
            )
        )
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onError(p0: Status) {

            }

            override fun onPlaceSelected(place: Place) {
                address = place.address?.toString()

                name = place.name?.toString()
                val latLong = LatLng(place.latLng?.latitude!!, place.latLng?.longitude!!)

                mMap.clear()
                mMap.addMarker(
                    MarkerOptions().position(latLong).title(address)
                )
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLong, 12.0f))

                val locationEntity = LocationEntity(0, name.toString(), address.toString(), latLong.longitude, latLong.latitude)

                binding.constraint.visibility = View.VISIBLE

                binding.btnSave.setOnClickListener {
                    viewModel.insert(locationEntity)
                    finish()
                }

            }

        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
    }
}