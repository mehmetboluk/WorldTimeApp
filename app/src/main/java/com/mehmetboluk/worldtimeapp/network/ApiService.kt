package com.mehmetboluk.worldtimeapp.network

import com.mehmetboluk.worldtimeapp.model.CityTime
import retrofit2.http.GET

interface ApiService {

    @GET("/cities.json")
    suspend fun getDataFromApi() : CityTime
}