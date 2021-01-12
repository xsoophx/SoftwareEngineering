package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.AbstractCollection
import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.configs.CodeChartsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.CodeChartsConfigCollection
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.collections.CodeChartsDataCollection
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
    private val codeChartsConfigCollection: CodeChartsConfigCollection by inject()
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
                (UserData::age eq filter.age.value).takeIf { filter.age.taken },
            )
        ).toList()
    }

    private fun queryCodeChartsConfig(filter: CodeChartsConfigFilter): List<CodeChartsConfig>{
        return codeChartsConfigCollection.find(
            and(
                (CodeChartsConfig::minViewsToSubdivide eq filter.minViewsToSubdivide.value).takeIf {filter.minViewsToSubdivide.taken},
                (CodeChartsConfig::stringCharacters eq filter.stringCharacters.value).takeIf {filter.stringCharacters.taken},
                (CodeChartsConfig::pictures eq filter.pictures.value).takeIf {filter.pictures.taken}
            )
        ).toList()
    }
}
