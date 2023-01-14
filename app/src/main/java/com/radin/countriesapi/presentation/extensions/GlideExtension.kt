package com.radin.countriesapi.presentation.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

fun ImageView.loadRoundImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .into(this)
}