package dev.cremenb.database

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.sqlite.db.SupportSQLiteOpenHelper
import dev.cremenb.database.dao.IProfileDao
import dev.cremenb.database.models.ProfileDbo

@Database(entities = [ProfileDbo::class], version = 1)
abstract class DataBase() : RoomDatabase() {

    abstract fun profileDao() : IProfileDao

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun createOpenHelper(config: DatabaseConfiguration): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }
}

fun DataBase (applicationContext: Context): DataBase {
    return Room.databaseBuilder(
        checkNotNull(applicationContext.applicationContext),
        DataBase::class.java,
        "CampusConnect"
    ).build()
}