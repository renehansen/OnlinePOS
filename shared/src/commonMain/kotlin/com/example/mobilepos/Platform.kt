package com.example.mobilepos

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform