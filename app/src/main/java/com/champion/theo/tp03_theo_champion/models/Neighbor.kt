package com.champion.theo.tp03_theo_champion.models

import com.champion.theo.tp03_theo_champion.dal.room.entities.NeighborEntity

data class Neighbor(
    val id: String,
    val name: String,
    val avatarUrl: String,
    val address: String,
    val phoneNumber: String,
    val aboutMe: String,
    val favorite: Boolean,
    val webSite: String
) {
    fun toEntity(): NeighborEntity {
        return NeighborEntity(
            this.id.toLong(),
            name,
            avatarUrl,
            address,
            phoneNumber,
            aboutMe,
            favorite,
            webSite
        )
    }
}