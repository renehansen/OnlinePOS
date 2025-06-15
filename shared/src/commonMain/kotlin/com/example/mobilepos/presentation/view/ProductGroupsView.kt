package com.example.mobilepos.presentation.view

@Composable
fun ProductGroupsView(
    viewModel: HomeViewModel,
    onProductGroupClick: (ProductType) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        viewModel.productGroups.forEach { productGroup ->
            Button(
                onClick = { onProductGroupClick(productGroup) },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(productGroup.name)
            }
        }
    }
}