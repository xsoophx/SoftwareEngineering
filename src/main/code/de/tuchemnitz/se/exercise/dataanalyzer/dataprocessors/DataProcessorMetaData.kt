package de.tuchemnitz.se.exercise.dataanalyzer.dataprocessors

import de.tuchemnitz.se.exercise.persist.IPersist
import de.tuchemnitz.se.exercise.persist.data.CodeChartsData
import de.tuchemnitz.se.exercise.persist.data.UserData
import de.tuchemnitz.se.exercise.persist.data.ZoomMapsData

class DataProcessorMetaData : DataProcessor<UserData>() {

    override fun process(data: List<IPersist>): List<UserData> {
        return data.mapNotNull { persistable ->
            when (persistable) {
                is UserData -> persistable
                is CodeChartsData -> persistable.currentUser
                is ZoomMapsData -> persistable.currentUser
                else -> null
            }
        }.toList()
    }
}