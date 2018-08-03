package temp.nrk.com.templet.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import temp.nrk.com.templet.db.dao.WebsiteDao
import temp.nrk.com.templet.db.entity.WebsiteEntity


@Database(entities = arrayOf(WebsiteEntity::class), version = 1, exportSchema = false)
abstract class AppDB : RoomDatabase() {

    abstract fun websiteDao(): WebsiteDao

    companion object {

        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            if (INSTANCE == null) {
                synchronized(AppDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context, AppDB::class.java, "APP_DB").build()
                    }
                }
            }
            return INSTANCE!!
        }
    }


}