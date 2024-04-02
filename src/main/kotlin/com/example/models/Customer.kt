package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Customer (
    val id : String,
    val firstName: String,
    val lastName: String,
    val email: String,
)

object Customers : Table() {
    val id = varchar("id", 10)
    val firstName = varchar("firstName", 50)
    val lastName = varchar("lastName", 50)
    val email = varchar("email", 50)

    override val primaryKey = PrimaryKey(id)
}


val customerStorage = mutableListOf<Customer>()