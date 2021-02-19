package com.champion.theo.tp03_theo_champion.repositories

abstract class BaseRepository<Model> {

    protected var modelList: MutableList<Model> = mutableListOf()

    fun create(model: Model) {
        this.modelList.add(model)
    }

    abstract fun update(model: Model)

    fun delete(model: Model) {
        this.modelList.remove(model)
    }

    fun all(): MutableList<Model> {
        return this.modelList
    }
}