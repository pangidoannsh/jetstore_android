package com.pangidoannsh.jetstore.model

data class OrderProduct(
    val product: Product,
    val count: Int,
    val isWishlist: Boolean
)