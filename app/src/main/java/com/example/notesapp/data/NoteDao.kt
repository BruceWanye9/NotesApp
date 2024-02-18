package com.example.notesapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    //Delete
    // sort
    @Upsert // Upsert used as both update or insert singly
    suspend fun upsertNote(note: Note)
 @Delete
 suspend fun deleteNote(note: Note)

 @Query("SELECT * FROM Note ORDER BY title ASC")
 fun getOrderedByTitle():Flow<List<Note>>

 @Query("SELECT * FROM Note ORDER BY  dateAdded")
 fun getOrderedByDateAddedBy(): Flow<List<Note>>

}