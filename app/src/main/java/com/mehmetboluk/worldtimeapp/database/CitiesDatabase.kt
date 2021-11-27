package com.mehmetboluk.worldtimeapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Model::class],
    version = 1
)
abstract class CitiesDatabase : RoomDatabase(){
    abstract fun getCitiesDAO() : CitiesDaO

    companion object{

        @Volatile
        private var instance : CitiesDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) = Room.
        databaseBuilder(context.applicationContext, CitiesDatabase::class.java,"citiesdatabase").allowMainThreadQueries().build()
    }
}