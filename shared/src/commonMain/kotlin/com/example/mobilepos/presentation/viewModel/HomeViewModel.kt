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
        _cart.value = _cart.value.add(product)
    }

    fun removeFromCart(product: Product) {
        _cart.value = _cart.value.remove(product)
    }

    fun clearCart() {
        _cart.value = Cart.empty()
    }

    fun pay() {
        _cart.value = _cart.value.pay()
    }
}