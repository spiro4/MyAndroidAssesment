package com.example.countrylistapp.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.countrylistapp.presentation.viewmodel.CountryViewModel
import com.example.countrylistapp.presentation.ui.components.CountryItem
import com.example.countrylistapp.presentation.ui.components.FilterDialog
import com.example.countrylistapp.domain.model.Country
import com.example.countrylistapp.domain.model.Filter
import com.example.countrylistapp.domain.utils.Result

@Composable
fun MainScreen(viewModel: CountryViewModel = viewModel()) {
    val resultState by viewModel.resultState.collectAsState()
    val countryList by viewModel.countries.collectAsState()
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var showFilterDialog by rememberSaveable { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { focusManager.clearFocus() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Country List",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge
            )

            IconButton(
                onClick = {
                    showFilterDialog = true
                    focusManager.clearFocus()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.FilterList,
                    contentDescription = "Filter",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        when (resultState) {
            is Result.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                        .size(50.dp)
                )            }
            is Result.Error -> {
                Text(
                    text = (resultState as Result.Error).message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxSize().padding(16.dp)
                )
            }
            is Result.Success -> {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        viewModel.setSearchQuery(it)
                    },
                    label = { Text("Search by Country") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    singleLine = true
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(countryList) { country ->
                        CountryItem(country)
                    }
                }
            }
        }
    }

    if (showFilterDialog) {
        FilterDialog(
            viewModel = viewModel,
            onDismiss = {
                showFilterDialog = false
                viewModel.applyFilters()
            }
        )
    }
}

