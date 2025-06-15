package com.example.mobilepos.presentation.viewModel

import com.example.mobilepos.domain.ProductManager
import com.example.mobilepos.domain.model.Cart
import com.example.mobilepos.domain.model.Product
import com.example.mobilepos.domain.model.ProductGroup
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(private val productManager: ProductManager) {
    private val _productGroups = MutableStateFlow(productManager.getAllProductGroups())
    val productGroups: StateFlow<List<ProductGroup>> = _productGroups

    private val _selectedProductGroup = MutableStateFlow<ProductGroup?>(null)
    val selectedProductGroup: StateFlow<ProductGroup?> = _selectedProductGroup

    private val _products = MutableStateFlow(emptyList<Product>())
    val products: StateFlow<List<Product>> = _products

    private val _cart = MutableStateFlow(Cart.empty())
    val cart: StateFlow<Cart> = _cart

    fun selectProductGroup(group: ProductGroup) {
        _selectedProductGroup.value = group
        _products.value = productManager.getProductByGroup(group)
    }

    fun addToCart(product: Product) {
        _cart.value = _cart.value.copy(products = _cart.value.getAll() + product)
    }

    fun buy() {
        // Logic to handle checkout, e.g., processing payment, clearing cart, etc.
        _cart.value = Cart.empty()
    }
}