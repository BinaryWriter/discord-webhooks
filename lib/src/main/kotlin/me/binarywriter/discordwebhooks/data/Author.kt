package me.binarywriter.discordwebhooks.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    var name: String? = null,
    var url: String? = null,
    @SerialName("icon_url")
    var iconUrl: String? = null
) {
    constructor(init: Author.() -> Unit) : this() {
        this.init()
    }
}