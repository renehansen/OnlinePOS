package com.example.mobilepos.core.translation

actual fun loadResource(resourceName: String): String? {
    return ResourceLoader::class.java.classLoader?.getResource(resourceName)?.readText()
}