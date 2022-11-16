package com.example.myapplication.data.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.model.ListItemData

@Database(
    entities = [ListItemData::class ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val personDao: PersonDao
}