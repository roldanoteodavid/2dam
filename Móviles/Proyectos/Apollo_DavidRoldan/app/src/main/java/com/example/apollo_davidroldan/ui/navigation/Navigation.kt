package com.example.apollo_davidroldan.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apollo_davidroldan.ui.common.BottomBar
import com.example.apollo_davidroldan.ui.screens.login.LoginScreen


@Composable
fun Navigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login",
    ) {
        composable(
            "login"
        ) {
            LoginScreen(
//                onViewDetalle = {uuid ->
//                    navController.navigate("detalle/${uuid}")
//                },
//                bottomNavigationBar =  {
//                    BottomBar(
//                        navController = navController,
//                        screens = screensBottomBar)
//                }
            )
        }
    }



}
