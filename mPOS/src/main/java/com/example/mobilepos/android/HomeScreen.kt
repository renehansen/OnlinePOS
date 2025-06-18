package com.example.mobilepos.android

import ProductCard
import ProductGroupCard
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.example.mobilepos.core.translation.TranslationKey
import com.example.mobilepos.core.translation.tr
import com.example.mobilepos.core.translation.translationKey
import com.example.mobilepos.core.ui.color.POSColor
import com.example.mobilepos.core.ui.color.util.ProductTypeColorMapper
import com.example.mobilepos.core.ui.padding.POSPadding
import com.example.mobilepos.presentation.viewModel.HomeViewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

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
                viewModel.addToCart(product)
            }
        }
    }
}

@Composable
fun CartView(viewModel: HomeViewModel) {
    val columnWidth = 300

    Card(
        modifier = Modifier
            .width(columnWidth.dp)
            .fillMaxHeight()
            .padding(POSPadding.DEFAULT.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(POSPadding.DEFAULT.dp)
        ) {
            CartTopRow(viewModel)
            CartItemDivider()
            Spacer(modifier = Modifier.height(POSPadding.DEFAULT.dp))

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                CartProductRows(viewModel)
            }
            Spacer(modifier = Modifier.height(POSPadding.DEFAULT.dp))

            CartItemDivider()
            CartTotalRow(viewModel)
            PayButton(viewModel)
        }
    }
}

@Composable
private fun CartTopRow(viewModel: HomeViewModel) {
    val cart = viewModel.cart.collectAsState()
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${
                tr(
                    TranslationKey.ORDER_NO
                )
            } ${cart.value.orderNumber}",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.weight(1f)
        )
        Column(
            horizontalAlignment = Alignment.End
        ) {
            IconButton(onClick = { viewModel.clearCart() }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = tr(TranslationKey.DELETE_ALL)
                )
            }
            Text(
                text = tr(TranslationKey.DELETE_ALL),
                style = MaterialTheme.typography.bodySmall
            )
        }

    }
}

@Composable
private fun CartProductRows(viewModel: HomeViewModel) {
    val cart = viewModel.cart.collectAsState()

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
                    text = "${product.price}",
                    modifier = Modifier.padding(end = POSPadding.EXTRA_SMALL.dp),
                )
                IconButton(onClick = { viewModel.removeFromCart(product) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null // Probably not needed for our purposes, so wont't bother with adding a translation key for this icon
                    )
                }
            }
        }
        CartItemDivider()
    }
}

@Composable
private fun CartTotalRow(viewModel: HomeViewModel) {
    val totalPrice = viewModel.cart.collectAsState().value.totalPrice()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = tr(TranslationKey.TOTAL),
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "$totalPrice",
            style = MaterialTheme.typography.headlineSmall
        )
    }
}

@Composable
private fun PayButton(viewModel: HomeViewModel) {
    Button(
        onClick = { viewModel.pay() },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = POSPadding.SMALL.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(POSColor.MONEY_GREEN),
            contentColor = Color.White,
        )
    ) {
        Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = tr(TranslationKey.PAY)
        )
        Spacer(modifier = Modifier.width(POSPadding.SMALL.dp))
        Text(text = tr(TranslationKey.PAY))
    }
}

@Composable
fun CartItemDivider(modifier: Modifier = Modifier, color: Color = Color.Gray) {
    HorizontalDivider(
        modifier = modifier.padding(vertical = POSPadding.SMALL.dp),
        color = color
    )
}

