package de.tuchemnitz.se.exercise.dataanalyzer
import java.awt.Dimension
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class DataRenderHeatMap : DataRenderer(), IMethod {
    override fun render(coordinates: Coordinates): Boolean {

        val size = coordinates.scaledImageSize
       // val imagePath = coordinates.picturePath.toString()

        // create new image and resize it to the size the user scaled to
        var image: Image = ImageIO.read(File("src/main/code/de/tuchemnitz/se/exercise/dataanalyzer/kitten.jpeg"))
       // image = image.getScaledInstance(size.x.toInt(), size.y.toInt(), Image.SCALE_DEFAULT)
        val buffered = image as BufferedImage


        for (x in coordinates.xStart.toInt() until coordinates.xEnd.toInt()) {
            for (y in coordinates.yStart.toInt() until coordinates.yEnd.toInt()) {
                //  color
                buffered.setRGB(x, y, 0xfffccc)
            }
        }
        ImageIO.write(buffered, "BMP", File("src/main/resources/output.bmp"))

        // if successful
        return true
    }
}
