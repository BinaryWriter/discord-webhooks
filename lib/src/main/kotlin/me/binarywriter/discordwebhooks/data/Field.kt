package me.binarywriter.discordwebhooks.data

import kotlinx.serialization.Serializable

@Serializable
data class Field(
    var name: String? = null,
    var value: String? = null,
    var inline: Boolean = false
) {
    constructor(init: Field.() -> Unit) : this() {
        this.init()
    }
}