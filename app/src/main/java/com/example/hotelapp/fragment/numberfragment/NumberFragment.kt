package com.example.hotelapp.fragment.numberfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hotelapp.R
import com.example.hotelapp.fragment.numberfragment.roomadapter.Room1Adapter

import com.example.hotelapp.databinding.FragmentNumberBinding
import com.example.hotelapp.viewmodel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NumberFragment : Fragment() {

    private lateinit var binding:FragmentNumberBinding
    private val adapter = Room1Adapter { onGoButtonClicked() }



    private val viewModel by viewModels<HotelViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNumberBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtn()
        initAdapter()

    }



    private fun initAdapter() {
        binding.rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.hotel.collect{
                Log.d("NumberFragment", "Room Name: ${it.room}")

                adapter.submitList(it.room)
            }
        }
        viewModel.getRoom()
    }

    private fun initBtn() {
        val name = arguments?.getString("name")
        binding.tvName.text = name
        binding.imgBack.setOnClickListener{
            findNavController().popBackStack()
        }


    }
    private fun onGoButtonClicked() {
        findNavController().navigate(R.id.action_numberFragment_to_paidFragment)
    }


}