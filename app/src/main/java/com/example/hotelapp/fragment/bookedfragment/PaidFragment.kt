    package com.example.hotelapp.fragment.bookedfragment

    import android.graphics.Color
    import android.os.Bundle
    import android.telephony.PhoneNumberFormattingTextWatcher
    import android.text.Editable
    import android.text.TextWatcher
    import android.util.Log
    import androidx.fragment.app.Fragment
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.EditText
    import androidx.fragment.app.viewModels
    import androidx.lifecycle.lifecycleScope
    import androidx.navigation.fragment.findNavController
    import androidx.recyclerview.widget.LinearLayoutManager
    import com.example.hotelapp.R
    import com.example.hotelapp.databinding.FragmentPaidBinding
    import com.example.hotelapp.fragment.bookedfragment.bookedadapter.BookedAdapter
    import com.example.hotelapp.fragment.bookedfragment.bookedadapter.BookedSecondAdapter
    import com.example.hotelapp.fragment.bookedfragment.bookedadapter.InfoAdapter
    import com.example.hotelapp.viewmodel.HotelViewModel
    import com.example.hotelapp.viewmodel.ValidationViewModel
    import dagger.hilt.android.AndroidEntryPoint
    import kotlinx.coroutines.launch

    @AndroidEntryPoint
    class PaidFragment : Fragment() {

        private lateinit var binding:FragmentPaidBinding
        private val adapter = BookedAdapter()
        private val viewModel by viewModels<HotelViewModel>()
        private val touristAdapter = InfoAdapter(this::removeLastTourist)
        private var isSecondTouristVisible = false
        private val bookedSecondAdapter = BookedSecondAdapter()
        private val validationViewModel by viewModels<ValidationViewModel>()

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            binding = FragmentPaidBinding.inflate(inflater, container, false)
            return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            initAdapter()
            initPhoneNumberEditText()
            initBtn()

        }

        private fun initBtn() {
            binding.cardAdd.setOnClickListener {
                Log.d("ololoo", "Card Add Clicked")
                touristAdapter.addTourist()
            }

            binding.cardViewFirstTourist.setOnClickListener {
                toggleFirstTouristVisibility()
            }


            binding.btnPaid.setOnClickListener {
                if (!validateForm()) {
                    showErrorMessage()
                    Log.d("ololoo", "Переход не выполнен: не все поля заполнены")
                } else {
                    findNavController().navigate(R.id.action_paidFragment_to_reservationFragment)
                    Log.d("ololoo", "Переход выполнен успешно")
                }
            }
        }

        private fun initAdapter() {
            binding.rv.adapter = adapter
            binding.rvPaid.layoutManager = LinearLayoutManager(context)
            binding.rvPaid.adapter = touristAdapter
            binding.rvBooked2.adapter = bookedSecondAdapter
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.hotel.collect{
                    adapter.submitList(it.booked)
                    bookedSecondAdapter.submitList(it.booked)
                }
            }
            viewModel.getBooked()

        }


        private fun initPhoneNumberEditText() {
            val etPhoneNumber: EditText = binding.etNumberPhone

            // Добавляем PhoneNumberFormattingTextWatcher для форматирования номера телефона
            etPhoneNumber.addTextChangedListener(PhoneNumberFormattingTextWatcher())

            // Добавляем TextWatcher для обработки ввода и установки маски
            etPhoneNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                    if (count > before) {
                        // Пользователь добавляет символы
                    } else {
                        // Пользователь удаляет символы
                        val text = etPhoneNumber.text.toString()
                        if (!text.startsWith("+7 ")) {
                            etPhoneNumber.removeTextChangedListener(this)
                            etPhoneNumber.setText("+7 ")
                            etPhoneNumber.setSelection(etPhoneNumber.text.length)
                            etPhoneNumber.addTextChangedListener(this)
                        }
                    }
                }

                override fun afterTextChanged(editable: Editable?) {}
            })

            // Устанавливаем начальное значение текста
            etPhoneNumber.setText("+7 ")
            etPhoneNumber.setSelection(etPhoneNumber.text.length)
        }

        private fun isEditTextVisibleAndFilled(editText: EditText): Boolean {
            return editText.visibility != View.VISIBLE || editText.text.toString().isNotEmpty()
        }

        private fun showErrorMessage() {

            showFieldError(binding.etName)
            showFieldError(binding.etSecondName)
            showFieldError(binding.etDate)
            showFieldError(binding.etCountry)
            showFieldError(binding.etPassport)
            showFieldError(binding.etPassportEnd)

            showFieldError(binding.etNumberPhone)
            showFieldError(binding.etEmail)

        }

        private fun showFieldError(editText: EditText) {
            val text = editText.text.toString().trim()
            val isEmailEt = isEditTextVisibleAndFilled(editText)

            if (text.isEmpty() && editText.visibility == View.VISIBLE) {
                // Устанавливаем красный фон, если поле пустое
                editText.setBackgroundResource(R.drawable.shape_edit)
                editText.background.alpha = 38
                Log.d("EditTextError", "Error in ${editText.id}: Empty field")
            } else if (editText == binding.etNumberPhone) {
                val cleanPhoneNumber = text.replace("+7 ", "")
                if (cleanPhoneNumber.isEmpty() || cleanPhoneNumber.length != 10 || !cleanPhoneNumber.all { it.isDigit() }) {
                    // Устанавливаем красный фон, если номер телефона имеет неверный формат
                    editText.setBackgroundResource(R.drawable.shape_edit)
                    editText.background.alpha = 38
                    Log.d("EditTextError", "Error in ${editText.id}: Invalid phone number")
                } else {
                    // Очищаем цвет фона, если поле заполнено верно
                    editText.background.clearColorFilter()
                    editText.setBackgroundResource(R.drawable.shape_et) // Поменяйте на ваш ресурс фона
                }
            } else if (editText == binding.etEmail && !validationViewModel.isEmailValid(text)) {
                // Устанавливаем красный фон, если email имеет неверный формат
                editText.setBackgroundResource(R.drawable.shape_edit)
                editText.background.alpha = 38
                Log.d("EditTextError", "Error in ${editText.id}: Invalid email")
            } else {
                // Очищаем цвет фона, если поле заполнено верно
                editText.background.clearColorFilter()
                editText.setBackgroundResource(R.drawable.shape_et) // Поменяйте на ваш ресурс фона
            }
        }

        private fun toggleFirstTouristVisibility() {
            if (binding.linear1.visibility == View.VISIBLE) {
                binding.linear1.visibility = View.GONE
            } else {
                binding.linear1.visibility = View.VISIBLE
            }
        }
        private fun removeLastTourist() {
            touristAdapter.removeLastTourist()
        }
        private fun validateForm(): Boolean {
            var name = binding.etName.text.toString()
            val secondName = binding.etSecondName.text.toString()
            val phoneNumber = binding.etNumberPhone.text.toString()
            var email = binding.etEmail.text.toString()
            val date = binding.etDate.text.toString()
            val country = binding.etCountry.text.toString()
            val passport = binding.etPassport.text.toString()
            val passportEnd = binding.etPassportEnd.text.toString()

            validationViewModel.validForm(
                name,
                secondName,
                phoneNumber,
                email,
                date,
                country,
                passport,
                passportEnd
            )

            // Проверка основных полей формы
            if (validationViewModel.validation.value == false) {
                return false
            }

            // Проверка полей туристов
            for (i in 0 until touristAdapter.itemCount) {
                val holder = binding.rvPaid.findViewHolderForAdapterPosition(i) as? InfoAdapter.TouristViewHolder
                val etTouristName = holder?.etTouristName
                val etTouristDate = holder?.binding?.etDate
                val etTouristCountry = holder?.binding?.etCountry
                val etTouristPassport = holder?.binding?.etPassport
                val etTouristPassportEnd = holder?.binding?.etPassportEnd
                val etSecondName = holder?.binding?.etSecondName

                val touristName = etTouristName?.text.toString()
                val touristSecondName = etSecondName?.text.toString()
                val touristDate = etTouristDate?.text.toString()
                val touristCountry = etTouristCountry?.text.toString()
                val touristPassport = etTouristPassport?.text.toString()
                val touristPassportEnd = etTouristPassportEnd?.text.toString()

                if (touristName.isEmpty()) {
                    etTouristName?.error = "Введите имя туриста"
                    return false
                }

                if(touristSecondName.isEmpty()) {
                    etSecondName?.error = "Введите фамилию туриста"
                }

                if (touristDate.isEmpty()) {
                    etTouristDate?.error = "Введите дату рождения туриста"
                    return false
                }

                if (touristCountry.isEmpty()) {
                    etTouristCountry?.error = "Введите гражданство туриста"
                    return false
                }

                if (touristPassport.isEmpty()) {
                    etTouristPassport?.error = "Введите номер заграничного паспорта туриста"
                    return false
                }

                if (touristPassportEnd.isEmpty()) {
                    etTouristPassportEnd?.error = "Введите срок действия заграничного паспорта туриста"
                    return false
                }
            }
            return true
        }

    }