package com.example.countrylistapp.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.countrylistapp.domain.model.Country

@Composable
fun CountryItem(country: Country) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(containerColor = androidx.compose.material3.MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(country.flagUrl),
                contentDescription = country.name,
                modifier = Modifier.size(50.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = country.name,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Capital: ${country.capital}",
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Population: ${country.population}",
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
