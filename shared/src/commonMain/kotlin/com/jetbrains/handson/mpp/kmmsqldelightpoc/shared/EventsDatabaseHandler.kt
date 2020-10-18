package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared
import kotlinx.serialization.json.Json;

expect fun insertEvent(name: String, attributes: HashMap<String, String>?)

