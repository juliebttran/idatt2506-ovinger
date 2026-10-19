package com.example.oving3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.oving3.ui.theme.Oving3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Oving3Theme {
                FriendScreenOwner()
            }
        }
    }
}

@Preview(showBackground = true, heightDp = 640)
@Composable
fun FriendScreenOwnerPreview() {
    FriendScreenOwner()
}