package de.tuchemnitz.se.exercise.dataanalyzer

open class ProcessData(datasets: Array<Any>, method: Number) {
    init {
        // should receive an array of datasets and a method(Enum)
        // if datasets.length > 1: it should sort the datasets by the image that was used
        // for subarray in array of datasets:
        // for dataset in subarray:
        // --> calculate coordinates of pixels within the area that was being looked at
        // --> render()
        // should return array of rendered datasets
    }

    fun sort() {
        // should sort by image -> group together all datasets with same image: Array<Array<Dataset>>
        // every subarray of datasets should have used the same image
    }

    fun calculateCoordinates() {
        // should calculate the coordinates to draw for every dataset within array of datasets
        // for code charts-> starting_x and starting_y pixels and width and height of rectangle
        // (from grid size, gird coordinates and img size)
        // for zoom maps-> center point and radius
        // for webcam -> coordinates should be provided (either in form of fine grid or points with small circle radius
        // should return array of coordinates
    }
}
