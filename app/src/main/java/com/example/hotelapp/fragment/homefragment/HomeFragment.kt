package com.example.hotelapp.fragment.homefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.model.HomeModel
import com.example.hotelapp.R
import com.example.hotelapp.fragment.homefragment.homeadapter.HomeAdapter
import com.example.hotelapp.fragment.homefragment.homeadapter.VpAdapter
import com.example.hotelapp.databinding.FragmentHomeBinding
import com.example.hotelapp.viewmodel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val adapter = HomeAdapter()
    private val vpAdapter = VpAdapter()
    private val viewModel by viewModels<HotelViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

    }

    private fun initBtn(model: HomeModel) {
        binding.btnGo.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", model.name)

            findNavController().navigate(R.id.action_homeFragment_to_numberFragment, bundle)
        }
    }

    private fun initAdapter() {
        binding.rv.adapter = adapter
        binding.rv1.adapter = vpAdapter // Присваиваем адаптер ViewPager


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hotel.collect { data ->

                // Логируем количество фотографий в списке
                Log.d("HomeFragment", "Number of photos: ${data.home.size}")
                vpAdapter.submitList(data.home)
                adapter.submitList(data.home)
                for (homeModel in data.home) {
                    initBtn(homeModel)
                }
            }
        }

        viewModel.getHome()
    }
}