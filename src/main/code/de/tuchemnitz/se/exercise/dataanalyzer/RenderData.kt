package main

abstract class RenderData(coordinates: Array<Number>, image: Any, color: String) {

    fun render(coordinates: Any, image: Any, color: Any) {
        // should create an overlay on original image at passed coordinates using color
        // should return combined image
    }
}
