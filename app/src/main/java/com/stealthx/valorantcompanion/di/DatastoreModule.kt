package com.stealthx.valorantcompanion.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.stealthx.valorantcompanion.data.local.datastore.AppDatastoreImpl
import com.stealthx.valorantcompanion.domain.onboarding.AppDatastore
import com.stealthx.valorantcompanion.domain.onboarding.usecases.ReadOnBoardPreferenceUsecase
import com.stealthx.valorantcompanion.domain.onboarding.usecases.SaveOnBoardPreferenceUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {
    @Provides
    @Singleton
    fun providesPreferencesDatastore(@ApplicationContext context: Context) : DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile ={
                context.preferencesDataStoreFile("onBoardingPref")
            }
        )
    }

    @Provides
    @Singleton
    fun providesAppDataStore(dataStore: DataStore<Preferences>): AppDatastore {
        return AppDatastoreImpl(dataStore)
    }

    @Provides
    @Singleton
    fun providesReadOnBoardPrefUsecase(appDatastore: AppDatastore) : ReadOnBoardPreferenceUsecase {
        return ReadOnBoardPreferenceUsecase(appDatastore)
    }

    @Provides
    @Singleton
    fun providesSaveOnBoardPrefUsecase(appDatastore: AppDatastore) : SaveOnBoardPreferenceUsecase {
        return SaveOnBoardPreferenceUsecase(appDatastore)
    }
}