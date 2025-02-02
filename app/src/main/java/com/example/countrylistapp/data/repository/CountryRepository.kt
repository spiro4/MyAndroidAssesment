package com.example.countrylistapp.data.repository

import com.example.countrylistapp.data.remote.CountryApiService
import com.example.countrylistapp.data.remote.toDomain
import com.example.countrylistapp.domain.model.Country
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.example.countrylistapp.domain.utils.Result
import kotlinx.coroutines.flow.catch
import java.io.IOException

class CountryRepository @Inject constructor(
    private val apiService: CountryApiService
) {

    fun getCountries(): Flow<Result<List<Country>>> = flow {
        emit(Result.Loading) // Emit loading state
        try {
            val countries = apiService.getCountries().map { it.toDomain() }
            emit(Result.Success(countries)) // Emit success state with data
        } catch (e: IOException) {
            emit(Result.Error("No internet connection"))
        } catch (e: Exception) {
            emit(Result.Error("Failed to fetch countries: ${e.message}"))
        }
    }
}




