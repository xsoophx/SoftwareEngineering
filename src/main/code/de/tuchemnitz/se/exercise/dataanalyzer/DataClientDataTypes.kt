package de.tuchemnitz.se.exercise.dataanalyzer

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.ItemViewModel
import tornadofx.getValue
import tornadofx.setValue

class DataClientQuery(
    codeChartsActivated: Boolean = false,
    zoomMapsActivated: Boolean = false,
    activeMethod: Method? = null,
    gender: Gender? = null,
    age: Age? = null
) {
    val codeChartsProperty = SimpleBooleanProperty(this, "codeChartsActivated", codeChartsActivated)
    var codeCharts by codeChartsProperty

    val zoomMapsProperty = SimpleBooleanProperty(this, "codeChartsActivated", zoomMapsActivated)
    var zoomMaps by zoomMapsProperty

    val ageProperty = SimpleObjectProperty<Age>(this, "age", age)
    var age: Age? by ageProperty

    val genderProperty = SimpleObjectProperty<Gender>(this, "gender", gender)
    var gender: Gender? by genderProperty

    val methodProperty = SimpleObjectProperty<Method>(this, "method", activeMethod)
    var method: Method? by methodProperty
}

class DataClientQueryModel(dataClientQuery: DataClientQuery = DataClientQuery()) :
    ItemViewModel<DataClientQuery>(dataClientQuery) {

    val codeChartsActivated = bind(DataClientQuery::codeChartsProperty)
    val zoomMapsActivated = bind(DataClientQuery::zoomMapsProperty)
    val gender = bind(DataClientQuery::genderProperty)
    val age = bind(DataClientQuery::ageProperty)
    val method = bind(DataClientQuery::methodProperty)
}
