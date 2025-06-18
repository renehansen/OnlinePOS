// Can't find mockk...
/*
package com.example.mobilepos.domain.model

import com.example.mobilepos.domain.ProductManager
import com.example.mobilepos.domain.ProductRepository
import io.mockk.every
import io.mockk.mockk
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductManagerTest {

    private lateinit var mockRepository: mockk<ProductRepository>
    private lateinit var sut: ProductManager

    @BeforeTest
    fun setup() {
        mockRepository = mockk<ProductRepository>()
        sut = ProductManager(mockRepository)
    }

    @Test
    fun `getProductByGroup should return products for the given group`() {
        // Arrange
        val products = listOf(
            Product(name = "Fake burger", price = 1000.0),
            Product(name = "Air burger", price = 500.0)
        )

        val group = ProductGroup(type = ProductType.BURGER, products)
        every { mockRepository.getProductsByGroup(group) } returns products

        // Act
        val result = sut.getProductByGroup(group)

        // Assert
        assertEquals(products, result)
    }

    @Test
    fun `getAllProductGroups should return all available product groups`() {
        // Arrange
        val groups = listOf(
            ProductGroup(type = ProductType.BURGER),
            ProductGroup(type = ProductType.SANDWICH)
        )
        every { mockRepository.getAllProductGroups() } returns groups

        // Act
        val result = sut.getAllProductGroups()

        // Assert
        assertEquals(groups, result)
    }
}
*/
