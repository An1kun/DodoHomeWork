package com.example.dodohomework

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dodohomework.databinding.ItemPizzaBinding

class PizzaAdapter(private val pizzasList: List<PizzaItem>) :
    RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>(), Filterable {

    private var filteredPizzas: List<PizzaItem> = pizzasList


    class PizzaViewHolder(val binding: ItemPizzaBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PizzaViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPizzaBinding.inflate(inflater, parent, false)
        return PizzaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return filteredPizzas.size
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) {
        val pizza = filteredPizzas[position]
        val context = holder.itemView.context

        with(holder.binding) {
            textViewPizzaName.text = pizza.name
            textViewPizzaDescription.text = pizza.description
            textViewPizzaPrice.text = "${pizza.price} \$"
            Glide.with(context).load(pizza.imageUrl).into(imageViewPizzaImage)
            root.setOnClickListener {
                val intent = Intent(context, PizzaDetails::class.java).apply {
                    putExtra("pizza_name", pizza.name)
                    putExtra("pizza_description", pizza.description)
                    putExtra("pizza_price", "${pizza.price} \$")
                    putExtra("pizza_image_url", pizza.imageUrl)
                }
                context.startActivity(intent)
            }
        }


    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint?.toString()?.lowercase()
                val filteredList = if (queryString.isNullOrEmpty()) {
                    pizzasList
                } else {
                    pizzasList.filter { it.name.lowercase().contains(queryString) }
                }
                return FilterResults().apply { values = filteredList }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredPizzas = results?.values as List<PizzaItem>? ?: listOf()
            }
        }
    }
}
