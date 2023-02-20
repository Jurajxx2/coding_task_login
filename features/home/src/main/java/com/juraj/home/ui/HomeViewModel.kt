package com.juraj.home.ui

import androidx.lifecycle.ViewModel
import com.juraj.common.utils.launchNetworkRequest
import com.juraj.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val userRepository: UserRepository): ViewModel() {

    private val _logoutActionCompleted = MutableSharedFlow<Boolean>()
    val logoutActionCompleted: SharedFlow<Boolean> = _logoutActionCompleted

    fun logout() {
        launchNetworkRequest({
            userRepository.logout()
            _logoutActionCompleted.emit(true)
        })
    }
}