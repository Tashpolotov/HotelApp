package com.example.hotelapp.fragment.bookedfragment.bookedadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BookedModel
import com.example.hotelapp.databinding.ItemBookedBinding

class BookedAdapter:ListAdapter<BookedModel, BookedAdapter.BookedViewHolder>(BookDiff()) {


    inner class BookedViewHolder(private val binding:ItemBookedBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookedModel) {
            binding.tvName.text = model.hotel_name
            binding.tvAdress.text = model.hotel_adress
            binding.tvNameHotel.text = model.hotel_name
            binding.tvArrivalCity.text = model.arrival_country
            binding.tvRating.text = model.horating.toString()
            binding.tvRatingName.text = model.rating_name
            binding.tvDate.text = "${model.tour_date_start} - ${model.tour_date_stop}"
            binding.tvMuchNight.text = model.number_of_nights.toString()
            binding.tvFood.text = model.nutrition
            binding.tvRoom.text = model.room




        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedViewHolder {
        return BookedViewHolder(ItemBookedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookDiff:DiffUtil.ItemCallback<BookedModel>() {
    override fun areItemsTheSame(oldItem: BookedModel, newItem: BookedModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BookedModel, newItem: BookedModel): Boolean {
        return oldItem == newItem
    }

}
