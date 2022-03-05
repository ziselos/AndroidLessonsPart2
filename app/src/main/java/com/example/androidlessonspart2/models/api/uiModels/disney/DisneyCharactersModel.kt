package com.example.androidlessonspart2.models.api.uiModels.disney

data class DisneyCharactersModel(
    val count: Int,
    val `data`: List<Data>,
    val nextPage: String,
    val totalPages: Int
)