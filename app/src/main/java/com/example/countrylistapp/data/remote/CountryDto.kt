package com.example.countrylistapp.data.remote

import com.example.countrylistapp.domain.model.Country
import com.example.countrylistapp.domain.model.UserState
import com.google.gson.annotations.SerializedName

data class CountryDto(
    @SerializedName("name") val name: NameDto,
    @SerializedName("population") val population: Long,
    @SerializedName("flags") val flags: FlagsDto,
    @SerializedName("capital") val capital: List<String>?,
    @SerializedName("region") val region: String
)

data class NameDto(
    @SerializedName("common") val common: String
)

data class FlagsDto(
    @SerializedName("png") val png: String
)

fun CountryDto.toDomain(userState: UserState = UserState.ACTIVE): Country {
    return Country(
        name = name.common,
        population = population,
        flagUrl = flags.png,
        capital = capital?.firstOrNull() ?: "N/A",
        region = region,
        userState = userState
    )
}
