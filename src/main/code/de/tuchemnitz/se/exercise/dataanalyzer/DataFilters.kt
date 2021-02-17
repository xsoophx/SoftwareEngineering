package de.tuchemnitz.se.exercise.dataanalyzer

import de.tuchemnitz.se.exercise.persist.data.Gender

data class QueryFilter(
    val userDataFilter: Filter<UserDataFilter>?,
    val codeChartsDataFilter: Filter<CodeChartsDataFilter>?,
    val zoomMapsFilter: Filter<ZoomMapsDataFilter>?,
)

val dummyUserDataFilter =
    Filter<UserDataFilter>(
        taken = false,
        value = UserDataFilter(
            firstName = Filter(taken = false, value = ""),
            lastName = Filter(taken = false, value = ""),
            age = Filter(taken = false, value = Age(minimumAge = 0, maximumAge = 0)),
            gender = Filter(taken = false, value = Gender.Male)
        )
    )
