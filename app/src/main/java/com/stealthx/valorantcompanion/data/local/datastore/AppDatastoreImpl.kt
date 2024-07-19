package com.stealthx.valorantcompanion.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.stealthx.valorantcompanion.domain.onboarding.AppDatastore
import com.stealthx.valorantcompanion.utils.ONBOARD_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AppDatastoreImpl(
    private val datastore: DataStore<Preferences>
) : AppDatastore {
    override fun readOnBoardingPref(): Flow<Boolean> {
        return datastore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[ONBOARD_KEY] ?: false
        }
    }

    override suspend fun saveOnBoardingPref() {
        datastore.edit { preferences ->
            preferences[ONBOARD_KEY] = true
        }
    }
}