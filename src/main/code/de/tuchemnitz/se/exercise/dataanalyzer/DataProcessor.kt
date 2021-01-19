package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist

open class DataProcessor() {
    open fun process(data: List<IPersist>): MutableList<Coordinates> {
        return mutableListOf()
    }
}
