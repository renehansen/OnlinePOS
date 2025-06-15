package com.example.mobilepos.domain.model

enum class ProductType {
    BURGER,
    SANDWICH,
    GRILLED_SANDWICH,
    KIDS_MENU,
    SALAD,
    SIDE_ORDER
}

data class ProductGroup(
    val type: ProductType,
    val products: List<Product>
)