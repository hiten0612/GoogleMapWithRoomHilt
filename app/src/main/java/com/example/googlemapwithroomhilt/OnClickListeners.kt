package com.example.googlemapwithroomhilt

interface OnClickListeners {

    fun onDeleteClick(position : Int, locationEntity: LocationEntity)
    fun onEditClick(position: Int, locationEntity: LocationEntity)
}