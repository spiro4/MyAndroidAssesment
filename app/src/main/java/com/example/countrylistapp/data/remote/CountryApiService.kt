package com.example.countrylistapp.data.remote
import retrofit2.http.GET

interface CountryApiService {
    @GET("all")
    suspend fun getCountries(): List<CountryDto>
}
