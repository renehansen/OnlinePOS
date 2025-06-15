package com.example.mobilepos.presentation.view

@Composable
fun ProductsView(
    viewModel: HomeViewModel,
    selectedProductGroup: ProductType?
) {
    val products = viewModel.getProductsForGroup(selectedProductGroup)
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxHeight()
    ) {
        items(products.size) { index ->
            val product = products[index]
            Button(
                onClick = { viewModel.addToCart(product) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(product.name)
            }
        }
    }
}