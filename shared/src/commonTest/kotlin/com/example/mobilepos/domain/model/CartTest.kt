package com.example.mobilepos.domain.model

import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class CartTest {

    private lateinit var sut: Cart

    @BeforeTest
    fun setup() {
        sut = Cart.empty()
    }

    @Test
    fun `add product to cart should increase product list size`() {
        val product = Product(name = "Test Product", price = 10.0)

        sut = sut.add(product)

        assertEquals(1, sut.products.size)
        assertEquals(product, sut.products[0])
    }

    @Test
    fun `remove product from cart should decrease product list size`() {
        //Arrange
        val product = Product(name = "Test Product", price = 10.0)
        sut = Cart.empty().add(product)

        // Act
        sut = sut.remove(product)

        // Assert
        assertTrue(sut.products.isEmpty())
    }

    @Test
    fun `removing a product not in the cart should not affect the cart`() {
        // Arrange
        val cartProduct = Product(name = "Product 1", price = 10.0)
        val notACartProduct = Product(name = "Product 2", price = 20.0)
        sut = sut.add(cartProduct)

        // Act
        sut = sut.remove(notACartProduct)

        // Assert
        assertEquals(1, sut.products.size)
        assertEquals(cartProduct, sut.products[0])
    }

    @Test
    fun `getAll should return all products in the cart`() {
        // Arrange
        val product1 = Product(name = "Product 1", price = 10.0)
        val product2 = Product(name = "Product 2", price = 20.0)
        sut = sut.add(product1).add(product2)

        // Act
        val allProducts = sut.getAll()

        // Assert
        assertEquals(2, allProducts.size)
        assertTrue(allProducts.containsAll(listOf(product1, product2)))
    }

    @Test
    fun `totalPrice should return the sum of all product prices`() {
        // Arrange
        val product1 = Product(name = "Product 1", price = 10.0)
        val product2 = Product(name = "Product 2", price = 20.0)
        sut = sut.add(product1).add(product2)

        // Act
        val totalPrice = sut.totalPrice()

        // Assert
        assertEquals(30.0, totalPrice)
    }

    @Test
    fun `pay should empty the cart and increment the order number`() {
        // Arrange
        val product = Product(name = "Product 1", price = 10.0)
        sut = sut.add(product)

        // Act
        val newCart = sut.pay()

        // Assert
        assertTrue(newCart.products.isEmpty())
        assertEquals(sut.orderNumber + 1, newCart.orderNumber)
    }
}