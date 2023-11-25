package com.pangidoannsh.jetstore.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.model.OrderProduct
import com.pangidoannsh.jetstore.ui.ViewModelFactory
import com.pangidoannsh.jetstore.ui.common.UiState

@Composable
fun DetailScreen(
    productId: Long,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            StoreRepository.getInstance()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
//    var productState: OrderProduct? by remember {
//        mutableStateOf(viewModel.getDetail(productId))
//    }
//
//    fun setProductState(product: OrderProduct) {
//        productState = product
//    }
//    productState?.let { data ->
//        DetailContent(
//            data.product.image,
//            data.product.title,
//            data.product.price,
//            data.product.description,
//            data.count,
//            data.isWishlist,
//            onBackClick = navigateBack,
//            onAddToCart = { count ->
//                viewModel.addToCart(data.product, count)
//                navigateToCart()
//            },
//            onClickWishlist = {
//                viewModel.addToWishlist(data.product, !data.isWishlist)
//                setProductState(viewModel.getDetail(productId))
//            }
//        )
//    }
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProductId(productId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    data.product.image,
                    data.product.title,
                    data.product.price,
                    data.product.description,
                    data.count,
                    data.isWishlist,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.product, count)
                        navigateToCart()
                    },
                    onClickWishlist = {
                        viewModel.addToWishlist(data.product, !data.isWishlist)
                    }
                )
            }

            is UiState.Error -> {}
        }
    }
}

