package com.eliseylobanov.recipes.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eliseylobanov.recipes.databinding.RandomRecyclerItemBinding
import com.eliseylobanov.recipes.entities.Meal

class MealsAdapter(val onClickListener: OnClickListener) :
        ListAdapter<Meal, MealsAdapter.AsteroidsViewHolder>(DiffCallback) {

    class AsteroidsViewHolder(private var binding: RandomRecyclerItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(meals: Meal) {
            binding.meals = meals
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.idMeal == newItem.idMeal
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AsteroidsViewHolder {
        return AsteroidsViewHolder(RandomRecyclerItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AsteroidsViewHolder, position: Int) {
        val meal = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(meal)
        }
        holder.bind(meal)
    }

    class OnClickListener(val clickListener: (meal: Meal) -> Unit) {
        fun onClick(meal: Meal) = clickListener(meal)
    }
}
