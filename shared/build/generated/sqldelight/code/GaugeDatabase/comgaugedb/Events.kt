package comgaugedb

import kotlin.Long
import kotlin.String

data class Events(
  val eventName: String,
  val eventType: String,
  val attributes: String?,
  val sessionId: Long,
  val trackedAt: Long
) {
  override fun toString(): String = """
  |Events [
  |  eventName: $eventName
  |  eventType: $eventType
  |  attributes: $attributes
  |  sessionId: $sessionId
  |  trackedAt: $trackedAt
  |]
  """.trimMargin()
}
