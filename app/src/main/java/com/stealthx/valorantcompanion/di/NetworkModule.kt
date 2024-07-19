package com.stealthx.valorantcompanion.di

import com.stealthx.valorantcompanion.data.remote.api.AgentsAPI
import com.stealthx.valorantcompanion.data.remote.api.CurrenciesAPI
import com.stealthx.valorantcompanion.data.remote.api.MapsAPI
import com.stealthx.valorantcompanion.data.remote.api.ModesAPI
import com.stealthx.valorantcompanion.data.remote.api.WeaponsAPI
import com.stealthx.valorantcompanion.data.remote.repository.AgentRepositoryImpl
import com.stealthx.valorantcompanion.data.remote.repository.CurrenicesRepositoryImpl
import com.stealthx.valorantcompanion.data.remote.repository.MapsRepositoryImpl
import com.stealthx.valorantcompanion.data.remote.repository.ModesRepositoryImpl
import com.stealthx.valorantcompanion.data.remote.repository.WeaponsRepositoryImpl
import com.stealthx.valorantcompanion.domain.repository.AgentRepository
import com.stealthx.valorantcompanion.domain.repository.CurrenciesRepository
import com.stealthx.valorantcompanion.domain.repository.MapsRepository
import com.stealthx.valorantcompanion.domain.repository.ModesRepository
import com.stealthx.valorantcompanion.domain.repository.WeaponsRepository
import com.stealthx.valorantcompanion.domain.usecases.agents.GetAgentDetailsUseCase
import com.stealthx.valorantcompanion.domain.usecases.agents.GetAllAgentsListUsecase
import com.stealthx.valorantcompanion.domain.usecases.currencies.GetAllCurrenciesUsecase
import com.stealthx.valorantcompanion.domain.usecases.maps.GetAllMapsUsecase
import com.stealthx.valorantcompanion.domain.usecases.modes.GetAllModesUsecase
import com.stealthx.valorantcompanion.domain.usecases.weapons.GetAllWeaponsUsecase
import com.stealthx.valorantcompanion.utils.VALORANT_API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(VALORANT_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesAgentsAPI(retrofit: Retrofit): AgentsAPI {
        return retrofit.create(AgentsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesAgentRepository(agentsAPI: AgentsAPI) : AgentRepository {
        return AgentRepositoryImpl(agentsAPI)
    }

    @Provides
    @Singleton
    fun providesGetAllAgentsListUsecase(agentRepository: AgentRepository): GetAllAgentsListUsecase {
        return GetAllAgentsListUsecase(agentRepository)
    }

    @Provides
    @Singleton
    fun providesGetAgentDetailsUseCase(agentRepository: AgentRepository) : GetAgentDetailsUseCase {
        return GetAgentDetailsUseCase(agentRepository)
    }

    @Provides
    @Singleton
    fun providesMapsAPI(retrofit: Retrofit) : MapsAPI {
        return retrofit.create(MapsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesMapsRepository(mapsAPI: MapsAPI) : MapsRepository {
        return MapsRepositoryImpl(mapsAPI)
    }

    @Provides
    @Singleton
    fun providesGetAllMapsListUsecase(mapsRepository: MapsRepository) : GetAllMapsUsecase {
        return GetAllMapsUsecase(mapsRepository)
    }

    @Provides
    @Singleton
    fun providesModesAPI(retrofit: Retrofit) : ModesAPI {
        return  retrofit.create(ModesAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesModesRepository(modesAPI: ModesAPI) : ModesRepository {
        return ModesRepositoryImpl(modesAPI)
    }

    @Provides
    @Singleton
    fun providesGetAllModesListUsecase(modesRepository: ModesRepository): GetAllModesUsecase {
        return GetAllModesUsecase(modesRepository)
    }

    @Provides
    @Singleton
    fun providesWeaponsAPI(retrofit: Retrofit): WeaponsAPI {
        return retrofit.create(WeaponsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesWeaponsRepository(weaponsAPI: WeaponsAPI): WeaponsRepository {
        return WeaponsRepositoryImpl(weaponsAPI)
    }

    @Provides
    @Singleton
    fun providesGetAllWeaponsUsecase(weaponsRepository: WeaponsRepository): GetAllWeaponsUsecase {
        return GetAllWeaponsUsecase(weaponsRepository)
    }

    @Provides
    @Singleton
    fun providesCurrenciesAPI(retrofit: Retrofit): CurrenciesAPI {
        return retrofit.create(CurrenciesAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesCurrenciesRepository(currenciesAPI: CurrenciesAPI): CurrenciesRepository {
        return CurrenicesRepositoryImpl(currenciesAPI)
    }

    @Provides
    @Singleton
    fun providesGetAllCurrenciesUsecase(currenciesRepository: CurrenciesRepository): GetAllCurrenciesUsecase {
        return GetAllCurrenciesUsecase(currenciesRepository)
    }
}