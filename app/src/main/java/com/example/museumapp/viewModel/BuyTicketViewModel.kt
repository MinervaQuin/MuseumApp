package com.example.museumapp.viewModel


import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.museumapp.model.resources.TicketType
import com.example.museumapp.model.resources.TicketTypes
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import java.util.stream.Collectors
import java.util.stream.Stream

import javax.inject.Inject

class BuyTicketViewModel @Inject constructor() : ViewModel() {
    val selectedDate = mutableStateOf(LocalDate.now())
    var selectedDayOfWeek: DayOfWeek? = null


    // Función para cargar datos del calendario
    fun loadCalendarData(startDate: LocalDate) {
        viewModelScope.launch {
            // Obtiene el lunes de la semana de startDate
            val mondayOfSelectedWeek = startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))

            // Si el lunes de la semana seleccionada es anterior a LocalDate.now(), selecciona LocalDate.now()
            // De lo contrario, selecciona el lunes de la semana seleccionada
            selectedDate.value = if (mondayOfSelectedWeek.isBefore(LocalDate.now())) {
                LocalDate.now()
            } else {
                mondayOfSelectedWeek
            }

            // Actualiza el día de la semana seleccionado
            selectedDayOfWeek = selectedDate.value?.dayOfWeek
        }
    }

    // Función para manejar la selección de una fecha
    fun onDateSelected(selectedDate: LocalDate) {
        viewModelScope.launch {
            // Si la fecha es anterior a LocalDate.now(), no se puede seleccionar
            if (selectedDate.isBefore(LocalDate.now())) {
                return@launch
            }

            // Selecciona la nueva fecha y actualiza el día de la semana seleccionado
            this@BuyTicketViewModel.selectedDate.value = selectedDate
            selectedDayOfWeek = selectedDate.dayOfWeek
        }
    }

    // Función para obtener fechas visibles
    fun getVisibleDates(): List<LocalDate> {
        // Puedes personalizar la lógica aquí según tus necesidades
        val startDate = selectedDate.value?.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            ?: LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endDate = startDate.plusDays(6)
        return getDatesBetween(startDate, endDate)
    }

    // Función para obtener fechas entre dos fechas
    private fun getDatesBetween(startDate: LocalDate, endDate: LocalDate): List<LocalDate> {
        val numOfDays = startDate.until(endDate).days
        return Stream.iterate(startDate) { date ->
            date.plusDays(1)
        }
            .limit((numOfDays + 1).toLong())
            .collect(Collectors.toList())
    }


    fun increaseTicketCount(ticketType: TicketType) {
        TicketTypes.increaseTicketCount(ticketType)
    }

    fun decreaseTicketCount(ticketType: TicketType) {
        TicketTypes.decreaseTicketCount(ticketType)
    }
}
