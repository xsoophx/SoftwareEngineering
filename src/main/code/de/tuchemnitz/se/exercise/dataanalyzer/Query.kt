package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.litote.kmongo.and
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.gt
import org.litote.kmongo.gte
import org.litote.kmongo.lt
import org.litote.kmongo.lte
import tornadofx.Controller

class Query : Controller() {

    data class QueryFilter(
        val userDataFilter: Filter<UserDataFilter>?,
        val codeChartsDataFilter: Filter<CodeChartsDataFilter>?,
        val zoomMapsFilter: Filter<ZoomMapsDataFilter>?,
    )

    val userDataCollection: UserDataCollection by inject()
    val codeChartsDataCollection: CodeChartsDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    private val dummyUserDataFilter =
        Filter<UserDataFilter>(
            taken = false,
            value = UserDataFilter(
                firstName = Filter(taken = false, value = ""),
                lastName = Filter(taken = false, value = ""),
                age = Filter(taken = false, value = Age(minimumAge = 0, maximumAge = 0)),
                gender = Filter(taken = false, value = Gender.Male)
            )
        )

    fun queryAllElementsSeparately(queryFilter: QueryFilter): List<IPersist> {
        val list = mutableListOf<IPersist>()
        val userDataFilter = queryFilter.userDataFilter ?: dummyUserDataFilter

        if (queryFilter.userDataFilter?.taken == true)
            list.addAll(queryUserData(queryFilter.userDataFilter.value))

        if (queryFilter.codeChartsDataFilter?.taken == true)
            list.addAll(
                queryCodeChartsData(
                    queryFilter.codeChartsDataFilter.value,
                    userDataFilter.value
                )
            )

        if (queryFilter.zoomMapsFilter?.taken == true)
            list.addAll(queryZoomMapsData(queryFilter.zoomMapsFilter.value, userDataFilter.value))

        return list
    }

    private fun queryUserData(filter: UserDataFilter): List<UserData> {
        return userDataCollection.find(
            and(
                (UserData::firstName eq filter.firstName.value).takeIf { filter.firstName.taken },
                (UserData::lastName eq filter.lastName.value).takeIf { filter.lastName.taken },
                (UserData::age gte (filter.age.value!!.minimumAge ?: 0)).takeIf { filter.age.taken },
                (UserData::age lte (filter.age.value.minimumAge ?: 0)).takeIf { filter.age.taken }
            )
        ).toList()
    }

    private fun queryCodeChartsData(
        codeChartsFilter: CodeChartsDataFilter,
        userDataFilter: UserDataFilter
    ): List<CodeChartsData> {
        return codeChartsDataCollection.find(
            and(
                (CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                    eq codeChartsFilter.imagePath.value).takeIf { codeChartsFilter.imagePath.taken },
                (CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::pictureViewTime
                    eq codeChartsFilter.pictureViewTime.value).takeIf { codeChartsFilter.pictureViewTime.taken },
                (CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::matrixViewTime
                    eq codeChartsFilter.matrixViewTime.value).takeIf { codeChartsFilter.matrixViewTime.taken },
                (CodeChartsData::currentUser / UserData::firstName eq
                    userDataFilter.firstName.value).takeIf { userDataFilter.firstName.taken },
                (CodeChartsData::currentUser / UserData::lastName eq
                    userDataFilter.lastName.value).takeIf { userDataFilter.firstName.taken },
                (CodeChartsData::currentUser / UserData::age gte
                    (userDataFilter.age.value!!.minimumAge ?: 0)).takeIf { userDataFilter.age.taken },
                (CodeChartsData::currentUser / UserData::age lt
                    (userDataFilter.age.value.maximumAge ?: 0)).takeIf { userDataFilter.age.taken },
            )
        ).toList()
    }

    private fun queryZoomMapsData(
        zoomMapsFilter: ZoomMapsDataFilter,
        userDataFilter: UserDataFilter
    ): List<ZoomMapsData> {
        return zoomMapsDataCollection.find(
            and(
                (ZoomMapsData::zoomKey eq zoomMapsFilter.keyCode.value).takeIf { zoomMapsFilter.keyCode.taken },
                (ZoomMapsData::imagePath eq zoomMapsFilter.imagePath.value).takeIf { zoomMapsFilter.imagePath.taken },
                (ZoomMapsData::currentUser / UserData::firstName eq
                    userDataFilter.firstName.value).takeIf { userDataFilter.firstName.taken },
                (CodeChartsData::currentUser / UserData::lastName eq
                    userDataFilter.lastName.value).takeIf { userDataFilter.firstName.taken },
                (CodeChartsData::currentUser / UserData::age gte
                    (userDataFilter.age.value!!.minimumAge ?: 0)).takeIf { userDataFilter.age.taken },
                (CodeChartsData::currentUser / UserData::age lt
                    (userDataFilter.age.value.maximumAge ?: 0)).takeIf { userDataFilter.age.taken },
            )
        ).toList()
    }
}
