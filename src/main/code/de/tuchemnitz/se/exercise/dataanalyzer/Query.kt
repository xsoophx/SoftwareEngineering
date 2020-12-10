package de.tuchemnitz.se.exercise.dataanalyzer

import org.bson.conversions.Bson

class Query() {

    fun connect() {
        // should connect to database
    }
    fun query(filter: Bson) {
        // should query database :
        // if array.length > 1: database.findMany(filter)
        // else database.findOne(filter)
    }
    fun close() {
        // should close connection
    }
}
