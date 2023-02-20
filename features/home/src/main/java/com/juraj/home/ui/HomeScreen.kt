package com.juraj.home.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.juraj.common.components.PrimaryButton
import com.juraj.common.navigation.NavigationItem
import com.juraj.home.R

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewModel>()

    LaunchedEffect(Unit) {
        viewModel.logoutActionCompleted.collect { isLoggedIn ->
            if (isLoggedIn) {
                navController.navigate(NavigationItem.LoginNavigationItem.route) {
                    popUpTo(NavigationItem.HomeNavigationItem.route) { inclusive = true }
                }
            }
        }
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.hello_animation))
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(composition, iterations = LottieConstants.IterateForever)
        PrimaryButton(text = stringResource(R.string.home_logout)) {
            viewModel.logout()
        }
    }
}
