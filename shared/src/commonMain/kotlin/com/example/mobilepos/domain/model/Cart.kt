package com.example.mobilepos.domain.model

data class Cart(
    val products: List<Product> = emptyList()) {

    companion object {
        fun empty(): Cart {
            return Cart()
        }
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

    fun clear(): Cart {
        return copy(products = emptyList())
    }

    fun totalPrice(): Double {
        return products.sumOf { it.price }
    }
}