package com.github.shenziq1.fortherecord.di

import android.content.Context
import androidx.room.Room
import com.github.shenziq1.fortherecord.database.FakeTask
import com.github.shenziq1.fortherecord.database.TaskDao
import com.github.shenziq1.fortherecord.database.TaskDatabase
import com.github.shenziq1.fortherecord.repository.OfflineRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{
    @Singleton
    @Provides
    fun provideOfflineRepository(taskDao: TaskDao): OfflineRepository{
        return OfflineRepository(taskDao = taskDao)
    }

    @Provides
    fun provideTaskDao(database: TaskDatabase): TaskDao {
        return database.taskDao()
    }

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase{
        return Room.databaseBuilder(
            context,
            TaskDatabase::class.java,
            "taskDatabase"
        ).build()
    }
}

object DataSourceModule{

}
object DatabaseModule{

}