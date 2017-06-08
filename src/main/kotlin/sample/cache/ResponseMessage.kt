package sample.cache

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class ResponseMessage(
        val id: String,
        @field:JsonProperty("received_payload") val receivedPayload: String,
        val ack: String
): Serializable {
    constructor(): this("", "", "")

    companion object {
        private val serialVersionUID: Long = 1421431324L
    }
}