package com.champion.theo.tp03_theo_champion.repositories

import com.champion.theo.tp03_theo_champion.mocks.NeighborMock
import com.champion.theo.tp03_theo_champion.models.Neighbor

class NeighborRepository: BaseRepository<Neighbor>() {
    companion object {
        private var instance: NeighborRepository? = null

        fun getInstance(): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository()
            }
            return instance!!
        }
    }

    init {
        this.modelList = NeighborMock.getData()
    }

    override fun update(model: Neighbor) {

    }
}