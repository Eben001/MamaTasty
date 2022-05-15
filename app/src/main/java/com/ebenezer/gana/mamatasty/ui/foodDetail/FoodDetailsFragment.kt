package com.ebenezer.gana.mamatasty.ui.foodDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.ebenezer.gana.mamatasty.R
import com.ebenezer.gana.mamatasty.data.network.Food
import com.ebenezer.gana.mamatasty.databinding.FragmentFoodDetailsBinding

class FoodDetailsFragment : Fragment() {


    private var _binding: FragmentFoodDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FoodDetailViewModel by viewModels()

    private val navigationArgs: FoodDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFoodDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id

        viewModel.getFood(id).observe(viewLifecycleOwner) { food ->
            setData(food)
        }
    }

    private fun setData(food: Food) {
        binding.apply {
            foodImage.load(food.imageUrl) {
                placeholder(R.drawable.loading_animation)
                error(R.drawable.ic_broken_image)
                crossfade(true)
            }


            foodTitle.text = food.title
            foodDescription.text = food.description


        }

    }


}