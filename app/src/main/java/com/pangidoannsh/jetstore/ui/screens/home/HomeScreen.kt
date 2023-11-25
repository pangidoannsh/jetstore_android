package com.pangidoannsh.jetstore.ui.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pangidoannsh.jetstore.ui.ViewModelFactory
import com.pangidoannsh.jetstore.ui.components.ProductItem
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pangidoannsh.jetstore.R
import com.pangidoannsh.jetstore.data.StoreRepository
import com.pangidoannsh.jetstore.model.FakeProductDataSource
import com.pangidoannsh.jetstore.model.OrderProduct
import com.pangidoannsh.jetstore.ui.common.UiState


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel
) {

    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllProducts()
            }

            is UiState.Success -> {
                if (uiState.data.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = stringResource(id = R.string.empty_products),
                            color = MaterialTheme.colorScheme.secondary,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = modifier
                ) {
                    items(uiState.data) { data ->
                        ProductItem(
                            title = data.product.title,
                            image = data.product.image,
                            price = data.product.price,
                            city = data.product.city,
                            modifier = Modifier.clickable {
                                navigateToDetail(data.product.id)
                            }
                        )
                    }
                }
            }

            is UiState.Error -> {}
        }
    }

}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        navigateToDetail = {}, viewModel = viewModel(
            factory = ViewModelFactory(StoreRepository.getInstance())
        )
    )
}