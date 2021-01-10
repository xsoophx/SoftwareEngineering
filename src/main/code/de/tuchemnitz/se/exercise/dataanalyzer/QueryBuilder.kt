package de.tuchemnitz.se.exercise.dataanalyzer

import org.litote.kmongo.KMongo

class QueryBuilder(tool: String) {

    companion object {
        private val client = KMongo.createClient()
        private val database = client.getDatabase("test")
        // val collection = collection(database)
    }

    init {

        val collection = database.getCollection(tool)
    }

    fun find(ageLowerLimit: Number, ageUpperLimit: Number, gender: String): List<DummyData> {
        // should query database :
        // if array.length > 1: database.findMany(filter)
        // else database.findOne(filter)
        val data: List<DummyData> = listOf()
        return data
    }
}
