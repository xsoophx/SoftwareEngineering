package de.tuchemnitz.se.exercise.core.graphics.eyetracking

import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.CAMERAS
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.captureImage
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.selectCamera
import de.tuchemnitz.se.exercise.core.graphics.system.MainBarView
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.geometry.Rectangle2D
import javafx.scene.image.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.javafx.JavaFx
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tornadofx.combobox
import tornadofx.getValue
import tornadofx.hbox
import tornadofx.imageview
import tornadofx.label
import tornadofx.setValue
import tornadofx.singleAssign
import kotlin.coroutines.CoroutineContext

/**
 * This view is responsible for all the camera based Eye tracking depicted.
 */
class EyeTrackingView : MainBarView("Eye-Tracking"), CoroutineScope {
    override val coroutineContext: CoroutineContext = Dispatchers.JavaFx

    private var cameraChanged = true
    private var imageGrabber = createImageGrabber()

    private val videoCaptureIndexProperty = SimpleIntegerProperty(this, "videoCaptureIndex", 0)
    private var videoCaptureIndex: Int by videoCaptureIndexProperty

    private var imageView: ImageView by singleAssign()

    init {
        with(contentBox) {
            hbox(spacing = 10.0, alignment = Pos.CENTER) {
                label(text = "Video device:")

                combobox(
                    property = videoCaptureIndexProperty,
                    values = (CAMERAS.indices).toList()
                )
            }

            hbox(alignment = Pos.CENTER) {
                imageView = imageview {
                    isPreserveRatio = true
                }
            }
        }

        root.widthProperty().addListener { _ ->
            resizeImage()
        }
        root.heightProperty().addListener { _ ->
            resizeImage()
        }
        videoCaptureIndexProperty.addListener { _ -> cameraChanged = true }
    }

    fun launchImageGrabber() {
        imageGrabber.start()
    }

    override fun onDelete() {
        imageGrabber.cancel()
        imageGrabber = createImageGrabber()

        super.onDelete()
    }

    private fun createImageGrabber() = launch(context = Dispatchers.Default, start = CoroutineStart.LAZY) {
        while (isActive) {
            delay((1000L / (CAMERAS[videoCaptureIndex].framesPerSecond.takeIf { it != 0.0 } ?: 1.0)).toLong())
            if (cameraChanged) {
                imageView.adjustViewport()
                selectCamera(videoCaptureIndex)
                cameraChanged = false
            }
            val image = captureImage()
            withContext(Dispatchers.JavaFx) {
                imageView.image = image
            }
        }
    }

    private suspend fun ImageView.adjustViewport() = withContext(Dispatchers.JavaFx) {
        val (width, height) = CAMERAS[videoCaptureIndex]
        viewport = Rectangle2D(0.0, 0.0, width, height)
    }

    private fun resizeImage() {
        if (root.width == 0.0 || root.height == 0.0) {
            return
        }

        imageView.fitWidth = root.width
        imageView.fitHeight = root.height - 120.0
    }
}