package de.tuchemnitz.se.exercise.core.graphics.eyetracking

import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.cameras
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.captureImage
import de.tuchemnitz.se.exercise.core.graphics.eyetracking.OpenCVHelpers.selectCamera
import javafx.application.Platform
import javafx.beans.property.SimpleIntegerProperty
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.image.ImageView
import tornadofx.*

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

    override fun onBeforeShow() {
        super.onBeforeShow()

        Platform.runLater {
            imageView.resizeToCamera()

            selectCamera(videoCaptureIndex)
            imageView.image = captureImage()
        }
    }

    private fun ImageView.resizeToCamera() {
        val (width, height) = cameras[videoCaptureIndex]
        fitWidth = width
        fitHeight = height

        currentStage?.sizeToScene()
    }
}