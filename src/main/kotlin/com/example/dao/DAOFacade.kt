package com.example.dao

import com.example.models.Customer

interface DAOFacade {
    suspend fun allCustomer() : List<Customer>
    suspend fun getCustomer(id: String) : Customer?
    suspend fun addCustomer(customer: Customer) : Customer?
    suspend fun editCustomer(customer: Customer) : Boolean
    suspend fun removeCustomer(id: String) : Boolean
}