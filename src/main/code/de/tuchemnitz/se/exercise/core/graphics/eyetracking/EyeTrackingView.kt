package de.tuchemnitz.se.exercise.core.graphics.eyetracking

import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.cameras
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.captureImage
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.selectCamera
import javafx.application.Platform
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.image.ImageView
import tornadofx.View
import tornadofx.combobox
import tornadofx.hbox
import tornadofx.imageview
import tornadofx.label
import tornadofx.singleAssign
import tornadofx.vbox
import tornadofx.getValue
import tornadofx.setValue

/**
 * This view is responsible for all the camera based Eye tracking depicted.
 */
class EyeTrackingView : View(title = "Eye-Tracking") {
    private val videoCaptureIndexProperty = SimpleIntegerProperty(this, "videoCaptureIndex", 0)
    private var videoCaptureIndex: Int by videoCaptureIndexProperty

    private var imageView: ImageView by singleAssign()

    override val root: Parent = vbox {
        hbox(spacing = 10.0, alignment = Pos.CENTER) {
            label(text = "Video device:")

            combobox(
                property = videoCaptureIndexProperty,
                values = (cameras.indices).toList()
            )
        }

        imageView = imageview {
            videoCaptureIndexProperty.addListener { _ ->
                resizeToCamera()

                selectCamera(videoCaptureIndex)
                image = captureImage()
            }
        }
    }

    /**
     * The stage is being overwritten, right before its shown, to resize and capture the image.
     */
    override fun onBeforeShow() {
        super.onBeforeShow()

        Platform.runLater {
            imageView.resizeToCamera()

            selectCamera(videoCaptureIndex)
            imageView.image = captureImage()
        }
    }

    /**
     * The Window, the user is currently looking at is being resized to the cameras resolution.
     *  @receiver shows the current frame of the camera.
     */
    private fun ImageView.resizeToCamera() {
        val (width, height) = cameras[videoCaptureIndex]
        fitWidth = width
        fitHeight = height

        currentStage?.sizeToScene()
    }
}