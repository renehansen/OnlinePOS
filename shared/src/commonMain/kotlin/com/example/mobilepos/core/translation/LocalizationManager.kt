package com.example.mobilepos.core.translation

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/// Defines the supported languages in the application.
enum class Language(val code: String) {
    ENGLISH("en"),
    // Add more languages as needed..
}

object LocalizationManager {
    /// The current language for localization. Default is English.
    var language = Language.ENGLISH

    // Quick hack until the right solution (see below) is properly implemented.
    private val translations = mapOf(
        "productTypeBurger" to "Burger",
        "productTypeSandwich" to "Sandwich",
        "productTypeGrilledSandwich" to "Grilled Sandwich",
        "productTypeKidsMenu" to "Kids Menu",
        "productTypeSalad" to "Salad",
        "productTypeSideOrder" to "Side Order",
        "buy" to "Buy!",
        "placeholder" to "Cancel"
    )


    /// Gets the localized string for the given TranslationKey based on the current language.
    fun getString(key: TranslationKey): String {
        return translations[key.key] ?: key.name
    }
}

// TODO(RHA): Don't have time to properly implement this right now, but the general idea is to load translations from a JSON file based on the current language.
/*
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

