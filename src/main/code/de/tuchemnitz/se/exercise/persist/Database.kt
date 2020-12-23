package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.util.KMongoUtil.defaultCollectionName
import tornadofx.Controller
import kotlin.reflect.KClass

const val DATABASE_NAME = "test"

class Database(databaseName: String = DATABASE_NAME) : Controller() {
    val client = KMongo.createClient()
    val database: MongoDatabase = client.getDatabase(databaseName)

    fun <ENTITY : IPersist> getCollection(clazz: KClass<ENTITY>): MongoCollection<ENTITY> {
        return database.getCollection(defaultCollectionName(clazz), clazz.java)
    }
}
