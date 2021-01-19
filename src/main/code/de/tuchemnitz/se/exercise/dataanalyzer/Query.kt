package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.bson.conversions.Bson
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.litote.kmongo.gte
import org.litote.kmongo.lte
import org.slf4j.LoggerFactory

class Query : AbstractCollection<IPersist>(IPersist::class) {

    data class UserFilter(
        val userDataFilter: Filter<UserDataFilter>,
        val codeChartsDataFilter: Filter<CodeChartsDataFilter>
    )

    companion object {
        val logger = LoggerFactory.getLogger(this::class.java)
    }

    val userDataCollection: UserDataCollection by inject()
    private val zoomMapsDataCollection: ZoomMapsDataCollection by inject()

    fun query(filter: Bson) {
        // should query database :
        // if array.length > 1: database.findMany(filter)
        // else database.findOne(filter)
    }

    fun queryAllElementsSeparately(userFilter: UserFilter): List<IPersist> {
        val list = mutableListOf<IPersist>()
        if (userFilter.userDataFilter.taken)
            list.addAll(queryUserData(userFilter.userDataFilter.value))

        return list
    }

    private fun queryUserData(filter: UserDataFilter): List<UserData> {
        return userDataCollection.find(
            and(
                (UserData::firstName eq filter.firstName.value).takeIf { filter.firstName.taken },
                (UserData::lastName eq filter.lastName.value).takeIf { filter.lastName.taken },
                (UserData::age gte filter.ageRangeLower.value).takeIf { filter.ageRangeLower.taken },
                (UserData::age lte filter.ageRangeUpper.value).takeIf { filter.ageRangeUpper.taken },
                (UserData::age eq filter.gender.value).takeIf { filter.gender.taken },
            )
        ).toList()
    }
}
