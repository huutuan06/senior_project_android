package com.app.vogobook.di.module

import androidx.room.Room
import com.app.vogobook.app.Application
import com.app.vogobook.localstorage.AppDatabase
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.dao.BookDAO
import com.app.vogobook.localstorage.dao.CategoryDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(application: Application) {

    private var mAppDatabase: AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME).build()

    @Singleton
    @Provides
    fun provideAppDatabase() : AppDatabase {
        return mAppDatabase
    }

    @Singleton
    @Provides
    fun provideBookDAO(appDatabase: AppDatabase) : BookDAO {
        return appDatabase.getBookDAO()
    }

    @Singleton
    @Provides
    fun provideCategoryDAO(appDatabase: AppDatabase) : CategoryDAO {
        return appDatabase.getCategoryDAO()
    }

    @Singleton
    @Provides
    fun provideRoomUIManager(bookDAO: BookDAO, categoryDAO: CategoryDAO): RoomUIManager {
        return RoomUIManager(bookDAO, categoryDAO)
    }

}