package com.example.mobilepos.domain

import com.example.mobilepos.domain.model.Product
import com.example.mobilepos.domain.model.ProductGroup

/// Handles domain logic related to products.
///
/// Currently, it just retrieves products directly from the repository,
/// but can be extended to include actual logic
class ProductManager(private val productRepository: ProductRepository) {
    /// Retrieves all products for the given product group.
    fun getProductByGroup(group: ProductGroup): List<Product> =
        productRepository.getProductsByGroup(group)


    /// Retrieves all available product groups.
    fun getAllProductGroups(): List<ProductGroup> =
        productRepository.getAllProductGroups()
}