package com.muvidb.app.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.muvidb.app.data.local.dao.MovieDao
import com.muvidb.app.data.local.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = true)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        private const val DB_NAME = "quote_db"
        fun create(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                DB_NAME
            ).fallbackToDestructiveMigration().build()
        }
    }

}