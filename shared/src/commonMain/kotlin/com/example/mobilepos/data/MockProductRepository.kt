package com.example.mobilepos.domain

import com.example.mobilepos.domain.model.Product
import com.example.mobilepos.domain.model.ProductGroup
import com.example.mobilepos.domain.model.ProductType

class MockProductRepository : ProductRepository {

    private val burgers = listOf(
        Product(name = "Big Mac", price = 60.0),
        Product(name = "Double Cheeseburger", price = 35.0),
        Product(name = "McChicken", price = 54.0),
        Product(name = "Big Tasty Bacon", price = 72.0),
        Product(name = "Homestyle Pepper & Bacon", price = 84.0),
        Product(name = "McFeast", price = 64.0),
        Product(name = "Quarter Pounder", price = 60.0),
    )

    private val sandwiches = listOf(
        Product(name = "Chicken Sandwich", price = 45.0),
        Product(name = "Fish Sandwich", price = 50.0),
        Product(name = "Veggie Delight", price = 30.0),
        Product(name = "Cheese Sandwich", price = 35.0),
        Product(name = "BLT Sandwich", price = 40.0),
        Product(name = "Club Sandwich", price = 55.0),
        Product(name = "Tuna Sandwich", price = 50.0),
    )

    private val grilledSandwiches = listOf(
        Product(name = "Grilled Chicken Sandwich", price = 55.0),
        Product(name = "Grilled Veggie Sandwich", price = 40.0),
        Product(name = "Grilled Cheese Sandwich", price = 35.0),
    )

    private val kidsMenu = listOf(
        Product(name = "Happy Meal", price = 40.0),
        Product(name = "Kids Chicken Nuggets", price = 30.0),
        Product(name = "Kids Cheeseburger", price = 35.0),
        Product(name = "Kids Fish Sandwich", price = 45.0),
        Product(name = "Kids Veggie Wrap", price = 25.0)
    )

    private val salads = listOf(
        Product(name = "Caesar Salad", price = 35.0),
        Product(name = "Garden Salad", price = 30.0),
        Product(name = "Greek Salad", price = 40.0),
        Product(name = "Chicken Salad", price = 50.0),
        Product(name = "Tuna Salad", price = 45.0),
        Product(name = "Fruit Salad", price = 25.0)
    )

    private val sideOrders = listOf(
        Product(name = "French Fries", price = 20.0),
        Product(name = "Onion Rings", price = 25.0),
        Product(name = "Mozzarella Sticks", price = 30.0),
        Product(name = "Side Salad", price = 15.0),
        Product(name = "Garlic Bread", price = 18.0),
        Product(name = "Coleslaw", price = 12.0),
        Product(name = "Potato Wedges", price = 22.0)
    )

    private val burgerGroup = ProductGroup(
        type = ProductType.BURGER,
        products = burgers
    )

    private val sandwichGroup = ProductGroup(
        type = ProductType.SANDWICH,
        products = sandwiches
    )

    private val grilledSandwichGroup = ProductGroup(
        type = ProductType.GRILLED_SANDWICH,
        products = grilledSandwiches
    )

    private val kidsMenuGroup = ProductGroup(
        type = ProductType.KIDS_MENU,
        products = kidsMenu
    )

    private val saladGroup = ProductGroup(
        type = ProductType.SALAD,
        products = salads
    )

    private val sideOrderGroup = ProductGroup(
        type = ProductType.SIDE_ORDER,
        products = sideOrders
    )

    private val mockProductGroups = listOf(
        burgerGroup,
        sandwichGroup,
        grilledSandwichGroup,
        kidsMenuGroup,
        saladGroup,
        sideOrderGroup
    )

    override fun getProductsByGroup(group: ProductGroup): List<Product> {
        return mockProductGroups.find { it.type == group.type }?.products ?: emptyList()
    }

    override fun getAllProductGroups(): List<ProductGroup> {
        return mockProductGroups
    }
}