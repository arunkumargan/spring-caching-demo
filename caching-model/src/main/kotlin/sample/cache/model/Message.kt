package sample.cache.model

import java.io.Serializable

data class Message(
        var id: String,
        var payload: String,
        var fail: Boolean,
        var delay: Long
): Serializable {
    constructor(): this("", "", false, 0)

    companion object {
        private val serialVersionUID: Long = 40192849018491L
    }
}