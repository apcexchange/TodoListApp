package com.example.todo.data

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todo.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao() : TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
       @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback(){
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            //database operations
           val dao = database.get().taskDao()

//            applicationScope.launch {
//                dao.insert(Task("solve algorithms", important = true))
//                dao.insert(Task("write articles"))
//                dao.insert(Task("wash clothes"))
//                dao.insert(Task("print npower form"))
//                dao.insert(Task("call babe", completed = true))
//                dao.insert(Task("buy Api"))
//                dao.insert(Task("watch movie"))
//                dao.insert(Task("play tennis", important = true))
//                dao.insert(Task("eat launch",completed = true))
//            }

        }
    }
}