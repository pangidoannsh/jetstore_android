package com.pangidoannsh.jetstore.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.model.OrderProduct
import com.pangidoannsh.jetstore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    val repository: StoreRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderProduct>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderProduct>>>
        get() = _uiState

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    fun setQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun search() {
        viewModelScope.launch {
            repository.findProduct(query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }.collect { orderProduct ->
                    _uiState.value = UiState.Success(orderProduct)
                }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            repository.getAllProduct()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderProduct ->
//                    Log.d("getAllProducts", orderProduct.toString())
                    _uiState.value = UiState.Success(orderProduct)
                }
        }
    }
}