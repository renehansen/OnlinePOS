package com.example.mobilepos.domain.model

data class Cart(
    val products: List<Product> = emptyList()) {
    // Order number just placed in the cart for brevity.
    val orderNumber: Int = nextOrderNumber

    companion object {
        fun empty(): Cart {
            return Cart()
        }

        private var nextOrderNumber: Int = 1
    }

    fun add(product: Product): Cart {
        return copy(products = products + product)
    }

    fun remove(product: Product): Cart {
        return copy(products = products - product)
    }

    fun getAll(): List<Product> {
        return products
    }

    fun totalPrice(): Double {
        return products.sumOf { it.price }
    }

    fun pay(): Cart {
        nextOrderNumber++
        return empty()
    }
}