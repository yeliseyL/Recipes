package com.eliseylobanov.recipes.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.recipes.R
import com.eliseylobanov.recipes.entities.Meal
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Picasso.with(imgView.context)
                .load(imgUri)
                .placeholder(R.drawable.ic_food_placeholder)
                .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Meal>?) {
    val adapter = recyclerView.adapter as MealsAdapter
    adapter.submitList(data)
}

@BindingAdapter("favoriteImage")
fun bindDetailsFavoriteImage(imageView: ImageView, isFavorite: Boolean) {
    if (isFavorite) {
        imageView.setImageResource(R.drawable.ic_favorite_solid)
    } else {
        imageView.setImageResource(R.drawable.ic_favorite)
    }
}