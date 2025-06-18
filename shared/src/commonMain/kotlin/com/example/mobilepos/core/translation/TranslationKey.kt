package com.example.mobilepos.core.translation

import com.example.mobilepos.domain.model.ProductType

/// Shortcut for getting the localized string for the given TranslationKey
fun tr(key: TranslationKey): String {
    return LocalizationManager.getString(key)
}

/// Translation keys for the different languages.
///
/// The key values should match the keys in the translation JSON files.
enum class TranslationKey(val key: String) {
    PRODUCT_TYPE_BURGER("productTypeBurger"),
    PRODUCT_TYPE_SANDWICH("productTypeSandwich"),
    PRODUCT_TYPE_GRILLED_SANDWICH("productTypeGrilledSandwich"),
    PRODUCT_TYPE_KIDS_MENU("productTypeKidsMenu"),
    PRODUCT_TYPE_SALAD("productTypeSalad"),
    PRODUCT_TYPE_SIDE_ORDER("productTypeSideOrder"),
    ORDER_NO("orderNo"),
    DELETE_ALL("deleteAll"),
    TOTAL("total"),
    PAY("pay"),

}

val ProductType.translationKey: TranslationKey
    get() = when (this) {
        ProductType.BURGER -> TranslationKey.PRODUCT_TYPE_BURGER
        ProductType.SANDWICH -> TranslationKey.PRODUCT_TYPE_SANDWICH
        ProductType.GRILLED_SANDWICH -> TranslationKey.PRODUCT_TYPE_GRILLED_SANDWICH
        ProductType.KIDS_MENU -> TranslationKey.PRODUCT_TYPE_KIDS_MENU
        ProductType.SALAD -> TranslationKey.PRODUCT_TYPE_SALAD
        ProductType.SIDE_ORDER -> TranslationKey.PRODUCT_TYPE_SIDE_ORDER
    }
