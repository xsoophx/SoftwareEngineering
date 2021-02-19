package de.tuchemnitz.se.exercise.core.graphics.eyetracking

import javafx.scene.image.Image
import org.opencv.core.Mat
import org.opencv.core.MatOfByte
import org.opencv.core.MatOfRect
import org.opencv.core.Scalar
import org.opencv.core.Size
import org.opencv.imgcodecs.Imgcodecs.imencode
import org.opencv.imgproc.Imgproc
import org.opencv.objdetect.CascadeClassifier
import org.opencv.objdetect.Objdetect
import org.opencv.videoio.VideoCapture
import org.opencv.videoio.Videoio
import org.opencv.videoio.Videoio.CAP_PROP_FRAME_HEIGHT
import org.opencv.videoio.Videoio.CAP_PROP_FRAME_WIDTH
import org.slf4j.LoggerFactory
import java.io.ByteArrayInputStream
import java.nio.file.Paths
import kotlin.math.round

/**
 * Main logic for the Eye Tracking View is implemented here.
 */
object OpenCVHelpers {
    private const val CLASSIFIER_PATH = "src/main/code/de/tuchemnitz/se/exercise/core/graphics/eyetracking/classifiers/haarcascade_eye.xml"
    private val logger = LoggerFactory.getLogger(OpenCVHelpers::class.java)

    private val capture = VideoCapture()

    private val classifier = CascadeClassifier(CLASSIFIER_PATH).apply {
        if (empty()) {
            val currentWorkingDir = Paths.get("").toAbsolutePath().toString()
            logger.warn("Classifier \"$CLASSIFIER_PATH\" could not be loaded. Working directory: $currentWorkingDir")
        }
    }

    val CAMERAS: List<CameraInfo> by lazy {
        val results = mutableListOf<CameraInfo>()
        var count = 0
        while (capture.open(count)) {
            ++count
            val resolution = CameraInfo(
                width = capture[CAP_PROP_FRAME_WIDTH],
                height = capture[CAP_PROP_FRAME_HEIGHT],
                framesPerSecond = capture[Videoio.CAP_PROP_FPS]
            )
            results.add(resolution)
            logger.debug("Resolution of video capture device #$count: $resolution")
        }
        logger.debug("Found $count video capture devices!")
        return@lazy results
    }

    /*
* Data class, which is holding the Resolution of the camera.
*/
    data class CameraInfo(val width: Double = -1.0, val height: Double = -1.0, val framesPerSecond: Double = 0.0)

    /**
     * Different cameras can be selected.
     * [index] number of the Camera selected.
     */
    fun selectCamera(index: Int) {
        capture.open(index)
    }

    private fun markEyes(frame: Mat) {
        if (!classifier.empty()) {
            val greyFrame = Mat()
            Imgproc.cvtColor(frame, greyFrame, Imgproc.COLOR_BGR2GRAY)
            Imgproc.equalizeHist(greyFrame, greyFrame)

            val minFaceSize = round(greyFrame.rows().toDouble() * 0.2)
            val faces = MatOfRect()

            classifier.detectMultiScale(
                greyFrame, faces, 1.1, 2,
                Objdetect.CASCADE_SCALE_IMAGE, Size(minFaceSize, minFaceSize), Size()
            )

            for (face in faces.toArray()) {
                Imgproc.rectangle(frame, face.tl(), face.br(), Scalar(0.0, 255.0, 0.0, 255.0), 3)
            }
        }
    }

    /**
     * One frame is being captured and returned as an Image.
     */
    fun captureImage(): Image? = Mat()
        .takeIf(capture::read)
        ?.apply(this::markEyes)
        ?.let { frame -> MatOfByte().also { buffer -> imencode(".png", frame, buffer) } }
        ?.let { image -> Image(ByteArrayInputStream(image.toArray())) }
}
