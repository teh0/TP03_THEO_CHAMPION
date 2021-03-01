package com.champion.theo.tp03_theo_champion.dal.utils

import com.champion.theo.tp03_theo_champion.dal.room.entities.NeighborEntity
import com.champion.theo.tp03_theo_champion.models.Neighbor

fun NeighborEntity.toNeighbor() = Neighbor(
    id = id.toString(),
    name = name,
    avatarUrl = avatarUrl,
    address = address,
    phoneNumber = phoneNumber,
    aboutMe = aboutMe,
    favorite = favorite,
    webSite = webSite ?: ""
)