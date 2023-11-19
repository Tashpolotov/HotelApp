package com.example.hotelapp.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hotelapp.R
import com.example.hotelapp.databinding.FragmentReservationBinding
import com.example.hotelapp.fragment.bookedfragment.bookedadapter.BookedSecondAdapter
import com.example.hotelapp.fragment.bookedfragment.bookedadapter.InfoAdapter
import com.example.hotelapp.viewmodel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


class ReservationFragment : Fragment() {

    private lateinit var binding: FragmentReservationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReservationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBtn()

    }

    private fun initBtn() {
        binding.btnSuper.setOnClickListener {
            findNavController().navigate(R.id.action_reservationFragment_to_homeFragment)
        }

        binding.imgBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}