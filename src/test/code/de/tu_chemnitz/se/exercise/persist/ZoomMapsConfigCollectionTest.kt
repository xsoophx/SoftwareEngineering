package de.tu_chemnitz.se.exercise.persist

import assertk.assertThat
import assertk.assertions.*
import com.mongodb.client.model.Filters
import de.tu_chemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import de.tu_chemnitz.se.exercise.persist.configs.ZoomMapsConfig
import org.bson.conversions.Bson
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.litote.kmongo.KMongo
import org.litote.kmongo.`in`
import org.litote.kmongo.eq

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class ZoomMapsConfigCollectionTest {
  private val client = KMongo.createClient() //get com.mongodb.MongoClient new instance
  private val database = client.getDatabase("test") //normal java driver usage
  private val collection = ZoomMapsConfigCollection(database)

  companion object {
    private val configs = setOf(
      ZoomMapsConfig(zoomSpeed = 0.5F, keyBindings = setOf("u", "d", "l", "r")),
      ZoomMapsConfig(zoomSpeed = 0.1F, keyBindings = setOf("a", "a", "a", "a")),
      ZoomMapsConfig(zoomSpeed = 2.3F, keyBindings = setOf("c", "d", "e", "z"))
    )
  }

  @BeforeEach
  fun cleanUp() {
    collection.deleteMany()
  }

  @Test
  fun `all configs should be saved properly at once`() {
    collection.saveMany(configs)
    assertThat(collection.find(ZoomMapsConfig::_id `in` configs.map(ZoomMapsConfig::_id)))
      .containsOnly(*configs.toTypedArray())

  }

  @Test
  fun `every config should be saved properly`() {
    configs.forEach {
      collection.saveOne(it)
      assertThat(collection.find(ZoomMapsConfig::_id eq it._id)).contains(it)
    }
  }

  @Test
  fun `every config should be deleted properly`() {
    configs.forEach {
      collection.deleteOne(ZoomMapsConfig::_id eq it._id)
      assertThat(collection.find(ZoomMapsConfig::_id eq it._id)).doesNotContain(it)
    }

  }

  @Test
  fun `findOneById should work properly for each id`() {
    collection.saveMany(configs)
    configs.forEach {
      assertThat(collection.findOneById(it._id)).isEqualTo(it)
    }
  }

  @Test
  fun `configs should be deleted properly at once`() {
    val ids = "_id" `in` configs
    collection.deleteMany(ids)

    configs.forEach {
      collection.deleteOne(ZoomMapsConfig::_id eq it._id)
      assertThat(collection.findOneById(it._id)).isNull()
    }
  }

  private infix fun String.`in`(collection: Collection<Any>): Bson =
    Filters.`in`(this, collection)

}