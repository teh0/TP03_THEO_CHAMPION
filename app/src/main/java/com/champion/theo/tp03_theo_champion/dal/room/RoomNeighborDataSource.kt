package com.champion.theo.tp03_theo_champion.dal.room

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.champion.theo.tp03_theo_champion.dal.NeighborDatasource
import com.champion.theo.tp03_theo_champion.dal.room.daos.NeighborDao
import com.champion.theo.tp03_theo_champion.dal.utils.toNeighbor
import com.champion.theo.tp03_theo_champion.models.Neighbor

class RoomNeighborDataSource(application: Application) : NeighborDatasource {
    private val database: NeighborDataBase = NeighborDataBase.getDataBase(application)
    private val dao: NeighborDao = database.neighborDao()
    private val _neighbors = MediatorLiveData<List<Neighbor>>()

    init {
        _neighbors.addSource(dao.getNeighbors()) { entities ->
            _neighbors.value = entities.map { entity ->
                entity.toNeighbor()
            }
        }
    }

    /**
     * Return neighbours datalive collection
     */
    override val neighbours: LiveData<List<Neighbor>>
        get() = _neighbors

    /**
     * Delete neighbour
     */
    override fun deleteNeighbour(neighbor: Neighbor) {
        dao.delete(neighbor.toEntity())
    }

    /**
     * Create neighbour
     */
    override fun createNeighbour(neighbor: Neighbor) {
        dao.create(neighbor.toEntity())
    }

    /**
     * Update neighbour status
     */
    override fun updateFavoriteStatus(neighbor: Neighbor) {
        dao.update(neighbor.toEntity())
    }

    /**
     * Update neighbour
     */
    override fun updateNeighbour(neighbor: Neighbor) {
        dao.update(neighbor.toEntity())
    }
}