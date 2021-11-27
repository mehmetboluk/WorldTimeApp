package com.mehmetboluk.worldtimeapp.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "cities")
data class Model(
    @ColumnInfo(name = "cityName")
    val cityName : String,
    @ColumnInfo(name = "cityTimeZone")
    val cityTimeZone : String,
    @ColumnInfo(name = "backgroundColor")
    val backgroundColor : String,
    @ColumnInfo(name = "dialColor")
    val dialColor : String) {
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}