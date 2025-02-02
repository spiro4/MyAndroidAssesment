package com.example.countrylistapp.domain.model

sealed class Filter {
    object None : Filter()
    data class PopulationFilter(val range: PopulationRange) : Filter()
    data class RegionFilter(val region: String) : Filter()
    data class UserStateFilter(val state: UserState) : Filter()
}
