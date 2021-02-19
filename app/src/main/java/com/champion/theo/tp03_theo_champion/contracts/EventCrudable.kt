package com.champion.theo.tp03_theo_champion.contracts

interface EventCrudable<Model> {
    fun onDelete(model: Model)
}