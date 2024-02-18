package com.example.notesapp.Presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.Note
import com.example.notesapp.data.NoteDao
import com.example.notesapp.data.NoteDataBase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteViewModel(
    private var dao:NoteDao
) : ViewModel(){
private var isSortedByDateAdded=MutableStateFlow(true)
    @OptIn(ExperimentalCoroutinesApi::class)
   private var notes=isSortedByDateAdded.flatMapLatest {
       if(it){
           dao.getOrderedByDateAddedBy()
       }else
       {
           dao.getOrderedByTitle()
       }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
 var _state = MutableStateFlow(NoteState())
    var state= combine(_state,isSortedByDateAdded,notes){
        state,isSortedByDateAdded,notes->
        state.copy(
            notes=notes
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),NoteState())
    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.DeleteNote -> {
               viewModelScope.launch {
                   dao.deleteNote(event.note)
               }
            }
            is NotesEvent.SaveNote ->
            {
                val note= Note(
                    title=_state.value.title.value,
                    disp = _state.value.disp.value,
                    dateAdded = System.currentTimeMillis()
                )
                viewModelScope.launch {
 dao.upsertNote(note=note)
                }
                _state.update {
                    NoteState(
                        title = mutableStateOf(""),
                        disp = mutableStateOf("")
                    )
                }
            }

            is NotesEvent.SortNotes -> {
                isSortedByDateAdded.value = !isSortedByDateAdded
                    .value
            }
        }
    }

}