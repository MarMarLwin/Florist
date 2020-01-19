package com.example.floristbypo.dataaccess

import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import java.util.*


abstract class AppDatabase: RoomDatabase(){
//    abstract fun productionDao():ProductionOrderDAO
//    companion object {
//        var INSTANCE:AppDatabase?=null
//        fun getAppDatabase(context: Context):AppDatabase?{
//            if (INSTANCE == null) {
//                synchronized(AppDatabase::class.java) {
//                    INSTANCE= Room.databaseBuilder(context.applicationContext, AppDatabase::class.java,
//                        context.applicationInfo.dataDir+"//databases//OnTheGoWMS.db")
//                        .allowMainThreadQueries().fallbackToDestructiveMigration().build()
//                }
//            }
//            return INSTANCE
//        }
//    }
class DateTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}
}