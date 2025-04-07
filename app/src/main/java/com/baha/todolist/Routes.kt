package com.baha.todolist


sealed class Routes(val route: String) {
    object AddEvent : Routes("add_event")
    object EventList : Routes("event_list")
}
