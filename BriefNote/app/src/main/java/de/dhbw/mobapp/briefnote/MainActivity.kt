package de.dhbw.mobapp.briefnote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.dhbw.mobapp.briefnote.database.NoteDatabase
import de.dhbw.mobapp.briefnote.ui.theme.BriefNoteTheme
import de.dhbw.mobapp.briefnote.view.main.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BriefNoteTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    InputSection(innerPadding = innerPadding)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSection(innerPadding: PaddingValues) {
    val viewModel = viewModel<MainViewModel>()
    viewModel.initialize(NoteDatabase.getInstance(LocalContext.current))

    val buttonModifier = Modifier
        .padding(top = 10.dp)
        .clip(shape = RoundedCornerShape(25.dp))
        .background(MaterialTheme.colorScheme.primary)

    Scaffold(
        topBar = { TopAppBar(title = { Text("BriefNoteCompose") }) },
        modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = viewModel.noteText,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    viewModel.noteText = it
                })
            Button(
                modifier = buttonModifier,
                onClick = {}) {
                Text(text = "Neue Notiz hinzufügen")
            }
            Button(
                modifier = buttonModifier,
                onClick = {}) {
                Text(text = "Alle Notizen löschen")
            }
        }
    }
}
