package com.example.models

import kotlinx.serialization.Serializable


//a response
//to send a list of multiple
//heroes as a class


@Serializable //allows us to convert this object as a json response
data class Hero(
    val id: Int,
    val name: String,
    val image : String,
    val about: String,
    val rating : Double,
    val power: Int,
    val month: String, //birth month of the hero
    val day: String, // birth+day of the hero
    val family : List<String>, //a list of string to list all the heroes family members
    val abilities: List<String>,
    val natureTypes : List<String>
)
