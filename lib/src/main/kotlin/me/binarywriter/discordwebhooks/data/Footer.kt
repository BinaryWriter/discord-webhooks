package me.binarywriter.discordwebhooks.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Footer(
    var text: String? = null,
    @SerialName("icon_url")
    var iconUrl: String? = null
) {
    constructor(init: Footer.() -> Unit) : this() {
        this.init()
    }
}