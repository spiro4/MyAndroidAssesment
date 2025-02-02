package com.example.countrylistapp.domain.model

data class Country(
    val name: String,
    val population: Long,
    val flagUrl: String,
    val capital: String,
    val region: String,
    val userState: UserState
)

enum class UserState {
    ACTIVE, INACTIVE, BLOCKED, TERMINATED
}



enum class Position {
    HOUSEKEEPERS, FRONT_DESK, GENERAL_MANAGER, MAINTENANCE
}

enum class PopulationRange {
    LESS_THAN_1M, LESS_THAN_5M, LESS_THAN_10M
}
