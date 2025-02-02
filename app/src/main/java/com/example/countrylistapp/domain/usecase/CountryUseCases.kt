package com.example.countrylistapp.domain.usecase

import com.example.countrylistapp.data.repository.CountryRepository
import com.example.countrylistapp.domain.model.*
import com.example.countrylistapp.domain.utils.Result
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CountryUseCases @Inject constructor(
    private val repository: CountryRepository
) {
    /*fun getCountries(): Flow<List<Country>> =
        repository.getCountries().map { countries ->
            countries.sortedBy { it.name } // Sort
        }*/

    fun getCountries(): Flow<Result<List<Country>>> =
    repository.getCountries()

    fun searchCountries(query: String, currentList: List<Country>): List<Country> {
        return if (query.isBlank()) {
            currentList // Return existing list
        } else {
            currentList.filter {
                it.name.startsWith(query, ignoreCase = true) ||
                        it.name.contains(query, ignoreCase = true)
            }.sortedBy {
                if (it.name.startsWith(query, ignoreCase = true)) 0 else 1
            }
        }
    }

    fun filterCountries(filter: Filter, countries: List<Country>): List<Country> {
        return when (filter) {
            is Filter.PopulationFilter -> countries.filter { country ->
                when (filter.range) {
                    PopulationRange.LESS_THAN_1M -> country.population < 1_000_000
                    PopulationRange.LESS_THAN_5M -> country.population < 5_000_000
                    PopulationRange.LESS_THAN_10M -> country.population < 10_000_000
                }
            }
            is Filter.UserStateFilter -> countries.filter { it.userState == filter.state }
            is Filter.RegionFilter -> TODO()
            Filter.None -> countries
        }
    }
}


