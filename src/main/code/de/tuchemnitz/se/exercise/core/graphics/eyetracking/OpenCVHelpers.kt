package de.tuchemnitz.se.exercise.core.graphics.eyetracking

import javafx.scene.image.Image
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.imgcodecs.Imgcodecs.imencode
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio.*
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream

object OpenCVHelpers {
    private val logger = LoggerFactory.getLogger(OpenCVHelpers::class.java)

    private val capture = VideoCapture()

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

    data class Resolution(val width: Double = -1.0, val height: Double = -1.0)

    fun selectCamera(index: Int) {
        capture.open(index)
    }

    fun captureImage(): Image {
        val frame = Mat()
        capture.read(frame)
        val buffer = MatOfByte()
        imencode(".png", frame, buffer)
        return Image(ByteArrayInputStream(buffer.toArray()))
    }
}