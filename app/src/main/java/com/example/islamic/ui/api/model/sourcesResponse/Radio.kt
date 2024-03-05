package com.example.islamic.ui.api.model.sourcesResponse

import com.google.gson.annotations.SerializedName

data class Radio(
    @field:SerializedName("id")
    val id:String?=null,
    @field:SerializedName("name")
    val name:String?=null,
    @field:SerializedName("url")
    val url:String?=null


)
