package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import javafx.scene.input.KeyCode
import tornadofx.Controller

/**
 * Holds main logic of the data analyst application
 * Handles the flow of data
 */

class DataAnalyst : Controller() {

    private val query: Query by inject()

    /**
     * Takes filter parameters which the user input
     * Queries database collection depending on the tool asked for by the user
     * Returns a list of datasets which match the given filters
     */

    fun getData(dataClientQuery: DataClientQuery): List<IPersist> =
        query.queryAllElementsSeparately(dataClientQuery.createQueryFilter())

    private fun DataClientQuery.createQueryFilter() = Query.QueryFilter(
        userDataFilter = userDataFilter(),
        codeChartsDataFilter = codeChartsDataFilter(),
        zoomMapsFilter = zoomMapsDataFilter(),
        pictureDataFilter = pictureDataFilter()
    )

    private fun DataClientQuery.userDataFilter() = Filter<UserDataFilter>(
        taken = maximumAge != 120 || minimumAge != 0 || gender != null,
        value = UserDataFilter(
            firstName = Filter(taken = false, value = ""),
            lastName = Filter(taken = false, value = ""),
            age = Filter(
                taken = maximumAge == 120 && minimumAge == 0,
                value = Age(minimumAge = minimumAge, maximumAge = maximumAge)
            ),
            gender = Filter(taken = gender != null, value = gender)
        )
    )

    private fun DataClientQuery.codeChartsDataFilter() = Filter<CodeChartsDataFilter>(
        taken = codeCharts,
        value = CodeChartsDataFilter(
            pictureViewTime = Filter(taken = false, value = -1),
            matrixViewTime = Filter(taken = false, value = -1),
        )
    )

    private fun DataClientQuery.zoomMapsDataFilter() = Filter<ZoomMapsDataFilter>(
        taken = zoomMaps,
        value = ZoomMapsDataFilter(
            keyCode = Filter(taken = false, value = KeyCode.A)
        )
    )

    private fun DataClientQuery.pictureDataFilter() = Filter<PictureDataFilter>(
        taken = false,
        value = PictureDataFilter(
            imagePath = Filter(taken = false, value = "")
        )
    )
}
