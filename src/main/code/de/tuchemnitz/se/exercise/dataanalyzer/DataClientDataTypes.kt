package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.Image
import de.tuchemnitz.se.exercise.persist.ImageCollection.Companion.defaultImagePath
import de.tuchemnitz.se.exercise.persist.data.Gender
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue

/**
 * Holds the information of the query created by the user.
 */
class DataClientQuery(
    codeChartsActivated: Boolean = true,
    zoomMapsActivated: Boolean = true,
    gender: Gender = Gender.Unselected,
    minimumAge: Int = 0,
    maximumAge: Int = 120,
    image: Image = Image(name = "Chameleon", path = defaultImagePath("Chameleon"))

) {
    /**
     * Injection from the responsible Model, to get data into the class.
     */
    val codeChartsProperty = SimpleBooleanProperty(this, "codeChartsActivated", codeChartsActivated)
    var codeCharts by codeChartsProperty

    val zoomMapsProperty = SimpleBooleanProperty(this, "zoomMapsActivated", zoomMapsActivated)
    var zoomMaps by zoomMapsProperty

    val minimumAgeProperty = SimpleIntegerProperty(this, "age", minimumAge)
    var minimumAge by minimumAgeProperty

    val maximumAgeProperty = SimpleIntegerProperty(this, "age", maximumAge)
    var maximumAge by maximumAgeProperty

    val genderProperty = SimpleObjectProperty<Gender>(this, "gender", gender)
    var gender: Gender? by genderProperty

    val imageProperty = SimpleObjectProperty<Image>(this, "imagePath", image)
    var image: Image by imageProperty
}

/**
 * Responsible for injection into the [DataClientQuery] class
 */
class DataClientQueryModel(dataClientQuery: DataClientQuery = DataClientQuery()) :
    ItemViewModel<DataClientQuery>(dataClientQuery) {

    /**
     * Parameters are accessed in the Main View, to map User input to the responsible [DataClientQuery]
     */
    val codeChartsActivated = bind(DataClientQuery::codeChartsProperty)
    val zoomMapsActivated = bind(DataClientQuery::zoomMapsProperty)
    val gender = bind(DataClientQuery::genderProperty)
    val maximumAge = bind(DataClientQuery::maximumAgeProperty)
    val minimumAge = bind(DataClientQuery::minimumAgeProperty)
    val image = bind(DataClientQuery::imageProperty)
}
