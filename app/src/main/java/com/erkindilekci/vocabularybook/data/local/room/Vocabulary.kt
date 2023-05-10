package com.erkindilekci.vocabularybook.data.local.room

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.erkindilekci.vocabularybook.util.Constants

@Entity(tableName = Constants.DATABASE_TABLE)
data class VocabularyCard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val desc: String,
    val sentence: String?= null,
   @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
   val image: ByteArray? = null,
    val category: String
)
