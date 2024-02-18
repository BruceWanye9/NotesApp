package com.example.notesapp.Presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.notesapp.data.Note
import kotlinx.coroutines.flow.MutableStateFlow

data class NoteState (
    val notes :List<Note> = emptyList(),
    val title:MutableState<String> = mutableStateOf(""),
    val disp:MutableState<String> = mutableStateOf("")
)