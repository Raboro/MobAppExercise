package de.dhbw.mobapp.briefnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import de.dhbw.mobapp.briefnote.database.NoteDatabase
import de.dhbw.mobapp.briefnote.ui.theme.BriefNoteTheme
import de.dhbw.mobapp.briefnote.view.main.MainView
import de.dhbw.mobapp.briefnote.view.main.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BriefNoteTheme {
                BriefNote()
            }
        }
    }
}

@Composable
fun BriefNote() {
    val viewModel = viewModel<MainViewModel>()
    viewModel.initialize(NoteDatabase.getInstance(LocalContext.current))

    MainView(viewModel = viewModel)
}

