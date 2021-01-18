package de.tuchemnitz.se.exercise.dataanalyzer

open class DataProcessor() {
    open fun process(data: List<DummyData>): MutableList<Coordinates> {
        return mutableListOf()
    }

    open fun processMany(data: List<DummyData>): MutableList<Coordinates> {
        TODO("Not yet implemented")
    }
}
