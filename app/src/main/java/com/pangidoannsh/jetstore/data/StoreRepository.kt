package com.pangidoannsh.jetstore.data

import android.util.Log
import com.pangidoannsh.jetstore.model.FakeProductDataSource
import com.pangidoannsh.jetstore.model.OrderProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class StoreRepository {
    private val orderProduct = mutableListOf<OrderProduct>()

    init {
        if (orderProduct.isEmpty()) {
            FakeProductDataSource.dummyProducts.forEach {
                orderProduct.add(OrderProduct(it, 0, false))
            }
        }
    }

    fun getAllProduct(): Flow<List<OrderProduct>> {
        return flowOf(orderProduct)
    }

    fun findProduct(query: String): Flow<List<OrderProduct>> {
        val result = orderProduct.filter { it.product.title.contains(query, ignoreCase = true) }
        return flowOf(result)
    }

    fun getAllWishlist(): Flow<List<OrderProduct>> {
        return getAllProduct().map { products ->
            products.filter { product ->
                product.isWishlist
            }
        }
    }

    fun getOrderProductById(productId: Long): OrderProduct {
        return orderProduct.first {
            it.product.id == productId
        }
    }

    fun getAddedOrderProduct(): Flow<List<OrderProduct>> {
        return getAllProduct()
            .map { orderProducts ->
                orderProducts.filter { orderProduct ->
                    orderProduct.count != 0
                }
            }
    }

    fun addToWishlist(productId: Long, isWishlist: Boolean): Flow<Boolean> {
        val index = orderProduct.indexOfFirst { it.product.id == productId }
        val result = if (index >= 0) {
            val order = orderProduct[index]
            orderProduct[index] =
                order.copy(product = order.product, count = order.count, isWishlist = isWishlist)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun updateOrderProduct(productId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderProduct.indexOfFirst { it.product.id == productId }
        val result = if (index >= 0) {
            val order = orderProduct[index]
            orderProduct[index] =
                order.copy(product = order.product, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    companion object {
        @Volatile
        private var instance: StoreRepository? = null

        fun getInstance(): StoreRepository =
            instance ?: synchronized(this) {
                StoreRepository().apply {
                    instance = this
                }
            }
    }
}