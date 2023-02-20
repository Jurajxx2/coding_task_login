package com.juraj.common.utils

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

fun <T> ViewModel.launchNetworkRequest(
    backgroundBlock: suspend CoroutineScope.() -> T,
    onSuccess: (T) -> Unit = {},
    onError: (e: NetworkException) -> Unit = {}
) {
    viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = backgroundBlock()
            MainScope().launch {
                try {
                    onSuccess(result)
                } catch (e: IllegalStateException) {
                    Log.d("CodingTask", e.stackTraceToString())
                }
            }
        } catch (e: NetworkException) {
            MainScope().launch {
                try {
                    onError(e)
                } catch (e: IllegalStateException) {
                    Log.d("CodingTask", e.stackTraceToString())
                }
            }
        }
    }
}

class NetworkException(val code: Int, message: String): Throwable(message)