package com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.dao.GroupDao
import com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.dao.NoteDao
import com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.entity.GroupEntity
import com.hmibrahimsarkar.kabboloker_brakkhakbi.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, GroupEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
    abstract fun groupDao(): GroupDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "kabyolokor_brokhokobi_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
