package com.example.mobilepos.core.translation

/// Handles localization for the supported languages.
///
/// This one is just a quick hack until the right solution (see below) is properly implemented.
object LocalizationManager {
    private val translations = mapOf(
        "productTypeBurger" to "Burger",
        "productTypeSandwich" to "Sandwich",
        "productTypeGrilledSandwich" to "Grilled Sandwich",
        "productTypeKidsMenu" to "Kids Menu",
        "productTypeSalad" to "Salad",
        "productTypeSideOrder" to "Side Order",
        "pay" to "Pay",
        "orderNo" to "Order No.",
        "deleteAll" to "Delete all",
        "total" to "Total"
    )


    /// Gets the localized string for the given TranslationKey based on the current language.
    fun getString(key: TranslationKey): String {
        return translations[key.key] ?: key.name
    }
}

// TODO(RHA): Don't have time to properly implement this right now, but the general idea is to load translations from a JSON file based on the current language.
/*
/// Defines the supported languages in the application.
enum class Language(val code: String) {
    ENGLISH("en"),
    // Add more languages as needed..
}

/// Handles localization for the supported languages.
object LocalizationManager {
    /// The current language for localization. Default is English.
    var language = Language.ENGLISH

    private var translations: JsonObject = JsonObject(emptyMap())

    init {
        loadTranslations()
    }

    /// Loads the translations for the current language from the language JSON file.
    private
    fun loadTranslations() {
        val resourceName = "translation/${language.code}.json"
        val resource = loadResource(resourceName)
        translations = Json.parseToJsonElement(resource ?: "{}").jsonObject
    }


    /// Gets the localized string for the given TranslationKey based on the current language.
    fun getString(key: TranslationKey): String {
        return translations[key.key]?.jsonPrimitive?.content ?: key.name
    }
}
*/

