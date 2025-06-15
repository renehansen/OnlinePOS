package com.example.mobilepos.core.translation

enum class TranslationKey {
    WELCOME_MESSAGE,
    BURGER,
    GRILLED_SANDWICH
}

object ProductTypeMapper {
    private val productTypeToTranslationKey = mapOf<ProductType, TranslationKey>(
        ProductType.BURGER to TranslationKey.BURGER,
        ProductType.SANDWICH to TranslationKey.SANDWICH,
        ProductType.GRILLED_SANDWICH to TranslationKey.GRILLED_SANDWICH,
        ProductType.KIDS_MENU to TranslationKey.KIDS_MENU,
        ProductType.SALAD to TranslationKey.SALAD,
        ProductType.SIDE_ORDER to TranslationKey.SIDE_ORDER
    )

    fun map(productType: ProductType): TranslationKey {
        return productTypeToTranslationKey[productType]
            ?: throw IllegalArgumentException("No translation mapping found for ProductType: $productType")
    }
}