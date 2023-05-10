package com.erkindilekci.vocabularybook.util

import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.provider.MediaStore
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.room.TypeConverter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream



fun byteArrayToUri(byteArray: ByteArray, contentResolver: ContentResolver): Uri? {
    val timestamp = System.currentTimeMillis().toString()
    val displayName = "image_name_$timestamp.jpg"

    val values = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        put(MediaStore.Images.Media.DATA, byteArray)
        put(MediaStore.Images.Media.SIZE, byteArray.size)
        put(MediaStore.Images.Media.DATE_ADDED, System.currentTimeMillis())
        put(MediaStore.Images.Media.DATE_MODIFIED, System.currentTimeMillis())
        put(MediaStore.Images.Media.IS_PENDING, 1)
    }

    val collection = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
    val uri = contentResolver.insert(collection, values) ?: return null

    val outputStream = contentResolver.openOutputStream(uri) ?: return null
    outputStream.write(byteArray)
    outputStream.flush()
    outputStream.close()

    values.clear()
    values.put(MediaStore.Images.Media.IS_PENDING, 0)
    contentResolver.update(uri, values, null, null)

    return uri
}


fun uriToImageBitmap(contentResolver: ContentResolver, uri: Uri): ImageBitmap? {
    val inputStream = contentResolver.openInputStream(uri)
    inputStream?.use {
        val bitmap = BitmapFactory.decodeStream(it)
        return bitmap.asImageBitmap()
    }
    return null
}

fun byteArrayToImageBitmap(byteArray: ByteArray): ImageBitmap? {
    val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    return bitmap?.asImageBitmap()
}