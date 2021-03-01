package com.champion.theo.tp03_theo_champion.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.champion.theo.tp03_theo_champion.di.DI
import com.champion.theo.tp03_theo_champion.models.Neighbor
import com.champion.theo.tp03_theo_champion.repositories.NeighborRepository

class NeighborViewModel : ViewModel() {
    private val repository: NeighborRepository = DI.repository

    // On fait passe plat sur le résultat retourné par le repository
    val neighbors: LiveData<List<Neighbor>>
        get() = repository.getNeighbours()
}