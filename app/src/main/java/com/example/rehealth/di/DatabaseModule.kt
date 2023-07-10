package com.example.rehealth.di

import android.content.Context
import androidx.room.Room
import com.example.rehealth.data.ReHealthDB
import com.example.rehealth.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun databaseProvider(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            ReHealthDB::class.java,
            DATABASE_NAME
        ).build()

    @Singleton
    @Provides
    fun daoProvider(database: ReHealthDB) = database.medicineDao()
}