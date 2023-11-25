package com.pangidoannsh.jetstore.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.ui.screens.cart.CartViewModel
import com.pangidoannsh.jetstore.ui.screens.detail.DetailViewModel
import com.pangidoannsh.jetstore.ui.screens.home.HomeViewModel
import com.pangidoannsh.jetstore.ui.screens.wishlist.WishlistViewModel

class ViewModelFactory(private val repository: StoreRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            return WishlistViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}