package com.spacex.app.util.imageloader

import android.widget.ImageView

interface ImageLoader {

  fun load(imageResource: String, target: ImageView)

  fun loadPlaceHolder(imageResource: String, target: ImageView, placeHolderResource: Int)
}