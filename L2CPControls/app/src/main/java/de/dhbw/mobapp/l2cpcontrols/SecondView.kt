package de.dhbw.mobapp.l2cpcontrols

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondView() {
    val textState = remember {
        mutableStateOf(TextFieldValue())
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "SecondView") }) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            TextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                color = MaterialTheme.colorScheme.onSecondary,
                text = stringResource(R.string.reversed) + textState.value.text.reversed(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary)
                    .padding(10.dp)
            )
        }
    }
}