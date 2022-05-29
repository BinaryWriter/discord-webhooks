package me.binarywriter.discordwebhooks.data

import kotlinx.serialization.Serializable
import me.binarywriter.discordwebhooks.serializers.ColorSerializer
import me.binarywriter.discordwebhooks.serializers.DateSerializer
import java.awt.Color
import java.util.*

@Serializable
data class Embed(
    var title: String? = null,
    var description: String? = null,
    var url: String? = null,
    @Serializable(with = ColorSerializer::class)
    var color: Color? = null,
    var fields: MutableList<Field> = mutableListOf(),
    var author: Author? = null,
    var footer: Footer? = null,
    @Serializable(with = DateSerializer::class)
    var timestamp: Date? = null,
    var image: Image? = null
) {
    constructor(init: Embed.() -> Unit) : this() {
        this.init()
    }

    /**
     * Sets [author] of [Embed]
     */
    fun author(init: Author.() -> Unit) {
        this.author = Author(init)
    }

    /**
     * Sets [footer] of [Embed]
     */
    fun footer(init: Footer.() -> Unit) {
        this.footer = Footer(init)
    }

    /**
     * Adds [Field] to [fields]
     */
    fun field(init: Field.() -> Unit) {
        fields.add(Field().apply { this.init() })
    }
}