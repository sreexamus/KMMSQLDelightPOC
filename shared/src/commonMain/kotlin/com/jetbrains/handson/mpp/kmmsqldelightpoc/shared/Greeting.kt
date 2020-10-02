package com.jetbrains.handson.mpp.kmmsqldelightpoc.shared


class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}
