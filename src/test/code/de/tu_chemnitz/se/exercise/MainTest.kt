package de.tu_chemnitz.se.exercise

import io.mockk.confirmVerified
import io.mockk.excludeRecords
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import io.mockk.verifyOrder
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle

@TestInstance(Lifecycle.PER_CLASS)
class MainTest {
    @BeforeEach
    fun mockConsole() {
        mockkObject(Main.Console)
    }

    @AfterEach
    fun unmockConsole() {
        unmockkObject(Main.Console)
    }

    @Test
    fun `Running program with no arguments only prints hello world`() {
        val app = Main(arrayOf())
        app.run()

        excludeRecords { Main.Console.printLine(any()) }
        verify { Main.Console.print("Hello, world!\n") }
        confirmVerified(Main.Console)
    }

    @Test
    fun `Running program with arguments greets the world and then every one in order`() {
        val people = arrayOf("Sophia", "le Erich", "Alex", "Nathalie")

        val app = Main(people)
        app.run()

        excludeRecords { Main.Console.printLine(any()) }
        verifyOrder {
            Main.Console.print("Hello, world!\n")
            people.forEach { person ->
                Main.Console.print("Hello, $person!\n")
            }
        }
        confirmVerified(Main.Console)
    }
}
