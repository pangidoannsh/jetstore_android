package com.pangidoannsh.jetstore.ui.screens.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    val repository: StoreRepository
): ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderProduct() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderProduct()
                .collect { orderProduct ->
                    val totalCost =
                        orderProduct.sumOf { it.product.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderProduct, totalCost))
                }
        }
    }

    fun updateOrderReward(rewardId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderProduct(rewardId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderProduct()
                    }
                }
        }
    }
}