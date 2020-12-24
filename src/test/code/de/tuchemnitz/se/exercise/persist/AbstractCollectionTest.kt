package de.tuchemnitz.se.exercise.persist

import com.mongodb.client.FindIterable
import com.mongodb.client.MongoCollection
import com.mongodb.client.result.DeleteResult
import com.mongodb.client.result.InsertManyResult
import com.mongodb.client.result.InsertOneResult
import de.tuchemnitz.se.exercise.DATABASE
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.bson.BsonDocument
import org.bson.conversions.Bson
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.litote.kmongo.Id
import org.litote.kmongo.`in`
import org.litote.kmongo.eq
import org.litote.kmongo.newId

@TestInstance(Lifecycle.PER_CLASS)
@Tag(DATABASE)
class AbstractCollectionTest : AbstractDatabaseTest(db = mockk(relaxed = true)) {
    data class DummyConfig(
        override val _id: Id<DummyConfig> = newId(),
        val payload: String = ""
    ) : IPersist

    class DummyCollection : AbstractCollection<DummyConfig>(DummyConfig::class)

    private val mockedCollection = mockk<MongoCollection<DummyConfig>>()
    private val collection: DummyCollection by inject()

    @BeforeAll
    fun mockDB() {
        every { db.getCollection(DummyConfig::class) } returns mockedCollection
    }

    @BeforeEach
    fun setUp() {
        clearMocks(mockedCollection)
    }

    @AfterEach
    fun tearDown() {
        confirmVerified(mockedCollection)
    }

    @Test
    fun finding() {
        val mockedResult = mockk<FindIterable<DummyConfig>>()

        every { mockedCollection.find(any<Bson>()) } returns mockedResult
        collection.find(BsonDocument()) shouldBe mockedResult
        verify { mockedCollection.find(any<Bson>()) }
    }

    @Test
    fun `finding single by id`() {
        val cfg = DummyConfig()

        // findOneById is an extension function and cannot be mocked
        // but down the line it does a find(filter).firstOrNull()
        every { mockedCollection.find(any<Bson>()).firstOrNull() } returns cfg
        collection.findOneById(cfg._id) shouldBe cfg
        verify { mockedCollection.find(any<Bson>()).firstOrNull() }
    }

    @Test
    fun `saving one`() {
        val cfg = DummyConfig()
        val mockedResult = mockk<InsertOneResult>()

        every { mockedCollection.insertOne(any()) } returns mockedResult
        collection.saveOne(cfg)
        verify { mockedCollection.insertOne(eq(cfg)) }
    }

    @Test
    fun `saving many`() {
        val configs =
            setOf(DummyConfig(payload = "alpha"), DummyConfig(payload = "bravo"), DummyConfig(payload = "charlie"))
        val mockedResult = mockk<InsertManyResult>()

        every { mockedCollection.insertMany(any()) } returns mockedResult
        collection.saveMany(configs)
        verify { mockedCollection.insertMany(eq(configs.toList())) }
    }

    @Test
    fun deleting() {
        val cfg = DummyConfig()
        val filter = DummyConfig::_id eq cfg._id
        val mockedResult = mockk<DeleteResult>()

        every { mockedCollection.deleteOne(any()) } returns mockedResult
        collection.deleteOne(filter)
        verify { mockedCollection.deleteOne(eq(filter)) }
    }

    @Test
    fun `deleting many`() {
        val configs = setOf(DummyConfig(payload = "foo"), DummyConfig(payload = "bar"))
        val filter = DummyConfig::_id `in` configs.map(DummyConfig::_id)
        val mockedResult = mockk<DeleteResult>()

        every { mockedCollection.deleteMany(any()) } returns mockedResult
        collection.deleteMany(filter)
        verify { mockedCollection.deleteMany(eq(filter)) }
    }

    @Test
    fun `deleting all`() {
        val mockedResult = mockk<DeleteResult>()

        every { mockedCollection.deleteMany(any()) } returns mockedResult
        collection.deleteMany()
        verify { mockedCollection.deleteMany(eq(BsonDocument())) }
    }
}
