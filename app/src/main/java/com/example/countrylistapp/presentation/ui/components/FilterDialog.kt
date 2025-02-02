package com.example.countrylistapp.presentation.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.countrylistapp.domain.model.*
import com.example.countrylistapp.presentation.viewmodel.CountryViewModel

@Composable
fun FilterDialog(viewModel: CountryViewModel, onDismiss: () -> Unit) {
    val activeFilter by viewModel.activeFilter.collectAsState()

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Filter Countries") },
        text = {
            Column {
                Text("Filter by Population:")
                PopulationRange.values().forEach { range ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.setPopulationFilter(range)
                            }
                    ) {
                        RadioButton(
                            selected = activeFilter is Filter.PopulationFilter && (activeFilter as Filter.PopulationFilter).range == range,
                            onClick = {
                                viewModel.setPopulationFilter(range)
                            }
                        )
                        Text(text = range.name)
                    }
                }

                Text("Filter by User State:")
                UserState.values().forEach { state ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.setUserStateFilter(state)
                            }
                    ) {
                        RadioButton(
                            selected = activeFilter is Filter.UserStateFilter && (activeFilter as Filter.UserStateFilter).state == state,
                            onClick = {
                                viewModel.setUserStateFilter(state)
                            }
                        )
                        Text(text = state.name)
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("Apply")
            }
        },
        dismissButton = {
            Button(onClick = {
                viewModel.clearFilters()
                onDismiss()
            }) {
                Text("Clear Filters")
            }
        }
    )
}
