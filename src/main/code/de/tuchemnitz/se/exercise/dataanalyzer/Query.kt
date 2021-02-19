package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.PictureData
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.Gender
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.litote.kmongo.and
import org.litote.kmongo.div
import org.litote.kmongo.eq
import org.litote.kmongo.gte
import org.litote.kmongo.lt
import org.litote.kmongo.lte
import tornadofx.Controller

/**
 * Queries the database for eye tracking data and metadata
 */
class Query : Controller() {

    val userDataCollection: UserDataCollection by inject()
    val codeChartsDataCollection: CodeChartsDataCollection by inject()
    val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    /**
     * Queries for gender distribution among users of specific tools
     */
    fun codeChartsGenderCount(gender: Gender): Int {
        return codeChartsDataCollection.countDocuments(CodeChartsData::currentUser / UserData::gender eq gender)
    }

    fun zoomMapsGenderCount(gender: Gender): Int {
        return zoomMapsDataCollection.countDocuments(ZoomMapsData::currentUser / UserData::gender eq gender)
    }

    /**
     * Queries for image view distribution among users of specific tools
     * @param imagePath: path referring to the image which was used to collect eye tracking data
     */

    fun queryCodeChartsImage(imagePath: String): Int {
        return codeChartsDataCollection.find(
            CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                eq imagePath
        ).count()
    }

    fun queryZoomMapsImage(imagePath: String): Int {
        return zoomMapsDataCollection.countDocuments(
            ZoomMapsData::imagePath
                eq imagePath
        )
    }

    /**
     * Finds all users of specific tool
     */

    fun codeChartsUsers(): List<UserData> {
        return codeChartsDataCollection.find().map {
            it.currentUser
        }.toList()
    }

    fun zoomMapsUsers(): List<UserData> {
        return zoomMapsDataCollection.find().map {
            it.currentUser
        }.toList()
    }

    /**
     * Queries datasets by filter parameters specified by the user of the data client
     * Splits into separate queries and returns the combined result
     * @param queryFilter: contains user specified filter parameters
     */
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

    /**
     * Queries for specific user or age range
     * @param filter: contains user specified filter parameters
     */
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

    /**
     * Queries all datasets collected by the Code Charts tool for optional user defined filter parameters:
     * image, picture view time, user name, age range and gender
     * @param codeChartsFilter: contains user specified filter parameters
     * @param userDataFilter: contains user specified filter parameters
     */
    private fun queryCodeChartsData(
        codeChartsFilter: CodeChartsDataFilter,
        userDataFilter: UserDataFilter
    ): List<CodeChartsData> {
        return codeChartsDataCollection.find(
            and(
                (
                    CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::imagePath
                        eq codeChartsFilter.imagePath.value
                    ).takeIf { codeChartsFilter.imagePath.taken },
                (
                    CodeChartsData::codeChartsConfig / CodeChartsConfig::pictures / PictureData::pictureViewTime
                        eq codeChartsFilter.pictureViewTime.value
                    ).takeIf { codeChartsFilter.pictureViewTime.taken },
                (
                    CodeChartsData::currentUser / UserData::firstName eq
                        userDataFilter.firstName.value
                    ).takeIf { userDataFilter.firstName.taken },
                (
                    CodeChartsData::currentUser / UserData::lastName eq
                        userDataFilter.lastName.value
                    ).takeIf { userDataFilter.lastName.taken },
                (
                    CodeChartsData::currentUser / UserData::age gte
                        (userDataFilter.age.value!!.minimumAge ?: 0)
                    ).takeIf { userDataFilter.age.taken },
                (
                    CodeChartsData::currentUser / UserData::age lt
                        (userDataFilter.age.value.maximumAge ?: 0)
                    ).takeIf { userDataFilter.age.taken },
                (
                    CodeChartsData::currentUser / UserData::gender eq
                        userDataFilter.gender.value
                    ).takeIf { userDataFilter.firstName.taken }
            )
        ).toList()
    }

    /**
     * Queries all datasets collected by the Zoom Maps tool for optional user defined filter parameters:
     * image, picture view time, user name, age range and gender
     * @param zoomMapsFilter: contains user specified filter parameters
     * @param userDataFilter: contains user specified filter parameters
     */

    private fun queryZoomMapsData(
        zoomMapsFilter: ZoomMapsDataFilter,
        userDataFilter: UserDataFilter
    ): List<ZoomMapsData> {
        return zoomMapsDataCollection.find(
            and(
                (ZoomMapsData::zoomKey eq zoomMapsFilter.keyCode.value).takeIf { zoomMapsFilter.keyCode.taken },
                (ZoomMapsData::imagePath eq zoomMapsFilter.imagePath.value).takeIf { zoomMapsFilter.imagePath.taken },
                (
                    ZoomMapsData::currentUser / UserData::firstName eq
                        userDataFilter.firstName.value
                    ).takeIf { userDataFilter.firstName.taken },
                (
                    ZoomMapsData::currentUser / UserData::lastName eq
                        userDataFilter.lastName.value
                    ).takeIf { userDataFilter.firstName.taken },
                (
                    ZoomMapsData::currentUser / UserData::age gte
                        (userDataFilter.age.value!!.minimumAge ?: 0)
                    ).takeIf { userDataFilter.age.taken },
                (
                    ZoomMapsData::currentUser / UserData::age lt
                        (userDataFilter.age.value.maximumAge ?: 0)
                    ).takeIf { userDataFilter.age.taken },

                (
                    ZoomMapsData::currentUser / UserData::gender eq
                        (userDataFilter.gender.value)
                    ).takeIf { userDataFilter.gender.taken }
            )
        ).toList()
    }
}
