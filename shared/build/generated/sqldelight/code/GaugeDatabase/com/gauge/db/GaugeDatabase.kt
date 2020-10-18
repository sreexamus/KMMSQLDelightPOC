package com.gauge.db

import com.gauge.db.shared.newInstance
import com.gauge.db.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver
import comgaugedb.VdbQueries

interface GaugeDatabase : Transacter {
  val vdbQueries: VdbQueries

  companion object {
    val Schema: SqlDriver.Schema
      get() = GaugeDatabase::class.schema

    operator fun invoke(driver: SqlDriver): GaugeDatabase =
        GaugeDatabase::class.newInstance(driver)}
}
