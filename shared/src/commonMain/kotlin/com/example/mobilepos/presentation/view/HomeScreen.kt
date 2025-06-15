package com.example.mobilepos.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilepos.presentation.viewModel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    var selectedProductGroup by remember { mutableStateOf<ProductType?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        // Product Groups View
        ProductGroupsView(
            viewModel = viewModel,
            onProductGroupClick = { productGroup ->
                selectedProductGroup = productGroup
            }
        )

        // Products View
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ProductsView(
                viewModel = viewModel,
                selectedProductGroup = selectedProductGroup
            )
        }

        // Cart View
        Column(
            modifier = Modifier
                .width(300.dp)
                .fillMaxHeight()
        ) {
            CartView(viewModel = viewModel)
        }
    }
}


