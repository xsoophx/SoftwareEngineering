package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.litote.kmongo.util.KMongoUtil.defaultCollectionName
import tornadofx.Controller
import kotlin.reflect.KClass

const val DATABASE_NAME = "prod"

class Database(databaseName: String = DATABASE_NAME) : Controller() {
    val client = KMongo.createClient()
    val database: MongoDatabase = client.getDatabase(databaseName)

    fun <T : IPersist> getCollection(clazz: KClass<T>): MongoCollection<T> {
        return database.getCollection(defaultCollectionName(clazz), clazz.java)
    }
}
