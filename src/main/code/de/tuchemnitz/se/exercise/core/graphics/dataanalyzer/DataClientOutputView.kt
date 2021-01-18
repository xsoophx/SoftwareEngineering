package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.dataanalyzer.Coordinates
import de.tuchemnitz.se.exercise.dataanalyzer.Dimension
import javafx.scene.image.Image
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import tornadofx.*

/**
 * Displays the rendered data
 */

class DataClientOutputView : View("Data Client Output") {

    // val data: MutableList<Coordinates> by inject()
    val data = createData()
    val imagePath = data[0].picturePath
    val size = data[0].scaledImageSize
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    private val contentBox: VBox by fxid("content")
    var counter = 0

    init {
        with(contentBox) {

            // displays current output image

            stackpane {
                imageview {
                    x = data[0].scaledImageSize.x
                    y = data[0].scaledImageSize.y
                    image = Image(
                        imagePath
                    )
                }

                    rectangle {
                        x = data[counter].xStart
                        y = data[counter].yStart
                        width = 50.0
                        height = 50.0
                    }

                }


            // render next image and display
            button("NEXT") {
                action {
                    // jump back into loop and continue
                    // if there are no more datasets: display according view
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

        coordinates.add(Coordinates(17.0, 15.0, 20.0, 28.0, "penguin.png", Dimension(100.0, 100.0)))
        coordinates.add(Coordinates(50.0, 50.0, 70.0, 70.0, "kitten.jpeg", Dimension(100.0, 100.0)))

        return coordinates
    }
}
