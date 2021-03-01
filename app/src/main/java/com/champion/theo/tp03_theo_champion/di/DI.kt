package com.champion.theo.tp03_theo_champion.di

import android.app.Application
import com.champion.theo.tp03_theo_champion.repositories.NeighborRepository

object DI {
    /**
     * Repository
     */
    lateinit var repository: NeighborRepository

    /**
     * Trigger DI
     */
    fun inject(application: Application) {
        repository = NeighborRepository.getInstance(application)
    }
}