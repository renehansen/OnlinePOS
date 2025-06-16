package com.example.mobilepos.core.ui.color.util

import com.example.mobilepos.core.ui.color.POSColor
import com.example.mobilepos.domain.model.ProductType

object ProductTypeColorMapper {
    fun getColorForProductType(productType: ProductType): Int {
        return when (productType) {
            ProductType.BURGER -> POSColor.AMBER
            ProductType.SANDWICH, ProductType.GRILLED_SANDWICH -> POSColor.ORANGE_RED
            ProductType.KIDS_MENU -> POSColor.LIGHT_BLUE
            ProductType.SALAD -> POSColor.LIGHT_GREEN
            ProductType.SIDE_ORDER -> POSColor.LIGHT_GREY
        }
    }
}