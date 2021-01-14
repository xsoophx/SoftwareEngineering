package de.tuchemnitz.se.exercise.core.graphics.zoommaps

import com.sun.javafx.util.Utils.clamp
import de.tuchemnitz.se.exercise.codecharts.IMAGE_PATH
import de.tuchemnitz.se.exercise.core.configmanager.ConfigManager
import de.tuchemnitz.se.exercise.core.graphics.MainApp
import de.tuchemnitz.se.exercise.core.graphics.system.ToolSelectionView
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.geometry.Point2D
import javafx.geometry.Rectangle2D
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import tornadofx.View
import tornadofx.action
import tornadofx.button
import tornadofx.getValue
import tornadofx.imageview
import tornadofx.keyboard
import tornadofx.setValue

/*
* This view is responsible for zooming in and out on a picture.
*/
class ZoomMapsView : View("Zoom Maps") {
    /*
    * @param root: unique root BorderPane for all Views
    * @param contentBox: unique contentBox, where content is placed for every view
    */
    override val root: BorderPane by fxml(MainApp.MAIN_VIEW_TEMPLATE_PATH)
    private val contentBox: VBox by fxid("content")

    /*
    * @param scaleProperty: injected property for the zoomFunction. This has to be edited in order to change
    * the scale/speed of the zoom.
    */
    private val scaleProperty = SimpleDoubleProperty(1.0)
    var scale by scaleProperty

    /*
    * @param configManager: the ConfigManager is being injected to save and
    * get settings for the View (zoomSpeed, zoomButtons, etc.).
    */
    private val configManager: ConfigManager by inject()

    /*
    * @param zoomEnabledProperty: states whether the initial zoom is activated or not (default is false).
    */
    private val zoomEnabledProperty = SimpleBooleanProperty(false)
    var zoomEnabled by zoomEnabledProperty

    /*
    * Contains Logger, which is logging scroll and zoom Events.
    */
    companion object {
        val logger: Logger = LoggerFactory.getLogger("ZoomMapsView Logger")
    }

    /*
    * With the initialisation, the root of the fxml is being overwritten.
    */
    init {
        with(root) {

            /*
             * @param zoomKey: the zoomKey is retrieved from the settings of the configManager,
             * in case an error is occurring, it is being set to the char "E"
             */
            val zoomKey = configManager.getZoomMapsConfig()?.zoomKey ?: KeyCode.E

            /*
             * @receiver root/BorderPane: an EventFilter to the root is added. It is detecting key presses and
             * key releases. If the Key of the config is pressed, zooming is being enabled. Is it released,
             * zooming gets disabled.
             */
            addEventFilter(KeyEvent.KEY_PRESSED) { d ->
                keyboard {
                    logger.info("onKeyPressed: $d")
                    zoomEnabled = d.code == zoomKey
                }
            }
            addEventFilter(KeyEvent.KEY_RELEASED) { d ->
                keyboard {
                    logger.info("onKeyReleased: $d")
                    if (d.code == zoomKey)
                        zoomEnabled = false
                }
            }
        }

        /*
        * the contentBox is being filled with the image, on which the user is supposed to zoom in and out.
        */
        with(contentBox) {
            button("Hauptmenü") {
                action {
                    replaceWith(ToolSelectionView::class)
                }
                prefWidthProperty().bind(root.widthProperty())
                prefHeightProperty().bind(root.heightProperty())
            }

            /*
            * @param imageview: the imageview is initialized with all its settings. Its bound to the root
            * properties width and height, thus, the image scales with the screen size while zooming in and out.
            */
            imageview {
                image = Image(IMAGE_PATH)
                maxWidthProperty().bind(root.widthProperty())
                fitHeightProperty().bind(root.heightProperty())
                isPreserveRatio = true
                isPickOnBounds = true
                isSmooth = false // this does not disable anti-aliasing though :(

                val zoomMapsConfig = configManager.getZoomMapsConfig()
                viewport = Rectangle2D(0.0, 0.0, image.width, image.height)
                logger.info("$zoomMapsConfig")

                /*
                 * @param e: registers the mouseLocation of the zoom. This is being used to save the location
                 * in the config.
                 */
                setOnScroll { e ->
                    if (!zoomEnabled) {
                        return@setOnScroll
                    }

                    val mouseLocation = imageViewToImage(Point2D(e.x, e.y))
                    logger.info("zoomed in at: $mouseLocation.")

                    /*
                     * @param factor: the factor for zooming in and out is calculated.
                     * @param oldScale: the old scale factor is temporarily saved in it.
                     * @param scale: the new scale value is calculated.
                     * @param actualFactor: the new actual Factor is being calculated with the current scale
                     * and the old scale.
                     */
                    val factor = if (e.deltaY > 0) 1.0 / 1.2 else 1.2
                    val oldScale = scale
                    scale = clamp(0.0625, oldScale * factor, 1.0)
                    val actualFactor = scale / oldScale

                    val minX = mouseLocation.x - (mouseLocation.x - viewport.minX) * actualFactor
                    val minY = mouseLocation.y - (mouseLocation.y - viewport.minY) * actualFactor
                    viewport = Rectangle2D(
                        minX,
                        minY,
                        mouseLocation.x + (viewport.maxX - mouseLocation.x) * actualFactor - minX,
                        mouseLocation.y + (viewport.maxY - mouseLocation.y) * actualFactor - minY
                    )

                    logger.info("Scaling around: $mouseLocation, factor: $scale")
                }
            }
        }
    }

    /*
    * @receiver ImageView: extensionFunction to register the ZoomLocation of the Mouse on the receiver ImageView.
    * The function calculates the relative position of the zoom event, because the original size of the image might
    * be distorted by the zoom.
    * @param position: current Position of the Zoom Event
    */
    private fun ImageView.imageViewToImage(position: Point2D) = Point2D(
        viewport.minX + position.x * viewport.width / boundsInLocal.width,
        viewport.minY + position.y * viewport.height / boundsInLocal.height
    )

    /*
    * Needs to be overwritten, to get focus on root and attach eventFilters for the keys.
    */
    override fun onDock() {
        root.requestFocus()
    }
}
