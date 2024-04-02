package com.example

import com.example.dao.DatabaseSingleton
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseSingleton.init()
    configureSerialization()
    configureRouting()
    routing {

    }
}
