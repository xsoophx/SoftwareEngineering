package de.tuchemnitz.se.exercise.persist

import de.tuchemnitz.se.exercise.DATABASE
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.TestInstance
import tornadofx.Controller
import tornadofx.Scope
import tornadofx.set

/**
 * Abstract base class for Database tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag(DATABASE)
abstract class AbstractDatabaseTest(
    protected val db: Database = Database(databaseName = "test")
) : Controller() {
    override val scope = Scope()

    @BeforeAll
    fun setUpDb() {
        scope.set(db)
    }
}