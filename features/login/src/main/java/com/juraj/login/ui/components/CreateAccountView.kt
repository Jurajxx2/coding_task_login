package com.juraj.login.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.juraj.common.components.PrimaryButton
import com.juraj.common.components.SecondaryTextButton
import com.juraj.login.R
import com.juraj.login.ui.LoginViewModel

@Composable
fun CreateAccountView(viewModel: LoginViewModel) {
    val email = viewModel.email.collectAsState()
    val password = viewModel.password.collectAsState()
    val isCreateAccountEnabled = viewModel.isCreateAccountEnabled.collectAsState(false)

    Column(
        Modifier.heightIn(200.dp, 600.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.login_create_account_title),
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            email.value,
            { viewModel.email.value = it },
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        )
        OutlinedTextField(
            password.value,
            { viewModel.password.value = it },
            Modifier
                .fillMaxWidth()
                .padding(16.dp, 8.dp)
        )
        PrimaryButton(
            text = stringResource(R.string.login_create_account_submit_button),
            isCreateAccountEnabled.value
        ) {
            viewModel.onCreateAccountClick()
        }
        val context = LocalContext.current
        SecondaryTextButton(text = stringResource(R.string.login_already_have_an_account_button)) {
            Toast.makeText(
                context,
                R.string.login_already_have_an_account_toast,
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}