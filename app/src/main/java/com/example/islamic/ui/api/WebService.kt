package com.example.islamic.ui.api
import com.example.islamic.ui.api.model.sourcesResponse.RadiosChannels
import retrofit2.Call
import retrofit2.http.GET

interface WebService {
    @GET("api/v3/radios")
    fun getRadioSources() :Call<RadiosChannels>
}