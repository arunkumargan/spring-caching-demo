package sample.cache

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

data class Message(
        val id: String,
        val payload: String,
        @field:JsonProperty("throw_exception") val throwException: Boolean,
        @field:JsonProperty("delay_by") val delayBy: Long
):Serializable {
    constructor(): this("", "", false, 0)

    companion object {
        private val serialVersionUID: Long = 40192849018491L
    }
}