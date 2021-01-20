package de.tuchemnitz.se.exercise.dataanalyzer

/**
 * Creates a visual representation of the subject's eye position
 */
class DataRenderHeatMap : DataRenderer(), IMethod
/*
{

    /**
     * Creates a new image containing the image which the subject was looking at
     * with the eye position marked in color
     * returns true if new image was successfully created
     */

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
*/
