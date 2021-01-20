package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import de.tuchemnitz.se.exercise.dataanalyzer.Coordinates
import javafx.scene.image.Image
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import tornadofx.action
import tornadofx.button
import tornadofx.imageview
import tornadofx.rectangle
import tornadofx.stackpane
import tornadofx.toProperty

/**
 * Displays the rendered data
 */

class DataClientOutputView : MainBarView("Data Client Output") {

    // val data: MutableList<Coordinates> by inject()
    val data = createData()
    val imagePath = data[0].picturePath
    val size = data[0].scaledImageSize
    var counter = 0
    lateinit var rectangle: Rectangle
    var colors = createColorList()

    init {
        with(contentBox) {

            // displays current output image

            stackpane {
                imageview {
                    image = Image(
                        imagePath
                    )
                    fitWidthProperty().bind(data[0].scaledImageSize.x.toProperty())
                    fitHeightProperty().bind(data[0].scaledImageSize.y.toProperty())
                }
                rectangle = rectangle {
                    fill = colors[counter]
                    translateX = data[counter].xStart
                    translateY = data[counter].yStart
                    width = 50.0
                    height = 50.0
                }
            }

            // render next image and display
            button("NEXT") {
                action {
                    counter++
                    rectangle.fill = colors[counter]
                    rectangle.translateX = data[counter].xStart
                    rectangle.translateY = data[counter].yStart
                }
            }

            // close image view
            button("CLOSE") {
                action {
                    replaceWith(DataClientInitialView::class)
                }
            }
        }
    }

    // needed for git button
    fun printGitButton() {
    }

    fun createData(): MutableList<Coordinates> {
        var coordinates: MutableList<Coordinates> = mutableListOf()

        coordinates.add(
            Coordinates(
                -100.0,
                -100.0,
                -50.0,
                -50.0,
                "penguin.png",
                de.tuchemnitz.se.exercise.codecharts.Dimension(500.0, 600.0)
            )
        )
        coordinates.add(
            Coordinates(
                100.0,
                100.0,
                150.0,
                150.0,
                "kitten.jpeg",
                de.tuchemnitz.se.exercise.codecharts.Dimension(500.0, 600.0)
            )
        )
        coordinates.add(
            Coordinates(
                -200.0,
                -200.0,
                -150.0,
                -150.0,
                "kitten.jpeg",
                de.tuchemnitz.se.exercise.codecharts.Dimension(500.0, 600.0)
            )
        )
        coordinates.add(
            Coordinates(
                0.0,
                0.0,
                50.0,
                50.0,
                "kitten.jpeg",
                de.tuchemnitz.se.exercise.codecharts.Dimension(500.0, 600.0)
            )
        )

        return coordinates
    }

    fun createColorList(): MutableList<Color> {
        var colorList = mutableListOf<Color>()
        colorList.add(Color.BLUE)
        colorList.add(Color.BLANCHEDALMOND)
        colorList.add(Color.ALICEBLUE)
        colorList.add(Color.BURLYWOOD)
        colorList.add(Color.CORAL)
        colorList.add(Color.CRIMSON)
        colorList.add(Color.DARKORANGE)
        colorList.add(Color.GREENYELLOW)

        return colorList
    }
}
