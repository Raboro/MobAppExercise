package de.dhbw.mobapp.l2cpcontrols

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import de.dhbw.mobapp.l2cpcontrols.ui.theme.L2CPControlsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            L2CPControlsTheme {
                MainView()
            }
        }
    }
}
