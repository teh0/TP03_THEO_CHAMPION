package com.champion.theo.tp03_theo_champion.dal

import androidx.lifecycle.LiveData
import com.champion.theo.tp03_theo_champion.models.Neighbor

interface NeighborDatasource {
    /**
     * Get all my Neighbors
     * @return [List]
     */
    val neighbours: LiveData<List<Neighbor>>

    /**
     * Deletes a neighbor
     * @param neighbor : Neighbor
     */
    fun deleteNeighbour(neighbor: Neighbor)

    /**
     * Create a neighbour
     * @param neighbor: Neighbor
     */
    fun createNeighbour(neighbor: Neighbor)

    /**
     * Update "Favorite status" of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateFavoriteStatus(neighbor: Neighbor)

    /**
     * Update modified fields of an existing Neighbour"
     * @param neighbor: Neighbor
     */
    fun updateNeighbour(neighbor: Neighbor)
}