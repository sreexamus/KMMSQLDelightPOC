package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared

import com.squareup.sqldelight.db.SqlDriver
import com.gauge.db.GaugeDatabase

expect class Platform() {
    val platform: String
}

expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}

fun createDb(dbDriverFactory: DatabaseDriverFactory): GaugeDatabase {
    val driver = dbDriverFactory.createDriver()

    return GaugeDatabase(driver)
}