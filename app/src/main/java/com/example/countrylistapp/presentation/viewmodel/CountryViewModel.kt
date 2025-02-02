package com.example.countrylistapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylistapp.domain.model.*
import com.example.countrylistapp.domain.usecase.CountryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.countrylistapp.domain.utils.Result

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val countryUseCases: CountryUseCases
) : ViewModel() {

    private val _allCountries = MutableStateFlow<List<Country>>(emptyList())
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    private val _filteredCountries = MutableStateFlow<List<Country>>(emptyList())

    val countries: StateFlow<List<Country>> = _countries

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _activeFilter = MutableStateFlow<Filter>(Filter.None)
    val activeFilter: StateFlow<Filter> = _activeFilter

    private val _resultState = MutableStateFlow<Result<List<Country>>>(Result.Loading)
    val resultState: StateFlow<Result<List<Country>>> = _resultState

    init {
        fetchCountries()
    }

    fun fetchCountries() {
        viewModelScope.launch {
            countryUseCases.getCountries().collect { result ->
                when (result) {
                    is Result.Success -> {
                        val sortedCountries = result.data.sortedBy { it.name }
                        _allCountries.value = sortedCountries
                        _filteredCountries.value = sortedCountries
                        _countries.value = sortedCountries
                        _resultState.value = Result.Success(sortedCountries)
                    }
                    is Result.Error -> {
                        _resultState.value = result
                        _countries.value = emptyList() // Show empty state on failure
                    }
                    is Result.Loading -> {
                        _resultState.value = Result.Loading
                    }
                }
            }
        }
    }

    val setSearchQuery: (String) -> Unit = { query ->
        _searchQuery.value = query
        _countries.value = countryUseCases.searchCountries(query, _filteredCountries.value)
    }

    val setPopulationFilter: (PopulationRange?) -> Unit = { range ->
        updateFilter(if (range != null) Filter.PopulationFilter(range) else Filter.None)
    }

    val setUserStateFilter: (UserState?) -> Unit = { state ->
        updateFilter(if (state != null) Filter.UserStateFilter(state) else Filter.None)
    }

    val clearFilters: () -> Unit = {
        _activeFilter.value = Filter.None
        applyFilters()
    }

    private val updateFilter: (Filter) -> Unit = { filter ->
        _activeFilter.value = filter
        applyFilters()
    }

    val applyFilters: () -> Unit = {
        viewModelScope.launch {
            _filteredCountries.value = countryUseCases.filterCountries(_activeFilter.value, _allCountries.value)

            // Apply search within filtered results
            _countries.value = countryUseCases.searchCountries(_searchQuery.value, _filteredCountries.value)
        }
    }
}


