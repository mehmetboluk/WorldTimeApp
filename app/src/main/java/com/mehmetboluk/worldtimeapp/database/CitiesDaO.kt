package com.mehmetboluk.worldtimeapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CitiesDaO  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertData(model : Model)

    @Delete
    suspend fun delete(model : Model)

    @Query("SELECT * FROM cities")
    fun getAll() : MutableList<Model>

    @Query("DELETE FROM cities")
    fun deleteAll()
}