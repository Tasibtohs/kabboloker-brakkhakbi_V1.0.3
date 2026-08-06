package com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String = "",
    val content: String = "",
    val titleColorHex: String = "#D4A017",
    val textColorHex: String = "#1A1A2E",
    val fontFamilyKey: String = "hind_siliguri",
    val fontSizeSp: Float = 16f,
    val isBold: Boolean = false,
    val isItalic: Boolean = false,
    val isUnderline: Boolean = false,
    val isStrikethrough: Boolean = false,
    val textAlign: String = "LEFT", // LEFT, CENTER, RIGHT
    val lineBreakMode: String = "WORD", // WORD, LINE_BY_LINE, OFF
    val lineSpacingMultiplier: Float = 1.3f,
    val groupId: Long? = null,
    val isPinned: Boolean = false,
    val isLocked: Boolean = false,
    val isHidden: Boolean = false,
    val isTrashed: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
