package com.pangidoannsh.jetstore.ui.screens.detail

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.model.OrderProduct
import com.pangidoannsh.jetstore.model.Product
import com.pangidoannsh.jetstore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: StoreRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderProduct>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderProduct>>
        get() = _uiState

    fun getDetail(productId: Long): OrderProduct {
        return repository.getOrderProductById(productId)
    }

    fun getProductId(productId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderProductById(productId))
        }
    }

    fun addToCart(product: Product, count: Int) {
        viewModelScope.launch {
            repository.updateOrderProduct(product.id, count)
        }
    }

    fun addToWishlist(product: Product, newStatus: Boolean) {
        viewModelScope.launch {
            repository.addToWishlist(product.id, newStatus)
            _uiState.value = UiState.Success(repository.getOrderProductById(product.id))
        }
    }
}