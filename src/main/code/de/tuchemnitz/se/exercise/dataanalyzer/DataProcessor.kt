package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist

open class DataProcessor() {
    open fun process(data: List<DummyData>): MutableList<Coordinates> {
        return mutableListOf()
    }

    open fun processMany(data: List<IPersist>): MutableList<Coordinates> {
        TODO("Not yet implemented")
    }
}
