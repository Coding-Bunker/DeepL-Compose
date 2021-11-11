package it.github.samuele794.composedeepl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.github.samuele794.composedeepl.repository.DeepLRepository
import it.github.samuele794.composedeepl.repository.DeepLRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDeepLRepository(): DeepLRepository = DeepLRepositoryImpl()
}