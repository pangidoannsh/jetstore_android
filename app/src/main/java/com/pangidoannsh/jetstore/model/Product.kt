package com.pangidoannsh.jetstore.model

data class Product(
    val id: Long,
    val image: Int,
    val title: String,
    val price: Long,
    val city: String,
    val description:String
)