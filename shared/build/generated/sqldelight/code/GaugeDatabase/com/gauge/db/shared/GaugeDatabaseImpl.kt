package com.gauge.db.shared

import com.gauge.db.GaugeDatabase
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.internal.copyOnWriteList
import comgaugedb.Events
import comgaugedb.VdbQueries
import kotlin.Any
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<GaugeDatabase>.schema: SqlDriver.Schema
  get() = GaugeDatabaseImpl.Schema

internal fun KClass<GaugeDatabase>.newInstance(driver: SqlDriver): GaugeDatabase =
    GaugeDatabaseImpl(driver)

private class GaugeDatabaseImpl(
  driver: SqlDriver
) : TransacterImpl(driver), GaugeDatabase {
  override val vdbQueries: VdbQueriesImpl = VdbQueriesImpl(this, driver)

  object Schema : SqlDriver.Schema {
    override val version: Int
      get() = 1

    override fun create(driver: SqlDriver) {
      driver.execute(null, """
          |CREATE TABLE Events (
          |eventName TEXT NOT NULL,
          |eventType TEXT NOT NULL,
          |attributes TEXT,
          |sessionId INTEGER NOT NULL,
          |trackedAt INTEGER NOT NULL
          |)
          """.trimMargin(), 0)
    }

    override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ) {
    }
  }
}

private class VdbQueriesImpl(
  private val database: GaugeDatabaseImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), VdbQueries {
  internal val selectAllEvents: MutableList<Query<*>> = copyOnWriteList()

  override fun <T : Any> selectAllEvents(mapper: (
    eventName: String,
    eventType: String,
    attributes: String?,
    sessionId: Long,
    trackedAt: Long
  ) -> T): Query<T> = Query(-1889163904, selectAllEvents, driver, "vdb.sq", "selectAllEvents",
      "SELECT * FROM Events") { cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1)!!,
      cursor.getString(2),
      cursor.getLong(3)!!,
      cursor.getLong(4)!!
    )
  }

  override fun selectAllEvents(): Query<Events> = selectAllEvents { eventName, eventType,
      attributes, sessionId, trackedAt ->
    comgaugedb.Events(
      eventName,
      eventType,
      attributes,
      sessionId,
      trackedAt
    )
  }

  override fun insertEvent(
    eventName: String,
    eventType: String,
    attributes: String?,
    sessionId: Long,
    trackedAt: Long
  ) {
    driver.execute(-58363549,
        """INSERT OR REPLACE INTO Events(eventName, eventType, attributes, sessionId, trackedAt)VALUES(?,?,?,?,?)""",
        5) {
      bindString(1, eventName)
      bindString(2, eventType)
      bindString(3, attributes)
      bindLong(4, sessionId)
      bindLong(5, trackedAt)
    }
    notifyQueries(-58363549, {database.vdbQueries.selectAllEvents})
  }

  override fun deleteAllEvents() {
    driver.execute(-563304015, """DELETE FROM Events""", 0)
    notifyQueries(-563304015, {database.vdbQueries.selectAllEvents})
  }
}
