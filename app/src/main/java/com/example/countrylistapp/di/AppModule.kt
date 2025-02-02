package com.example.countrylistapp.di

import android.content.Context
import android.content.SharedPreferences
import com.example.countrylistapp.data.remote.CountryApiService
import com.example.countrylistapp.data.repository.CountryRepository
import com.example.countrylistapp.domain.usecase.CountryUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://restcountries.com/v3.1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryApiService(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCountryRepository(apiService: CountryApiService): CountryRepository {
        return CountryRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideCountryUseCases(repository: CountryRepository): CountryUseCases {
        return CountryUseCases(repository)
    }

}
