package de.tuchemnitz.se.exercise.persist

import org.litote.kmongo.KMongo

const val DATABASE_NAME = "test"

object DatabaseClient {
    val client = KMongo.createClient()
    val database = client.getDatabase(DATABASE_NAME)
}
