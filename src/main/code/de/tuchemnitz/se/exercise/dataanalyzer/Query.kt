package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.litote.kmongo.and
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.gt
import org.litote.kmongo.lt
import tornadofx.Controller

class Query : Controller() {

    data class QueryFilter(
        val userDataFilter: Filter<UserDataFilter>?,
        val codeChartsDataFilter: Filter<CodeChartsDataFilter>?,
        val zoomMapsFilter: Filter<ZoomMapsDataFilter>?,
        val pictureDataFilter: Filter<PictureDataFilter>?
    )

    val userDataCollection: UserDataCollection by inject()
    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    fun queryAllElementsSeparately(queryFilter: QueryFilter): List<IPersist> {
        val list = mutableListOf<IPersist>()
        if (queryFilter.userDataFilter?.taken == true)
            list.addAll(queryUserData(queryFilter.userDataFilter.value))

        if (queryFilter.codeChartsDataFilter?.taken == true)
            list.addAll(queryCodeChartsData(queryFilter.codeChartsDataFilter.value))

        if (queryFilter.zoomMapsFilter?.taken == true)
            list.addAll(queryZoomMapsData(queryFilter.zoomMapsFilter.value))

        return list
    }

    private fun queryUserData(filter: UserDataFilter): List<UserData> {
        return userDataCollection.find(
            and(
                (UserData::firstName eq filter.firstName.value).takeIf { filter.firstName.taken },
                (UserData::lastName eq filter.lastName.value).takeIf { filter.lastName.taken },
                (UserData::age gt (filter.age.value!!.minimumAge?.minus(1) ?: 0)).takeIf { filter.age.taken },
                (UserData::age lt (filter.age.value.minimumAge?.plus(1) ?: 0)).takeIf { filter.age.taken }
            )
        ).toList()
    }

    private fun queryCodeChartsData(filter: CodeChartsDataFilter): List<CodeChartsConfig> {
        return codeChartsConfigCollection.find(
            and(
                (CodeChartsConfig::pictures / PictureData::pictureViewTime eq filter.pictureViewTime.value).takeIf { filter.pictureViewTime.taken },
                (CodeChartsConfig::pictures / PictureData::matrixViewTime eq filter.matrixViewTime.value).takeIf { filter.matrixViewTime.taken }
            )
        ).toList()
    }

    private fun queryZoomMapsData(filter: ZoomMapsDataFilter): List<ZoomMapsData> {
        return zoomMapsDataCollection.find(
            and(
                (ZoomMapsData::zoomKey eq filter.keyCode.value).takeIf { filter.keyCode.taken }
            )
        ).toList()
    }
}
