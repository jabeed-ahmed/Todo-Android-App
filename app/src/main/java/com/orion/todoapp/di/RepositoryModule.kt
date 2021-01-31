package com.orion.todoapp.di

import com.orion.todoapp.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MainRepository(get(), get()) }
}
