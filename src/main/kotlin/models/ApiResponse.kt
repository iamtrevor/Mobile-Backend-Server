package com.example.models


//will contain a list of heroes
data class ApiResponse(
    val success: Boolean,
    val message: String?=null,
    val prevPage: Int?=null, //the android app will support paging 3 library
    val nextPage: Int?=null, //the android app will support paging 3 library
    val heroes: List<Hero> = emptyList(),
)
