package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.collections.UserDataCollection
import de.tuchemnitz.se.exercise.persist.data.collections.ZoomMapsDataCollection
import org.bson.conversions.Bson
import org.litote.kmongo.and
import org.litote.kmongo.eq
import org.slf4j.LoggerFactory

class Query : AbstractCollection<IPersist>(IPersist::class) {

    data class UserFilter(
        val userDataFilter: Filter<UserDataFilter>,
        val codeChartsDataFilter: Filter<CodeChartsData>
    )

    companion object {
        val logger = LoggerFactory.getLogger(this::class.java)
    }

    private val userDataCollection: UserDataCollection by inject()
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
                (UserData::lastName eq filter.surName.value).takeIf { filter.surName.taken },
                (UserData::age eq filter.age.value).takeIf { filter.age.taken },
            )
        ).toList()
    }
}
