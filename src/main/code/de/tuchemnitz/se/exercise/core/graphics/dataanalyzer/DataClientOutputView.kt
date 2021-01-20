package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Rectangle
import tornadofx.*

/**
 * Displays the rendered data
 */

class DataClientOutputView : View("Data Client Output") {

    val data = PROCESSED_DATA

    // for trial purposes 
    // val data = createData()

    /**
     * get path for selected picture: Should be the same for all datasets in list
     */
    val imagePath = data[0].picturePath

    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    private val contentBox: VBox by fxid("content")
    var counter = 0
    lateinit var rectangle: Rectangle

    /**
     * initialize list of colors
     * to visualize different datasets
     */
    var colors = createColorList()

    init {
        with(contentBox) {

            stackpane {
                imageview {
                    image = Image(
                        imagePath
                    )

                    /**
                     * scale image to scaled image size to have correct dimensions
                     */
                    fitWidthProperty().bind(data[0].scaledImageSize.x.toProperty())
                    fitHeightProperty().bind(data[0].scaledImageSize.y.toProperty())
                }
                rectangle = rectangle {
                    fill = colors[counter]
                    translateX = data[counter].xStart
                    translateY = data[counter].yStart
                    width = data[counter].width
                    height = data[counter].height
                }
            }

            // render next image and display
            button("NEXT") {
                action {
                    /**
                     * iterate over datasets and move rectangle to according coordinates
                     */
                    counter++
                    rectangle.fill = colors[counter]
                    rectangle.translateX = data[counter].xStart
                    rectangle.translateY = data[counter].yStart
                    rectangle.width = data[counter].width
                    rectangle.height = data[counter].height
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

    /*
    //for trial purposes
    fun createData(): MutableList<Coordinates> {
        var coordinates: MutableList<Coordinates> = mutableListOf()

        coordinates.add(Coordinates(-100.0, -100.0, -50.0, -50.0, "penguin.png", Dimension(500.0, 600.0)))
        coordinates.add(Coordinates(100.0, 100.0, 150.0, 150.0, "kitten.jpeg", Dimension(500.0, 600.0)))
        coordinates.add(Coordinates(-200.0, -200.0, -150.0, -150.0, "kitten.jpeg", Dimension(500.0, 600.0)))
        coordinates.add(Coordinates(0.0, 0.0, 50.0, 50.0, "kitten.jpeg", Dimension(500.0, 600.0)))

        return coordinates
    }*/

    fun createColorList(): MutableList<Color> {
        var colorList = mutableListOf<Color>()
        colorList.add(Color.BLUE)
        colorList.add(Color.BURLYWOOD)
        colorList.add(Color.CORAL)
        colorList.add(Color.CRIMSON)
        colorList.add(Color.DARKORANGE)
        colorList.add(Color.GREENYELLOW)

        return colorList
    }
}
