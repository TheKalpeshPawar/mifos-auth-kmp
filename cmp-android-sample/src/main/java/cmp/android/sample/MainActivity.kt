package cmp.android.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cmp.android.sample.ui.theme.MifosauthkmpTheme
import cmp.shared.sample.SharedApplication

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MifosauthkmpTheme {
                SharedApplication()
            }
        }
    }
}

