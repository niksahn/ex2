package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.model.ListItemDataEntity

@Database(
    entities = [ListItemDataEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract val personDao: PersonDao
}