package com.baha.todolist


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {
    @Insert
    suspend fun insertEvent(event: Event)

    @Query("SELECT * FROM event_table")
    fun getAllEvents(): Flow<List<Event>>

    @Delete
    suspend fun deleteEvent(event: Event)
}
