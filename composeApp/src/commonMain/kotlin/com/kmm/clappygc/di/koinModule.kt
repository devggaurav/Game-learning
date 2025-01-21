package com.kmm.clappygc.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.Settings
import com.russhwolf.settings.observable.makeObservable
import org.koin.core.context.startKoin
import org.koin.dsl.module


//
// Created by Code For Android on 21/01/25.
// Copyright (c) 2025 CFA. All rights reserved.
//

@OptIn(ExperimentalSettingsApi::class)
val sharedModule = module {
    single<ObservableSettings> { Settings().makeObservable() }
}


fun initializeKoin() {
    startKoin {
        modules(sharedModule)
    }
}