package com.example.mobilepos.core.ui.color.util

import com.example.mobilepos.domain.model.ProductType
import org.jetbrains.skia.Color

object ProductTypeColorMapper {
    fun getColorForProductType(productType: ProductType): Int {
        return when (productType) {
            ProductType.BURGER -> Color..YELLOW
            ProductType.SANDWICH, ProductType.GRILLED_SANDWICH -> Color.RED
            ProductType.KIDS_MENU -> Color.BLUE
            ProductType.SALAD -> Color.GREEN
            ProductType.SIDE_ORDER -> Color.CYAN
        }
    }
}