package de.tuchemnitz.se.exercise.dataanalyzer

import org.junit.jupiter.api.Test

class DataAnalystTest {
    @Test
    fun `validating input should work`() {
        // should chek if tool matches method
        // should return true if (tool == codeCharts || tool == zoomMaps) && (method == heatMap || method == Diagram)
        // should return true if (tool == webcam && method == timeline)
        // else return false
    }

    fun `getting Data from Database should work by invoking function`() {
        // should create new query object
        // should return data
    }

    fun `processing Data should work`() {
        // should create new data processor
        // should return processed data
    }

    fun `getting unsused color should work`() {
        // should return an unused color
    }

    fun `rendering Data should work`() {
        // should create a new data renderer
        // should return rendered data
    }

    fun `closing Data Analyst should work`() {
    }
}
