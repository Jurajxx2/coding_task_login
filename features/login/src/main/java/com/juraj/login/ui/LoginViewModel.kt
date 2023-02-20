package com.juraj.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.juraj.common.navigation.NavigationItem
import com.juraj.common.utils.isPasswordValid
import com.juraj.common.utils.launchNetworkRequest
import com.juraj.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val userRepository: UserRepository) : ViewModel() {

    private val _isUserLoggedIn = MutableSharedFlow<Boolean>()
    val isUserLoggedIn: SharedFlow<Boolean> = _isUserLoggedIn

    private val _message = MutableSharedFlow<String>()
    val message: SharedFlow<String> = _message


    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val emailPasswordValidation = email.combine(password) { currEmail, currPassword ->
        Patterns.EMAIL_ADDRESS.matcher(currEmail).matches() && currPassword.isPasswordValid()
    }

    val isCreateAccountEnabled = emailPasswordValidation.combine(isLoading) { curremailPasswordValidation, currIsLoading ->
        curremailPasswordValidation && !currIsLoading
    }

    init {
        checkUserLoggedIn()
    }

    fun checkUserLoggedIn() {
        launchNetworkRequest({
            _isUserLoggedIn.emit(userRepository.isUserLoggedIn())
        })
    }

    fun onCreateAccountClick() {
        launchNetworkRequest({
            _isLoading.emit(true)
            userRepository.createAccount(email.value, password.value)
            _isUserLoggedIn.emit(true)
        }, {
           _isLoading.value = false
        }, {
            _isLoading.value = false
            viewModelScope.launch {
                it.message?.let { it1 -> _message.emit(it1) }
            }
        })
    }
}