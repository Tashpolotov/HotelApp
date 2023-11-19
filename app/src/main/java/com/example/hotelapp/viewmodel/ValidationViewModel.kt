package com.example.hotelapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ValidationViewModel:ViewModel() {

    private val _validation = MutableLiveData<Boolean>()
    val validation : LiveData<Boolean> get() = _validation


    fun validForm( name: String, secondName: String, phoneNumber: String, email: String,
                   dateOfBirth: String, country: String, passportNumber: String, passportExpiration: String ) {
        val isNameValid = name.isNotEmpty()
        val isSecondNameValid = secondName.isNotEmpty()
        val isPhoneNumberValid = isPhoneNumberValid(phoneNumber)
        val isEmailValid = isEmailValid(email)
        val isDateBirth = isDateOfBirthValid(dateOfBirth)
        val isCountry = isCountryValid(country)
        val isPassportNumber = isPassportNumberValid(passportNumber)
        val isPassportExpiration = isPassportExpirationValid(passportExpiration)


        _validation.value = isNameValid && isSecondNameValid && isPhoneNumberValid && isEmailValid &&
                isDateBirth && isCountry && isPassportNumber && isPassportExpiration
    }
     fun isPhoneNumberValid(phoneNumber: String): Boolean {
        // Убираем префикс "+7 " перед проверкой
        val cleanPhoneNumber = phoneNumber.replace("+7 ", "")
        return cleanPhoneNumber.length == 10 && cleanPhoneNumber.all { it.isDigit() }
    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun isDateOfBirthValid(dateOfBirth: String): Boolean {
        // Добавьте соответствующие проверки для даты рождения
        return dateOfBirth.isNotEmpty()
    }

    private fun isCountryValid(country: String): Boolean {
        // Добавьте соответствующие проверки для гражданства
        return country.isNotEmpty()
    }

    private fun isPassportNumberValid(passportNumber: String): Boolean {
        // Добавьте соответствующие проверки для номера заграничного паспорта
        return passportNumber.isNotEmpty()
    }

    private fun isPassportExpirationValid(passportExpiration: String): Boolean {
        // Добавьте соответствующие проверки для срока действия паспорта
        return passportExpiration.isNotEmpty()
    }
}
