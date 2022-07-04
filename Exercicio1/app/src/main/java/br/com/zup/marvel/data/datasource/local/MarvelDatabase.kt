package br.com.zup.marvel.data.datasource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.zup.marvel.data.datasource.local.dao.MarvelDAO
import br.com.zup.marvel.domain.model.Marvel


@Database(entities = [Marvel::class], version = 1)
abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDAO

    companion object {
        @Volatile
        private var INSTANCE: MarvelDatabase? = null

        fun getDatabase(context: Context): MarvelDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MarvelDatabase::class.java,
                    "filme_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}