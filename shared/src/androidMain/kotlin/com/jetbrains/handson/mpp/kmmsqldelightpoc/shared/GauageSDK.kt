package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared

import android.content.Context
import com.gauge.db.GaugeDatabase

actual class GauageSDK {
    companion object Factory {
        var appContext: Context? = null

        fun initialize(context: Context)  {
            GauageSDK.appContext = context
            // Add any other code for initialization
        }
    }
}

