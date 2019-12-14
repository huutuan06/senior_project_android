package com.app.vogobook.di.module

import androidx.room.Room
import com.app.vogobook.app.Application
import com.app.vogobook.localstorage.AppDatabase
import com.app.vogobook.localstorage.RoomUIManager
import com.app.vogobook.localstorage.dao.*
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
    fun provideUserDAO(appDatabase: AppDatabase) : UserDAO {
        return appDatabase.getUserDAO()
    }

    @Singleton
    @Provides
    fun provideOrderDAO(appDatabase: AppDatabase) : OrderDAO {
        return appDatabase.getOrderDAO()
    }

    @Singleton
    @Provides
    fun provideReviewDAO(appDatabase: AppDatabase) : ReviewDAO {
        return appDatabase.getReviewDAO()
    }

    @Singleton
    @Provides
    fun provideRoomUIManager(bookDAO: BookDAO, categoryDAO: CategoryDAO, userDAO: UserDAO, orderDAO: OrderDAO, reviewDAO: ReviewDAO): RoomUIManager {
        return RoomUIManager(bookDAO, categoryDAO, userDAO, orderDAO, reviewDAO)
    }

}