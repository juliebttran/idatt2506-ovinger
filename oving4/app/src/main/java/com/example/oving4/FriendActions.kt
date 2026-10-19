package com.example.oving4

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.example.oving4.R

@Composable
fun FriendActions(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val manager = context.packageManager
    val dial = Intent(Intent.ACTION_DIAL)
    val search = Intent(Intent.ACTION_VIEW, searchUrl(name).toUri())
    Row(modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(
            onClick = { context.startActivity(dial) },
            enabled = dial.resolveActivity(manager) != null,
        ) {
            Text(text = stringResource(R.string.call_friend))
        }
        Button(
            onClick = { context.startActivity(search) },
            enabled = search.resolveActivity(manager) != null,
        ) {
            Text(text = stringResource(R.string.search_web))
        }
    }
}
private fun searchUrl(name: String) =
    "https://www.google.com/search?q=$name"

@Preview(showBackground = true)
@Composable
fun FriendActionsPreview() {
       FriendActions(name = "Kari")
}