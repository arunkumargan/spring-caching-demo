package sample.cache.model

import java.io.Serializable

data class ResponseMessage(
        var id: String,
        var received: String,
        var ack: String
): java.io.Serializable {
    constructor(): this("", "", "")

    companion object {
        private val serialVersionUID: Long = 1421431324L
    }
}