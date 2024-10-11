package de.dhbw.mobapp.l2cpcontrols

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.dhbw.mobapp.l2cpcontrols.ui.theme.L2CPControlsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            L2CPControlsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    L2CPControlsApp(innerPadding = innerPadding)
                }
            }
        }
    }
}

@Composable
fun L2CPControlsApp(names: List<String> = listOf("DHBW", "Compose"), innerPadding: PaddingValues) {
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(24.dp)
    ) {
        Text(
            text = stringResource(R.string.hello),
            color = MaterialTheme.colorScheme.onPrimary
        )
        Text(
            text = "Hello $name!",
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
