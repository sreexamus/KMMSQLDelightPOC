package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared

import com.squareup.sqldelight.db.SqlDriver
import platform.UIKit.UIDevice
import com.gauge.db.GaugeDatabase
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class Platform actual constructor() {
    actual val platform: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(GaugeDatabase.Schema, "GuageDB.db")
    }
}