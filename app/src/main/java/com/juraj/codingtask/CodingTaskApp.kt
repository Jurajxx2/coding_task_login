package com.juraj.codingtask

import android.app.Application
import com.juraj.common.utils.EncryptedPrefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CodingTaskApp: Application() {

    override fun onCreate() {
        super.onCreate()
        EncryptedPrefs.initEncryptedPrefs(this)
    }
}