package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.IData
import org.litote.kmongo.Id
import org.litote.kmongo.newId

// need to get data from database into this format i.e. extract these values
class DummyData(
    override val _id: Id<out IPersist>,
    // var imagePath: Path,
    var imagePath: String,
    var scaledImageSize: Dimension,
    var interval: Interval2D,
) : IData

data class Interval2D(
    val xMin: Double,
    val xMax: Double,
    val yMin: Double,
    val yMax: Double
)

data class Dimension(
    val x: Double,
    val y: Double
)

data class UserData(
    val firstName: String,
    val surName: String,
    val age: Int,
    override val _id: Id<out IPersist> = newId()
) : IData
