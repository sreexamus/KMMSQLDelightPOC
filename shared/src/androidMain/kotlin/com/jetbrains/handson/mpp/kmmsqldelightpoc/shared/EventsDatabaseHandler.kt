package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared

import android.content.Context
import com.gauge.db.GaugeDatabase
import com.jetbrains.handson.mpp.kmmsqldelightpoc.shared.GauageSDK

    actual fun insertEvent(name: String, attributes: HashMap<String, String>?) {
        var context = GauageSDK.appContext
        val factory = DatabaseDriverFactory(
            context = context
        )
        val driver = factory.createDriver()
        val db = GaugeDatabase(driver)
        try {
            db.vdbQueries.insertEvent(eventName = name, eventType = "auto",attributes = "123234232", sessionId = 4323, trackedAt = 433)
        }
        catch (e: java.lang.Exception) {
            throw Exception("Event table insertion $e")
        }

        // get list of events from Gauge DB
        val listOfEvents = db.vdbQueries.selectAllEvents().executeAsList()
        print(listOfEvents)
    }



