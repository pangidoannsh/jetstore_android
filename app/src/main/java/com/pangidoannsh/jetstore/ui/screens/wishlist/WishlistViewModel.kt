package com.pangidoannsh.jetstore.ui.screens.wishlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.model.OrderProduct
import com.pangidoannsh.jetstore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class WishlistViewModel(
    val repository: StoreRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderProduct>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderProduct>>>
        get() = _uiState

    fun getAllWishlist() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAllWishlist()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderProduct ->
                    _uiState.value = UiState.Success(orderProduct)
                }
        }
    }
}