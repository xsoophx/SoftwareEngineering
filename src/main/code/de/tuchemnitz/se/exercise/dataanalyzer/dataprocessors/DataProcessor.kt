package de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors

import de.tuchemnitz.se.exercise.persist.IPersist

abstract class DataProcessor<T>{

    abstract fun process(data: List<IPersist>): List<T>

}
