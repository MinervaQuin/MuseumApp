package com.example.museumapp.model.resources

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class TicketType(val name: String, val price: Double, var quantity: Int)

object TicketTypes {
    private val _ticketTypesList = MutableStateFlow(
        listOf(
            TicketType("Adulto", 15.99, 0),
            TicketType("Menor de 13 años", 9.99, 0),
            TicketType("Joven/Estudiante", 9.99, 0),
            TicketType("Mayor de 60 años", 4.99, 0)
        )
    )

    val ticketTypesList = _ticketTypesList.asStateFlow()

    // Función para aumentar la cantidad de una entrada específica
    fun increaseTicketCount(ticketType: TicketType) {
        val updatedList = _ticketTypesList.value.toMutableList()
        val index = updatedList.indexOf(ticketType)
        if (index != -1) {
            updatedList[index] = ticketType.copy(quantity = ticketType.quantity + 1)
            _ticketTypesList.value = updatedList
        }
    }

    // Función para disminuir la cantidad de una entrada específica
    fun decreaseTicketCount(ticketType: TicketType) {
        if (ticketType.quantity > 0) {
            val updatedList = _ticketTypesList.value.toMutableList()
            val index = updatedList.indexOf(ticketType)
            if (index != -1) {
                updatedList[index] = ticketType.copy(quantity = ticketType.quantity - 1)
                _ticketTypesList.value = updatedList
            }
        }
    }
}
