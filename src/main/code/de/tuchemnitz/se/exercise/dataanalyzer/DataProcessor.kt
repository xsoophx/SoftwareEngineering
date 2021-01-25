package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import javafx.geometry.Point2D

open class DataProcessor {
    open fun process(data: List<IPersist>): List<Point2D> {
        return mutableListOf()
    }

    open fun processMany(data: List<IPersist>): MutableList<Coordinates> {
        TODO("Not yet implemented")
    }
}
