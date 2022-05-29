package me.binarywriter.discordwebhooks.data

import kotlinx.serialization.Serializable

@Serializable
data class Image(
    var url: String? = null
) {
    constructor(init: Image.() -> Unit) : this() {
        this.init()
    }
}