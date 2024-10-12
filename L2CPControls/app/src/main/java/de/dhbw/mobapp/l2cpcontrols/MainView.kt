package de.dhbw.mobapp.l2cpcontrols

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MainView(names: List<String> = listOf("DHBW", "Compose")) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            names.forEach {
                Column(modifier = Modifier.padding(5.dp)) {
                    Greeting(name = it)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var expandad by rememberSaveable {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(24.dp)
    ) {
        Column(
            modifier = modifier
                .weight(1f)
        ) {
            Text(
                text = stringResource(R.string.hello),
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            if (expandad) {
                OutlinedButton(modifier = Modifier.padding(top = 5.dp), onClick = { /*TODO*/ }) {
                    Text(
                        text = stringResource(R.string.navigate),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
        OutlinedButton(
            modifier = Modifier.padding(top = 2.dp),
            onClick = { expandad = !expandad }) {
            Text(
                text = stringResource(if (expandad) R.string.show_less else R.string.show_more),
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}