package com.champion.theo.tp03_theo_champion.dal.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.champion.theo.tp03_theo_champion.dal.room.entities.NeighborEntity

@Dao
interface NeighborDao {
    /**
     * Get neighbour from DB
     */
    @Query("SELECT * from neighbors")
    fun getNeighbors(): LiveData<List<NeighborEntity>>

    /**
     * Get neighbour by id from DB
     */
    @Query("SELECT * FROM neighbors WHERE id LIKE :id")
    fun findById(id: Long): NeighborEntity

    /**
     * Delete a neighbour from DB
     */
    @Delete
    fun delete(neighbor: NeighborEntity)

    /**
     * Insert a neighbour to DB
     */
    @Insert
    fun create(vararg neighbor: NeighborEntity)

    /**
     * Update neighbour
     */
    @Update
    fun update(vararg neighbor: NeighborEntity)

}
