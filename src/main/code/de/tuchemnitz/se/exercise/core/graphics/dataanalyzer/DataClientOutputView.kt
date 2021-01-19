package de.tuchemnitz.se.exercise.core.graphics.dataanalyzer

import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import javafx.scene.image.Image
import tornadofx.action
import tornadofx.button
import tornadofx.imageview

/**
 * Displays the rendered data
 */

class DataClientOutputView : MainBarView("Data Client Output") {

    /**
     * View
     */
    init {
        with(contentBox) {

            // displays current output image
            imageview {

                image = Image(
                    "output.bmp"
                )
            }

            // render next image and display
            button("NEXT") {
                action {
                    // jump back into loop and continue
                    // if there are no more datasets: display according view
                    replaceWith(DataClientInitialView::class)
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
}
