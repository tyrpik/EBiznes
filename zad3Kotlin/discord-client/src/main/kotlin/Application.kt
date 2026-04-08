package com.example

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class DiscordMessage(val content: String)

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    val webhookUrl = "https://discord.com/api/webhooks/1491431052600934541/P17RAmdUcHr-vUf_N1MfRbSI7AKNYVYpB4LIT9a8Kj5M9fXhVR6VeKtQZBlWR4XuUx7b"

    println("Wysyłam wiadomość na Discorda...")

    // Wysłanie zapytania POST do Discorda
    try {
        val response = client.post(webhookUrl) {
            contentType(ContentType.Application.Json)
            setBody(DiscordMessage("Siemanko!"))
        }

        if (response.status.isSuccess()) {
            println("Wiadomość została wysłana pomyślnie!")
        } else {
            println("Błąd podczas wysyłania: ${response.status}")
        }
    } catch (e: Exception) {
        println("Wystąpił wyjątek: ${e.message}")
    } finally {
        client.close()
    }
}
