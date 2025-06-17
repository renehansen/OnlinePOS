package com.example.mobilepos.android

import ProductCard
import ProductGroupCard
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mobilepos.core.translation.tr
import com.example.mobilepos.core.translation.translationKey
import com.example.mobilepos.core.ui.color.util.ProductTypeColorMapper
import com.example.mobilepos.core.ui.padding.POSPadding
import com.example.mobilepos.presentation.viewModel.HomeViewModel

@Composable
fun NewHomeScreen(viewModel: HomeViewModel) {

    Row(modifier = Modifier.fillMaxSize()) {
        ProductGroupsView(viewModel)
        VerticalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = Color.Gray
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) {
            ProductsView(viewModel = viewModel)
        }
        CartView(viewModel)
    }
}


@Composable
fun ProductGroupsView(
    viewModel: HomeViewModel
) {
    val selectedProductGroup = viewModel.selectedProductGroup.collectAsState()
    val productGroups = viewModel.productGroups.collectAsState()
    val columnWidth = 200

    Column(
        modifier = Modifier
            .width(columnWidth.dp)
            .padding(POSPadding.DEFAULT.dp)
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        productGroups.value.forEach { productGroup ->
            ProductGroupCard(
                text = tr(productGroup.type.translationKey),
                backgroundColor =
                    Color(ProductTypeColorMapper.getColorForProductType(productGroup.type)),
                isSelected = productGroup == selectedProductGroup.value,
            ) {
                viewModel.selectProductGroup(productGroup)
            }
            Spacer(modifier = Modifier.height(POSPadding.DEFAULT.dp))
        }
    }
}

@Composable
fun ProductsView(
    viewModel: HomeViewModel
) {
    val products = viewModel.products.collectAsState()
    val selectedProductGroup = viewModel.selectedProductGroup.collectAsState()
    val cardColor = selectedProductGroup.value?.type?.let {
        Color(ProductTypeColorMapper.getColorForProductType(it))
    } ?: Color.White
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = POSPadding.LARGE.dp),
        contentPadding = PaddingValues(POSPadding.DEFAULT.dp),
        verticalArrangement = Arrangement.spacedBy(
            POSPadding.DEFAULT.dp
        ),
        horizontalArrangement = Arrangement.spacedBy(
            POSPadding.DEFAULT.dp
        )
    ) {
        items(products.value) { product ->
            ProductCard(
                product = product,
                backgroundColor = cardColor
            ) {
                Log.d("ProductCard", "Clicked on ${product.name}")
                viewModel.addToCart(product)
            }
        }
    }
}

@Composable
fun CartView(viewModel: HomeViewModel) {
    val columnWidth = 300
    val cart = viewModel.cart.collectAsState()
    val totalPrice = cart.value.getAll().sumOf { it.price }

    Column(
        modifier = Modifier
            .width(columnWidth.dp)
            .fillMaxHeight()
            .padding(POSPadding.DEFAULT.dp)
    ) {
        // Top Row: Order Number and Delete All
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Order #${viewModel.cart.value.orderNumber}",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.weight(1f)
            )
            Column(
                horizontalAlignment = Alignment.End
            ) {
                IconButton(onClick = { viewModel.cart.value.clear() }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete all"
                    )
                }
                Text(
                    text = "Delete all",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(modifier = Modifier.height(POSPadding.DEFAULT.dp))

        // Middle Section: Scrollable List of Products
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            cart.value.getAll().forEach { product ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = product.name,
                        modifier = Modifier.weight(1f)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "$${product.price}",
                            modifier = Modifier.padding(end = POSPadding.SMALL.dp),
                        )
                        IconButton(onClick = { viewModel.cart.value.remove(product) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete product"
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(POSPadding.SMALL.dp))
            }
        }

        Spacer(modifier = Modifier.height(POSPadding.DEFAULT.dp))

        // Bottom Section: Total Price and Pay Button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total: $${totalPrice}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Button(
            onClick = { viewModel.buy() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = POSPadding.SMALL.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Pay"
            )
            Spacer(modifier = Modifier.width(POSPadding.SMALL.dp))
            Text(text = "Pay")
        }
    }
}

