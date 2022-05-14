package yunho.app.kotlindictionary.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import yunho.app.kotlindictionary.Entity.Restaurant
import yunho.app.kotlindictionary.R
import yunho.app.kotlindictionary.databinding.ItemRestaurantBinding

class RestaurantAdapter(private val clickListener: (Restaurant) -> Unit):  RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {
    var restaurants = mutableListOf<Restaurant>()
    inner class ViewHolder(private val binding: ItemRestaurantBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Restaurant){
            Glide.with(binding.image)
                .load(item.image)
                .into(binding.image)

            binding.root.setOnClickListener {
                clickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant,parent,false)
        val binding = ItemRestaurantBinding.bind(view)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(restaurants[position])
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }
}