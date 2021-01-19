package de.tuchemnitz.se.exercise.persist

import assertk.assertThat
import assertk.assertions.containsOnly
import assertk.assertions.doesNotContain
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.mongodb.client.model.Filters
import de.tuchemnitz.se.exercise.DATABASE
import de.tuchemnitz.se.exercise.persist.configs.ZoomMapsConfig
import de.tuchemnitz.se.exercise.persist.configs.collections.ZoomMapsConfigCollection
import javafx.scene.input.KeyCode
import org.bson.conversions.Bson
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.litote.kmongo.`in`
import org.litote.kmongo.eq
import tornadofx.Controller

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Tag(DATABASE)
class ZoomMapsConfigCollectionTest : Controller() {
    private val collection: ZoomMapsConfigCollection by inject()

    companion object {
        private val configs = listOf(
            ZoomMapsConfig(zoomSpeed = 0.5, zoomKey = KeyCode.A),
            ZoomMapsConfig(zoomSpeed = 0.1, zoomKey = KeyCode.CONTROL),
            ZoomMapsConfig(zoomSpeed = 2.3, zoomKey = KeyCode.SPACE)
        )
    }

    @AfterEach
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
            assertThat(collection.findOneById(it._id)).isEqualTo(it)
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
