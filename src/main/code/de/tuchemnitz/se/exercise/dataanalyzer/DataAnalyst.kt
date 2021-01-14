package de.tuchemnitz.se.exercise.dataanalyzer

class DataAnalyst(val state: Boolean, val dataRenderer: List<RenderData>) {
    companion object {
        private val queryBuilder = Query()
    }

    private fun validate() {
        // should chek if tool matches method
        // should return true if (tool == codeCharts || tool == zoomMaps) && (method == heatMap || method == Diagram)
        // should return true if (tool == webcam && method == timeline)
        // else return false
    }

    private fun getData() {
        // should create new query object
        // should return data
    }

    private fun processData(data: Any) {
        // should create new data processor
        // should return processed data
    }

    private fun newColor() {
        // should return an unused color
    }

    private fun renderData(coordinates: Array<Number>, image: Any, color: String) {
        // should create a new data renderer
        // should return rendered data
    }

    fun createWindow(size: Pair<Number, Number>) {
        // should create new window of specified size
    }

    fun display(image: Array<DataAnalystPicture>) {

        // create new de.tuchemnitz.se.exercise.dataanalyzer.Displayer and pass array of images with rendered data overlay
    }

    fun close() {
    }
}
