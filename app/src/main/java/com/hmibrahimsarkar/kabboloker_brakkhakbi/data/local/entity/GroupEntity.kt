package com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "groups")
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val colorHex: String = "#D4A017",
    val createdAt: Long = System.currentTimeMillis()
)
