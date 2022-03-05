package com.example.androidlessonspart2.models.api

data class NewsByCategoryResponse(
    val category: String,
    val `data`: List<Data>,
    val success: Boolean
)