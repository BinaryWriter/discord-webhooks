package me.binarywriter.discordwebhooks.http

import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

object HttpClient {
    private val client = OkHttpClient().newBuilder().build()

    /**
     * Executes a GET request
     */
    fun get(url: String): Response = client.newCall(
        Request.Builder()
            .url(url)
            .get()
            .build()
    ).execute()

    /**
     * Executes a POST request
     */
    fun post(url: String, content: String, files: Map<String, ByteArray> = emptyMap()): Response = client.newCall(
        Request.Builder()
            .url(url)
            .post(
                MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("payload_json", content)
                    .addByteArrays(files)
                    .build()
            )
            .build()
    ).execute()

    /**
     * Executes a DELETE request
     */
    fun delete(url: String): Response = client.newCall(
        Request.Builder()
            .url(url)
            .delete()
            .build()
    ).execute()

    /**
     * Adds bytes to multipart body
     */
    private fun MultipartBody.Builder.addByteArrays(arrays: Map<String, ByteArray>): MultipartBody.Builder {
        var builder = this

        for ((key, value) in arrays) {
            builder = builder.addFormDataPart(key, key, value.toRequestBody())
        }

        return builder
    }
}