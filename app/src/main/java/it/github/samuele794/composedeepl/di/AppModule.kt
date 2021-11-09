package it.github.samuele794.composedeepl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.github.samuele794.composedeepl.repository.DeepLRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDeepLRepository() = DeepLRepository()
}

//@Module
//@InstallIn(ViewModelComponent::class)
//class ViewModelModule {
//
//    @Provides
//    fun provideTranslationViewmodel(repository: DeepLRepository) =
//        TranslationViewModel(repository)
//}