package com.example.hotelapp.module

import com.example.data.network.ApiService
import com.example.data.repository.HotelRepositoryImpl
import com.example.domain.repository.HotelRepository
import com.example.domain.usecase.HotelUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ModuleApp {

    @Provides
    fun provideSportApiService(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService):HotelRepository{
        return HotelRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: HotelRepository):HotelUseCase{
        return HotelUseCase(repository)
    }

}