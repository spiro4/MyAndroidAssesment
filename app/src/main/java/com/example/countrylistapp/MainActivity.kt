package com.example.countrylistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countrylistapp.presentation.ui.screens.MainScreen
import com.example.countrylistapp.presentation.viewmodel.CountryViewModel
import androidx.navigation.compose.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryListApp()
        }
    }
}

@Composable
fun CountryListApp() {
    val navController = rememberNavController()
    val viewModel: CountryViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {
        composable("mainScreen") {
            MainScreen(viewModel = viewModel)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListPreview() {
    CountryListApp()
}

