package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.IData

open class DataProcessor() {
    open fun process(data: List<IData>): MutableList<Coordinates> {
        return mutableListOf()
    }

    open fun processCodeCharts(data: List<CodeChartsData>): MutableList<Coordinates> {
        return mutableListOf()
    }



}
