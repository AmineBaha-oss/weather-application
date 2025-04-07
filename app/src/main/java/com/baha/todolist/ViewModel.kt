package com.baha.todolist


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class EventViewModel(private val eventDao: EventDao) : ViewModel() {
    val events: Flow<List<Event>> = eventDao.getAllEvents()

    fun addEvent(name: String, details: String, date: String, time: String) {
        viewModelScope.launch {
            val event = Event(name = name, details = details, date = date, time = time)
            eventDao.insertEvent(event)
        }
    }

    fun deleteEvent(event: Event) {
        viewModelScope.launch {
            eventDao.deleteEvent(event)
        }
    }
}
