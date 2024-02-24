package com.example.dodohomework

import java.io.Serializable

data class PizzaItem(
    val id: Int,
    val name: String,
    val description: String,
    var price: Int,
    val imageUrl: String,

):Serializable


