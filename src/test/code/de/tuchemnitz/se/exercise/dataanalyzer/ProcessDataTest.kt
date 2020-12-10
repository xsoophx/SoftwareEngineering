package de.tuchemnitz.se.exercise.dataanalyzer

import org.junit.jupiter.api.Test

class ProcessDataTest {
    @Test
    fun `sorting Data by picture should work`() {
        // should sort by image -> group together all datasets with same image: Array<Array<Dataset>>
        // every subarray of datasets should have used the same image
    }

    @Test
    fun `calculating pixel coordinates should work`() {
        // should calculate the coordinates to draw for every dataset within array of datasets
        // for code charts-> starting_x and starting_y pixels and width and height of rectangle
        // (from grid size, gird coordinates and img size)
        // for zoom maps-> center point and radius
        // for webcam -> coordinates should be provided (either in form of fine grid or points with small circle radius
        // should return array of coordinates
    }
}