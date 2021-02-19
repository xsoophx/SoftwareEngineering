package de.tuchemnitz.se.exercise.core.graphics.eyetracking

import javafx.scene.image.Image
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs.imencode
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT
import org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream

/**
 * Main logic for the Eye Tracking View is implemented here.
 */
object OpenCVHelpers {
    private val logger = LoggerFactory.getLogger(OpenCVHelpers::class.java)

    /*
    * A new video capture is being instantiated.
    */
    private val capture = VideoCapture()

    /*
    * All cameras and resolutions are being searched for and written into the logger.
    * In the end the user will be able to select from the found cameras.
    */
    val cameras: List<Resolution> by lazy {
        val results = mutableListOf<Resolution>()
        var count = 0
        while (capture.open(count)) {
            ++count
            val resolution = Resolution(width = capture[CAP_PROP_FRAME_WIDTH], height = capture[CAP_PROP_FRAME_HEIGHT])
            results.add(resolution)
            logger.debug("Resolution of video capture device #$count: $resolution")
        }
        logger.debug("Found $count video capture devices!")
        return@lazy results
    }

    /*
    * Data class, which is holding the Resolution of the camera.
    */
    data class Resolution(val width: Double = -1.0, val height: Double = -1.0)

    /**
     * Different cameras can be selected.
     * [index] number of the Camera selected.
     */
    fun selectCamera(index: Int) {
        capture.open(index)
    }

    /**
     * One frame is being captured and returned as an Image.
     */
    fun captureImage(): Image {
        val frame = Mat()
        capture.read(frame)
        val buffer = MatOfByte()
        imencode(".png", frame, buffer)
        return Image(ByteArrayInputStream(buffer.toArray()))
    }
}