package com.example.dodohomework

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dodohomework.databinding.ActivityPizzaDetailsBinding

class PizzaDetails : AppCompatActivity() {

    private lateinit var binding:ActivityPizzaDetailsBinding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPizzaDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        Glide.with(this).load(intent?.extras?.getString("pizza_image_url"))
            .into(binding.imageViewPizzaImage)

        binding.textViewPizzaName.text = intent?.extras?.getString("pizza_name")
        binding.textViewPizzaDescription.text = intent?.extras?.getString("pizza_description")
        binding.purchaseButton.text = intent?.extras?.getString("pizza_price")


    }
}