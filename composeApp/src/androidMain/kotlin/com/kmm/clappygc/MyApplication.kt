package com.kmm.clappygc

import android.app.Application
import com.kmm.clappygc.di.initializeKoin


//
// Created by Code For Android on 21/01/25.
// Copyright (c) 2025 CFA. All rights reserved.
//

class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initializeKoin()
    }
}