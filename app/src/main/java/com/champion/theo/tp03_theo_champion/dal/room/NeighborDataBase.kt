package com.champion.theo.tp03_theo_champion.dal.room

import android.app.Application
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.champion.theo.tp03_theo_champion.dal.room.daos.NeighborDao
import com.champion.theo.tp03_theo_champion.dal.room.entities.NeighborEntity
import com.champion.theo.tp03_theo_champion.mocks.NeighborMock
import java.util.concurrent.Executors

@Database(
    entities = [NeighborEntity::class],
    version = 1
)
abstract class NeighborDataBase : RoomDatabase() {
    abstract fun neighborDao(): NeighborDao

    companion object {
        private var instance: NeighborDataBase? = null

        fun getDataBase(application: Application): NeighborDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    application.applicationContext,
                    NeighborDataBase::class.java,
                    "neighbor_database.db"
                ).addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        insertFakeData()
                    }
                })
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }

        /**
         * Insert fake data to the database when it is create for the first time
         */
        private fun insertFakeData() {
            Executors.newSingleThreadExecutor().execute {
                NeighborMock.getData().forEach {
                    instance?.neighborDao()?.create(it.toEntity())
                }
            }
        }
    }
}