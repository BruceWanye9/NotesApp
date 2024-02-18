package com.example.notesapp.Presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.time.format.TextStyle

@Composable
fun AddNoteScreen(state: NoteState,navcontroller:NavController,
    onEvent: (NotesEvent)->Unit){
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            onEvent(
                NotesEvent.SaveNote(
                    title = state.title.value,
                    disp = state.disp.value
                )
            )
        }) {
          Icon(imageVector = Icons.Rounded.Check, contentDescription ="FlotingIcon" )
        }
    }) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()) {
           OutlinedTextField(value = state.title.value, onValueChange = {
             state.title.value=it
}, modifier = Modifier
               .fillMaxSize()
               .padding(16.dp),
               placeholder = {
                   Text(text = "Title")
               }, textStyle = androidx.compose.ui.text.TextStyle(
                   fontWeight = FontWeight.SemiBold,
                   fontSize = 18.sp
               )
               )
            OutlinedTextField(value = state.disp.value, onValueChange = {
                state.disp.value=it
            }, modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                placeholder = {
                    Text(text = "Description")
                }, textStyle = androidx.compose.ui.text.TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            )
        }
    }

}