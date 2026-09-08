package com.example.models

import kotlinx.serialization.Serializable


//will contain a list of heroes
@Serializable //allows us to convert this object as a json response
data class ApiResponse(
    val success: Boolean,
    val message: String?=null,
    val prevPage: Int?=null, //the android app will support paging 3 library
    val nextPage: Int?=null, //the android app will support paging 3 library
    val heroes: List<Hero> = emptyList(),
)
