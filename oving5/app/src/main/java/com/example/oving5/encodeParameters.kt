package com.example.oving5

import java.net.URLEncoder

fun encodeParameters(
    parameters: Map<String, String>): String = parameters.entries.joinToString("&"){
        entry ->
    encode(entry.key)+ "=" + encode(entry.value)
}

private fun encode(text: String): String = URLEncoder.encode(text, "UTF-8")
