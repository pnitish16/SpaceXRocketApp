package com.spacex.app.util.imageloader

import android.widget.ImageView
import com.squareup.picasso.Picasso


class ImageLoaderImpl(private val picasso: Picasso) : ImageLoader {

  override fun load(imageResource: String, target: ImageView) {
    picasso.load(imageResource).into(target)
  }

  override fun loadPlaceHolder(imageResource: String, target: ImageView, placeHolderResource: Int) {
    picasso.load(imageResource).placeholder(placeHolderResource).into(target)
  }
}