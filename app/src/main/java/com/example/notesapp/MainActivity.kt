package com.example.notesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.example.notesapp.Presentation.AddNoteScreen
import com.example.notesapp.Presentation.NoteScreen
import com.example.notesapp.Presentation.NoteViewModel
import com.example.notesapp.data.NoteDataBase
import com.example.notesapp.ui.theme.NotesAppTheme

class MainActivity : ComponentActivity() {
    private val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            NoteDataBase::class.java,
            "notes.dp"
        ).build()
    }

    private val viewModel by viewModels<NoteViewModel> {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val state by viewModel.state.collectAsState()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "NoteScreen") {
                        composable("NoteScreen") {
                            NoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                        }
                        composable("AddNoteScreen") {
                            AddNoteScreen(state = state, navController = navController, onEvent = viewModel::onEvent)
                    }
                }
            }
        }
    }
}





