package com.example.routes

import com.example.dao.DAOFacade
import com.example.dao.DAOFacadeImpl
import com.example.models.Customer
import com.example.models.customerStorage
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.runBlocking

fun Route.customerRouting() {
    val dao: DAOFacade = DAOFacadeImpl()

    route("/customer") {
        get {
            if (customerStorage.isNotEmpty()) {
                call.respond(dao.allCustomer())
            } else {
                call.respondText("No customer found", status = HttpStatusCode.NotFound)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )

            val customer = dao.getCustomer(call.parameters["id"]!!) ?: return@get call.respondText(
                "No customer with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(customer)

        }
        post {
            val customer = call.receive<Customer>()
            dao.addCustomer(customer)
            call.respondText("Customer stored correctly", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if (dao.removeCustomer(id)) {
                call.respondText("Customer removed correctly", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not found", status = HttpStatusCode.NotFound)
            }
        }
    }
}