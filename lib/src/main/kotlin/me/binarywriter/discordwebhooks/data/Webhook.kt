package me.binarywriter.discordwebhooks.data

import kotlinx.serialization.*
import kotlinx.serialization.json.Json
import me.binarywriter.discordwebhooks.http.HttpClient
import java.io.File

@Serializable
data class Webhook(
    var username: String? = null,
    var content: String? = null,
    @SerialName("avatar_url")
    var avatarUrl: String? = null,
    var embeds: MutableList<Embed> = mutableListOf(),
    var tts: Boolean = false,
    @Transient
    var files: Map<String, File> = emptyMap()
) {
    companion object {
        private val jsonInstance = Json { prettyPrint = true }

        /**
         * Returns the webhook created from json
         */
        fun fromJson(json: String) = jsonInstance.decodeFromString<Webhook>(json)
    }

    /**
     * Json representation of the [Webhook]
     */
    val json
        get() = jsonInstance.encodeToString(this)

    constructor(init: Webhook.() -> Unit) : this() {
        this.init()
    }

    /**
     * Adds [Embed] to [embeds]
     */
    fun embed(init: Embed.() -> Unit) {
        embeds.add(Embed().apply { this.init() })
    }

    /**
     * Sends [Webhook] to [url]
     */
    fun send(url: String) =
        try {
            HttpClient.post(url, json, files.mapValues { it.value.readBytes() }).code in 200..204
        } catch (ex: Exception) {
            false
        }
}