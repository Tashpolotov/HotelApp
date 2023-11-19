package com.example.hotelapp.fragment.bookedfragment.bookedadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelapp.R
import com.example.hotelapp.databinding.ItemPaidBinding


class InfoAdapter(private val onDeleteClick: () -> Unit)
    : RecyclerView.Adapter<InfoAdapter.TouristViewHolder>() {

    val items: MutableList<String> = mutableListOf()


    class TouristViewHolder(itemView: View, onDeleteClick: () -> Unit) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemPaidBinding.bind(itemView)
        val etTouristName: EditText = binding.etName

        init {
            binding.cardView.setOnClickListener {
                onDeleteClick.invoke()
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_paid, parent, false)
        return TouristViewHolder(view, onDeleteClick)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        val touristNumberWord = getTouristNumberWord(position)
        val touristText = "$touristNumberWord турист"
        holder.binding.tvFirstTourist.text = touristText
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addTourist() {
        items.add("Tourist ${items.size + 1}")
        notifyDataSetChanged()
    }

    fun removeLastTourist() {
        if (items.isNotEmpty()) {
            items.removeAt(items.size - 1)
            notifyDataSetChanged()
        }
    }
    private fun getTouristNumberWord(position: Int): String {
        return when (position) {
            0 -> "Второй"
            1 -> "Третий"
            2 -> "Четвёртый"
            3 -> "Пятый"
            4 -> "Шестой"
            5 -> "Седьмой"
            // Добавьте больше вариантов при необходимости
            else -> "Турист ${position + 1}"
        }
    }
}