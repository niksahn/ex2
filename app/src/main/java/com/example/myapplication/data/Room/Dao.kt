package com.example.myapplication.data.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.model.ListItemData

@Dao
interface PersonDao {
    @Query("SELECT * FROM users")
    fun getUsers(): List<ListItemData>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<ListItemData>)
}


