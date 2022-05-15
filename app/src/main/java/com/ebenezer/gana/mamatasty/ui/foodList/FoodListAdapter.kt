package com.ebenezer.gana.mamatasty.ui.foodList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ebenezer.gana.mamatasty.R
import com.ebenezer.gana.mamatasty.data.network.Food
import com.ebenezer.gana.mamatasty.databinding.ListItemFoodBinding

class FoodListAdapter(
    private val clickListener: (Food) -> Unit
) : ListAdapter<Food, FoodListAdapter.FoodViewHolder>(DiffCallback) {


    inner class FoodViewHolder(
        private var binding: ListItemFoodBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(foodItem: Food) {
            binding.apply {
                titleFood.text = foodItem.title
                titleDescription.text = foodItem.description

                foodImage.load(foodItem.imageUrl) {
                    placeholder(R.drawable.loading_animation)
                    error(R.drawable.ic_broken_image)
                }
            }


        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder(
            ListItemFoodBinding.inflate(
                LayoutInflater.from(parent.context)

            )
        )
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = getItem(position)
        holder.bind(foodItem)
        holder.itemView.setOnClickListener {
            clickListener(foodItem)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.imageUrl == newItem.imageUrl &&
                    oldItem.description == newItem.description
        }

    }


}