package com.example.mobilepos.presentation.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mobilepos.core.translation.TranslationKey
import com.example.mobilepos.core.translation.tr
import com.example.mobilepos.core.translation.translationKey
import com.example.mobilepos.core.ui.POSPadding
import com.example.mobilepos.presentation.viewModel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    Row(modifier = Modifier.fillMaxSize()) {
        ProductGroupsView(viewModel = viewModel)

        // Products View
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ProductsView(viewModel = viewModel)
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

@Composable
fun ProductGroupsView(
    viewModel: HomeViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        viewModel.productGroups.value.forEach { productGroup ->
            Button(
                onClick = { viewModel.selectProductGroup(productGroup) },
                modifier = Modifier.padding(POSPadding.SMALL.dp)
            ) {
                Text(tr((productGroup.type.translationKey)))
            }
        }
    }
}

@Composable
fun ProductsView(
    viewModel: HomeViewModel
) {
    val products = viewModel.products
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(products.value) { product ->
            Button(
                onClick = { viewModel.addToCart(product) },
                modifier = Modifier.padding(POSPadding.SMALL.dp)
            ) {
                Text(product.name)
            }
        }
    }
}

@Composable
fun CartView(viewModel: HomeViewModel) {
    val cart = viewModel.cart.value
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        cart.getAll().forEach { product ->
            Text(
                text = product.name,
                modifier = Modifier.padding(POSPadding.SMALL.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { viewModel.buy() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(POSPadding.DEFAULT.dp)
        ) {
            Text(tr(TranslationKey.BUY))
        }
    }
}

