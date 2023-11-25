package com.pangidoannsh.jetstore.ui.screens.cart

import com.pangidoannsh.jetstore.model.OrderProduct

data class CartState(
    val orderProduct: List<OrderProduct>,
    val totalCost: Long
)