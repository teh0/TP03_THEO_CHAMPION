package com.champion.theo.tp03_theo_champion.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.champion.theo.tp03_theo_champion.dal.NeighborDatasource
import com.champion.theo.tp03_theo_champion.dal.room.RoomNeighborDataSource
import com.champion.theo.tp03_theo_champion.mocks.NeighborMock
import com.champion.theo.tp03_theo_champion.models.Neighbor

class NeighborRepository private constructor(application: Application) {
    private val dataSource: NeighborDatasource

    init {
        dataSource = RoomNeighborDataSource(application)
    }

    fun getNeighbours(): LiveData<List<Neighbor>> = dataSource.neighbours

    fun delete(neighbor: Neighbor) = dataSource.deleteNeighbour(neighbor)

    fun create(neighbor: Neighbor) = dataSource.createNeighbour(neighbor)

    companion object {
        private var instance: NeighborRepository? = null

        fun getInstance(application: Application): NeighborRepository {
            if (instance == null) {
                instance = NeighborRepository(application)
            }
            return instance!!
        }
    }
}