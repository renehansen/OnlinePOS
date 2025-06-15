package com.example.mobilepos.domain

import com.example.mobilepos.domain.model.Product
import com.example.mobilepos.domain.model.ProductGroup

/// ProductRepository defines the contract for retrieving product data.
interface ProductRepository {
    /// Retrieves all products for the given product group.
    fun getProductsByGroup(group: ProductGroup): List<Product>

    /// Retrieves all available product groups.
    fun getAllProductGroups(): List<ProductGroup>
}