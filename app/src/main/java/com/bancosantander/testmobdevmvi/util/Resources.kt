package com.bancosantander.testmobdevmvi.util

import android.widget.ImageView
import com.bancosantander.testmobdevmvi.R
import com.squareup.picasso.Picasso

fun getImgPicasso(picture: String, view: ImageView?) {
    if (picture.isNotEmpty()) {
        Picasso.get()
            .load(picture)
            .error(R.drawable.ic_default_place_holder)
            .into(view)
    } else {
        Picasso.get()
            .load(R.drawable.ic_default_place_holder)
            .error(R.drawable.ic_default_place_holder)
            .into(view)
    }
}