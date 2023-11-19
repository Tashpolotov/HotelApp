package com.example.hotelapp.fragment.numberfragment.roomadapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.model.RoomsResponse
import com.example.hotelapp.fragment.homefragment.homeadapter.ImageAdapter
import com.example.hotelapp.databinding.ItemRomBinding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator


class Room1Adapter(private val listener: () -> Unit):ListAdapter<RoomsResponse, Room1Adapter.RoomViewHolder>(
    RoomDiff()
) {


    inner class RoomViewHolder(private val binding: ItemRomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewPager: ViewPager2 = binding.viewPager
        private val dots: DotsIndicator = binding.indic


        private val imageAdapter = ImageAdapter()

        init {
            viewPager.adapter = imageAdapter
            dots.setViewPager2(viewPager)
        }



        fun bind(model: RoomsResponse) {
            val allImageUrls: List<String> = model.image_urls.orEmpty()
            imageAdapter.data = allImageUrls
            imageAdapter.notifyDataSetChanged()
            Log.d("ImageUrls", "Количество URL-адресов: ${allImageUrls.size}")

            binding.tvName.text = model.name
            binding.tvPrice.text = model.price.toString()
            binding.tvPriceForIt.text = model.price_per

            binding.tvWifi.text = ""
            binding.tvLine.text = ""
            binding.tvAeroport.text = ""

                val wifiIndex = 0
                val lineIndex = 1
                val aeroportIndex = 2


                binding.tvWifi.text = model.peculiarities.getOrNull(wifiIndex) ?: ""
                binding.tvLine.text = model.peculiarities.getOrNull(lineIndex) ?: ""
                binding.tvAeroport.text = model.peculiarities.getOrNull(aeroportIndex) ?: ""


                binding.card1.visibility =
                    if (binding.tvLine.text.isNotEmpty()) View.VISIBLE else View.GONE
                binding.card2.visibility =
                    if (binding.tvWifi.text.isNotEmpty()) View.VISIBLE else View.GONE
                binding.card3.visibility =
                    if (binding.tvAeroport.text.isNotEmpty()) View.VISIBLE else View.GONE


            binding.btnGo.setOnClickListener {
                listener.invoke()
            }

            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        return RoomViewHolder(ItemRomBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class RoomDiff: DiffUtil.ItemCallback<RoomsResponse>() {
    override fun areItemsTheSame(oldItem: RoomsResponse, newItem: RoomsResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: RoomsResponse, newItem: RoomsResponse): Boolean {
        return oldItem == newItem
    }

}
