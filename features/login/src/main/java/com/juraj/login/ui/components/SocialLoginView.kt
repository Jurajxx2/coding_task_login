package com.juraj.login.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.juraj.common.components.SocialLoginButton
import com.juraj.login.R

@Composable
fun SocialLoginView() {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        SocialLoginButton(text = stringResource(R.string.login_social_login_google)) {
            Toast.makeText(
                context,
                R.string.login_social_login_google_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
        SocialLoginButton(text = stringResource(R.string.login_social_login_facebook)) {
            Toast.makeText(
                context,
                R.string.login_social_login_facebook_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
        SocialLoginButton(text = stringResource(R.string.login_social_login_apple)) {
            Toast.makeText(
                context,
                R.string.login_social_login_apple_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}