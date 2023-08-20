package com.gugugu.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.gugugu.local.dao.MealDao
import com.gugugu.local.database.GuguguDataBase
import com.gugugu.local.util.GuguguTable
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.annotation.Signed
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Provides
    @Singleton
    fun providesGuguguDataBase(
        @ApplicationContext context: Context
    ): GuguguDataBase = Room
        .databaseBuilder(
            context,
            GuguguDataBase::class.java,
            GuguguTable.DATABASE
        )
        .build()

    @Provides
    @Singleton
    fun providesMealDao(
        guguguDataBase: GuguguDataBase
    ): MealDao = guguguDataBase.mealDao()
}