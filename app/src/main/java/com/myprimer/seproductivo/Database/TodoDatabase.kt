package com.myprimer.seproductivo.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.myprimer.seproductivo.Modelo.Todo


@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {


    companion object {
        const val NAME = "Todo_DB"
    }



    abstract fun getTodoDao(): TodoDao

}


