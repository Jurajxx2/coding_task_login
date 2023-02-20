package com.juraj.login.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import com.juraj.codingtask.ui.theme.Black
import com.juraj.common.components.PrimaryButton
import com.juraj.common.components.SecondaryTextButton
import com.juraj.common.components.SocialLoginButton
import com.juraj.common.navigation.NavigationItem
import com.juraj.login.R
import com.juraj.login.ui.components.CreateAccountView
import com.juraj.login.ui.components.SocialLoginView
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewModel>()
    val isLoading = viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.checkUserLoggedIn()
        viewModel.isUserLoggedIn.collect { isLoggedIn ->
            if (isLoggedIn) {
                navController.navigate(NavigationItem.HomeNavigationItem.route) {
                    popUpTo(NavigationItem.LoginNavigationItem.route) { inclusive = true }
                }
            }
        }
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 0.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_close),
                    contentDescription = stringResource(
                        R.string.login_close_icon_description
                    ),
                    modifier = Modifier.clickable { navController.navigateUp() }
                )
                TextButton({
                    navController.navigate(NavigationItem.HomeNavigationItem.route) {
                        popUpTo(NavigationItem.LoginNavigationItem.route) { inclusive = true }
                    }
                }) {
                    Text(text = stringResource(R.string.login_later_button), color = Black)
                }
            }

            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier.fillMaxHeight(0.5f)) {
                    CreateAccountView(viewModel = viewModel)
                }
                Divider(color = Color.Gray, thickness = 2.dp)
                SocialLoginView()
            }
        }
        if (isLoading.value) {
            CircularProgressIndicator()
        }
    }
}
