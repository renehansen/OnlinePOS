package com.example.mobilepos.presentation.view

@Composable
fun CartView(viewModel: HomeViewModel) {
    val cart = viewModel.cart
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        cart.forEach { product ->
            Text(
                text = product.name,
                modifier = Modifier.padding(4.dp)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { viewModel.checkout() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Checkout")
        }
    }
}