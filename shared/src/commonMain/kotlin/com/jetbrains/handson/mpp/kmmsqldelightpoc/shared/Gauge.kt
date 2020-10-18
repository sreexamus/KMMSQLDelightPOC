package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared

object Gauge {
    fun logEvent(name: String, attributes: HashMap<String, String>?) = insertEvent(name, attributes)
}