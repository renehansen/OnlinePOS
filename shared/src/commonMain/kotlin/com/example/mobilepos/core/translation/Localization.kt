package com.example.mobilepos.core.translation

/// Defines the supported languages in the application.
enum class Language(val code: String) {
    ENGLISH("en"),
    SPANISH("es") // Not implement due to poor Spanish skills :D
}

/// Gets the localized string for the given TranslationKey based on the current language.
fun tr(key: TranslationKey): String {
    return Localization.getString(key)
}

/// Handles localization for the supported languages.
object Localization {
    /// The current language for localization. Default is English.
    var language = Language.ENGLISH

    /// Gets the localized string for the given TranslationKey based on the current language.
    fun getString(key: TranslationKey): String {
        return translations[language]?.get(key) ?: key.name
    }

    private val translations = mapOf(
        Language.ENGLISH to mapOf(
            TranslationKey.WELCOME_MESSAGE to "Welcome",
            TranslationKey.BURGER to "Burger",
            TranslationKey.GRILLED_SANDWICH to "Grilled Sandwich"
        )
    )
}