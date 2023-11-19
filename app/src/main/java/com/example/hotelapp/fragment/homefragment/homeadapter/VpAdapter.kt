package com.example.hotelapp.fragment.homefragment.homeadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.domain.model.HomeModel
import com.example.hotelapp.databinding.ItemVp2Binding
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class VpAdapter : ListAdapter<HomeModel, VpAdapter.ImageViewHolder>(DiffCallback()) {

    class ImageViewHolder(private val binding: ItemVp2Binding) : RecyclerView.ViewHolder(binding.root) {
        private val viewPager: ViewPager2 = binding.viewPager
        private val dots:DotsIndicator = binding.indic

        private val imageAdapter = ImageAdapter()


        init {
            viewPager.adapter = imageAdapter
            dots.setViewPager2(viewPager)
        }

        fun bind(imageModel: HomeModel) {
            imageAdapter.data = imageModel.image_urls
            imageAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemVp2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageModel = getItem(position)
        holder.bind(imageModel)
    }

    private class DiffCallback : DiffUtil.ItemCallback<HomeModel>() {
        override fun areItemsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HomeModel, newItem: HomeModel): Boolean {
            return oldItem == newItem
        }
    }
}