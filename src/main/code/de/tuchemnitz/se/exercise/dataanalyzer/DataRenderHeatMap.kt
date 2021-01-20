package de.tuchemnitz.se.exercise.dataanalyzer
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

/**
 * Creates a visual representation of the subject's eye position
 */
class DataRenderHeatMap : DataRenderer() {
    private val colorList: MutableList<Int> = mutableListOf()
    /**
     * Creates a new image containing the image which the subject was looking at
     * with the eye position marked in color
     * returns true if new image was successfully created
     */

    override fun render(coordinates: Coordinates): Boolean {

        // val size = coordinates.scaledImageSize
        // val imagePath = coordinates.picturePath.toString()

        // create new image and resize it to the size the user scaled to
        var image: Image = ImageIO.read(File("src/main/code/de/tuchemnitz/se/exercise/dataanalyzer/kitten.jpeg"))
        // image = image.getScaledInstance(size.x.toInt(), size.y.toInt(), Image.SCALE_DEFAULT)
        val buffered = image as BufferedImage

        // val bmImg: Bitmap = BitmapFactory.decodeFile("src/main/code/de/tuchemnitz/se/exercise/dataanalyzer/kitten.jpeg")

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

    override fun renderMany(coordinatesList: MutableList<Coordinates>): Boolean {
        initiateColorList()
        // val size = coordinates[0].scaledImageSize
        // val imagePath = coordinates.picturePath.toString()

        // create new image and resize it to the size the user scaled to
        // var image: Image = ImageIO.read(File("src/main/code/de/tuchemnitz/se/exercise/dataanalyzer/kitten.jpeg"))
        // image = image.getScaledInstance(size.x.toInt(), size.y.toInt(), Image.SCALE_DEFAULT)

        // val buffered = image as BufferedImage

        val imagePath = "src/main/code/de/tuchemnitz/se/exercise/dataanalyzer/kitten.jpeg"
        var myPicture = ImageIO.read(File(imagePath)) as BufferedImage
        myPicture = BufferedImage( 400, 600, myPicture.type)
        println(coordinatesList)
        // var resized = resize(buffered, 300, 200)

        for (coordinates in coordinatesList) {
            val color = colorList.removeAt(0)
            println(color)

            for (x in coordinates.xStart.toInt() until coordinates.xEnd.toInt()) {
                for (y in coordinates.yStart.toInt() until coordinates.yEnd.toInt()) {
                    //  new color color
                    myPicture.setRGB(x, y, color)
                }
            }
        }

        ImageIO.write(myPicture, "BMP", File("src/main/resources/output.bmp"))

        // if successful
        return true
    }

    private fun initiateColorList() {
        colorList.add(0x33ff35)
        colorList.add(0xff5733)
        colorList.add(0x900C3f)
        colorList.add(0x2980b9)
        colorList.add(0x0e6251)
    }

    private fun resize(img: BufferedImage, height: Int, width: Int): BufferedImage {
        val tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH)
        val resized = BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
        val g2d = resized.createGraphics()
        g2d.drawImage(tmp, 0, 0, null)
        g2d.dispose()
        return resized
    }
}
