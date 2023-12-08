package com.example.museumapp.model.di

import com.example.museumapp.model.FirestoreRepository
import com.example.museumapp.model.firebaseAuth.FirestoreRepositoryImpl
import com.example.museumapp.model.resources.MuseumAppState
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    fun provideFirestoreRepository(impl: FirestoreRepositoryImpl): FirestoreRepository = impl


}