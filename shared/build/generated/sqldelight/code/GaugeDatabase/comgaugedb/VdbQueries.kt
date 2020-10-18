package comgaugedb

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.Long
import kotlin.String

interface VdbQueries : Transacter {
  fun <T : Any> selectAllEvents(mapper: (
    eventName: String,
    eventType: String,
    attributes: String?,
    sessionId: Long,
    trackedAt: Long
  ) -> T): Query<T>

  fun selectAllEvents(): Query<Events>

  fun insertEvent(
    eventName: String,
    eventType: String,
    attributes: String?,
    sessionId: Long,
    trackedAt: Long
  )

  fun deleteAllEvents()
}
