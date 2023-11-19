package com.example.hotelapp.fragment.homefragment.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.HomeModel
import com.example.hotelapp.databinding.ItemHomeBinding

class HomeAdapter:ListAdapter<HomeModel, HomeAdapter.ViewHolder>(HomeDiff()) {


    inner class ViewHolder(private val binding:ItemHomeBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: HomeModel) {
            binding.tvRatingName.text = model.rating_name
            binding.tvRating.text = model.rating.toString()
            binding.tvName.text = model.name
            binding.tvAdress.text = model.address
            binding.tvPrice.text = "ОТ :${model.minimal_price} Р"
            binding.tvPriceForIt.text = model.price_for_it
            binding.tvDesc.text = model.about_the_hotel.description

            // Очищаем текстовое поле tvWifi перед установкой новых данных
            binding.tvWifi.text = ""
            binding.tvLine.text = ""
            binding.tvBeach.text = ""
            binding.tvAeroport.text = ""
            // Перебираем особенности и устанавливаем их в соответствующие текстовые поля
            model.about_the_hotel.peculiarities.forEach { peculiaritiesItem ->
                when {
                    peculiaritiesItem.contains("Wifi") -> binding.tvBeach.text = peculiaritiesItem
                    peculiaritiesItem.contains("пляжа") -> binding.tvWifi.text = peculiaritiesItem
                    peculiaritiesItem.contains("фитнес-клуб") -> binding.tvLine.text = peculiaritiesItem
                    peculiaritiesItem.contains("аэропорта") -> binding.tvAeroport.text = peculiaritiesItem

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HomeDiff:DiffUtil.ItemCallback<HomeModel>() {
    override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
        return oldItem == newItem
    }

}
