package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.litote.kmongo.findOneById
import tornadofx.Controller
import kotlin.reflect.KClass

abstract class AbstractCollection<T : IPersist>(
   clazz: KClass<T>
) : Controller() {
    private val database : Database by inject()
    protected val collection = database.getCollection(clazz)

    open fun find(filter: Bson): FindIterable<T> {
        return collection.find(filter)
    }

    open fun findOneById(filter: Any): T? {
        return collection.findOneById(filter)
    }

    open fun saveOne(data: T) {
        collection.insertOne(data)
    }

    open fun saveMany(data: Set<T>) {
        collection.insertMany(data.toList())
    }

    open fun deleteOne(filter: Bson) {
        collection.deleteOne(filter)
    }

    open fun deleteMany(filter: Bson = BsonDocument()) {
        collection.deleteMany(filter)
    }
}
