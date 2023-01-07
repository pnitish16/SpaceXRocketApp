package com.spacex.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spacex.app.data.db.dao.RocketDao
import com.spacex.app.data.db.entities.Converters
import com.spacex.app.data.db.entities.DbRocket
import com.spacex.app.data.db.entities.DbRocketDetails

private const val DB_NAME = "rocket_database"

@Database(entities = [(DbRocket::class), (DbRocketDetails::class)], version = 1)
@TypeConverters(value = [Converters::class])
abstract class RocketDatabase : RoomDatabase() {

    abstract fun rocketDao(): RocketDao

    companion object {
        fun create(context: Context): RocketDatabase {

            return Room.databaseBuilder(
                context,
                RocketDatabase::class.java,
                DB_NAME
            ).build()
        }
    }
}