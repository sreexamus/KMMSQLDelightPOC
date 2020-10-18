package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared
import com.gauge.db.GaugeDatabase

    actual fun insertEvent(name: String, attributes: HashMap<String, String>?) {
        val factory = DatabaseDriverFactory()
        val driver = factory.createDriver()
        val db = GaugeDatabase(driver)
        // insert event to Gauge DB
        try {
            db.vdbQueries.insertEvent(eventName = name, eventType = "auto",attributes = "123234232", sessionId = 4323, trackedAt = 433)
        }
        catch (e: Exception) {
            throw Exception("Event table insertion $e")
        }

        // get list of events from Gauge DB
        val listOfEvents = db.vdbQueries.selectAllEvents().executeAsList()
        print(listOfEvents)
    }

