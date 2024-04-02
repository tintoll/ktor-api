package com.example.dao

import com.example.dao.DatabaseSingleton.dbQuery
import com.example.models.Customer
import com.example.models.Customers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class DAOFacadeImpl : DAOFacade {

    private fun resultRowToCustomer(row:ResultRow) = Customer(
        id = row[Customers.id],
        firstName = row[Customers.firstName],
        lastName = row[Customers.lastName],
        email = row[Customers.email]
    )

    override suspend fun allCustomer(): List<Customer> = dbQuery {
        Customers.selectAll().map(::resultRowToCustomer)
    }

    override suspend fun getCustomer(id: String): Customer? = dbQuery {
        Customers
            .select { Customers.id eq id }
            .map(::resultRowToCustomer)
            .singleOrNull()
    }

    override suspend fun addCustomer(customer: Customer): Customer? = dbQuery {
        val insertStatement = Customers.insert {
            it[Customers.id] = customer.id
            it[Customers.firstName] = customer.firstName
            it[Customers.lastName] = customer.lastName
            it[Customers.email] = customer.email
        }

        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToCustomer)
    }

    override suspend fun editCustomer(customer: Customer):Boolean = dbQuery {
        Customers
            .update({ Customers.id eq customer.id }) {
                it[Customers.firstName] = customer.firstName
                it[Customers.lastName] = customer.lastName
                it[Customers.email] = customer.email
            } > 0
    }

    override suspend fun removeCustomer(id: String): Boolean = dbQuery {
        Customers.deleteWhere { Customers.id eq id } > 0
    }
}