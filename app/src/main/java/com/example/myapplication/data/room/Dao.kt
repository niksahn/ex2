package com.example.myapplication.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.ListItemDataEntity

@Dao
interface PersonDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<ListItemDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<ListItemDataEntity>)
}


