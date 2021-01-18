package de.tuchemnitz.se.exercise.dataanalyzer

open class DataRenderer(){

    open fun render(coordinates: Coordinates) : Boolean{
        // should create an overlay on original image at passed coordinates using color
        // should return combined image
        return true
    }

    open fun renderMany(coordinatesList: MutableList<Coordinates>): Boolean{
        return true
    }
}
