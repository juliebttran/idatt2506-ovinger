package com.example.oving7

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class MovieFiles(private val context: Context) {
    suspend fun readPacked(resourceId: Int): List<String> =
        withContext(Dispatchers.IO) {
            context.resources.openRawResource(resourceId)
                .bufferedReader(Charsets.UTF_8)
                .use { reader -> reader.readLines()}
        }

    suspend fun writeLocal(name: String, lines: List<String>) {
        withContext(Dispatchers.IO) {
            File(context.filesDir, name)
                .writeText(lines.joinToString("\n"), Charsets.UTF_8)
        }
    }

    suspend fun readLocal(name: String): List<String> =
        withContext(Dispatchers.IO) {
            val file = File(context.filesDir, name)
            if (file.exists()) file.readLines(Charsets.UTF_8)
            else emptyList()
        }

    suspend fun localPath(name: String): String =
        withContext(Dispatchers.IO) {
            File(context.filesDir, name).path
        }
}