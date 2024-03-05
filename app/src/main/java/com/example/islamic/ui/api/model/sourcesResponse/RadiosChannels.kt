package com.example.islamic.ui.api.model.sourcesResponse

import com.google.gson.annotations.SerializedName

data class RadiosChannels(
    @field:SerializedName("radios")
    val radios: List<Radio?>?=null
)
