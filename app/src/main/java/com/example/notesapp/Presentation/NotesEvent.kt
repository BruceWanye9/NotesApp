package com.example.notesapp.Presentation

import com.example.notesapp.data.Note

sealed interface NotesEvent {
     object SortNotes:NotesEvent
     data class DeleteNote(var note: Note):NotesEvent
    data class SaveNote
        (var title:String,
        var disp:String)
        :NotesEvent
}