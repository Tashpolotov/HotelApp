package com.example.hotelapp.fragment.bookedfragment.bookedadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.BookedModel
import com.example.hotelapp.databinding.ItemBookedBinding
import com.example.hotelapp.databinding.ItemBookedSecondBinding

class BookedSecondAdapter: ListAdapter<BookedModel, BookedSecondAdapter.BookedViewHolder>(BookedDiff()) {


    inner class BookedViewHolder(private val binding: ItemBookedSecondBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: BookedModel) {
            binding.tvFuelCharge.text = model.fuel_charge.toString()
            binding.tvServiceCharge.text = model.service_charge.toString()
            binding.tvTourPrice.text = model.tour_price.toString()
            val total = model.fuel_charge + model.service_charge + model.tour_price
            binding.tvTotalPrice.text = total.toString()


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedViewHolder {
        return BookedViewHolder(ItemBookedSecondBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BookedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BookedDiff: DiffUtil.ItemCallback<BookedModel>() {
    override fun areItemsTheSame(oldItem: BookedModel, newItem: BookedModel): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: BookedModel, newItem: BookedModel): Boolean {
        return oldItem == newItem
    }

}
