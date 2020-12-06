package de.tuchemnitz.se.exercise

import io.mockk.clearAllMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@TestInstance(Lifecycle.PER_CLASS)
class GreeterTest {
    private val outputDevice = mockk<OutputDevice>(relaxed = true)
    private val greeter = _root_ide_package_.de.tuchemnitz.se.exercise.Greeter(out = outputDevice)

    @AfterEach
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `greeting nobody greets everybody`() {
        greeter.greet()

        verify { outputDevice.printLine("Hello, world!") }
        confirmVerified(outputDevice)
    }

    @ParameterizedTest(name = "{index} => target: {0}")
    @ValueSource(strings = ["Sophia", "le Erich", "Alex", "Nathalie"])
    fun `greeting somebody greets that individual`(target: String) {
        greeter.greet(whom = target)

        verify { outputDevice.printLine("Hello, $target!") }
        confirmVerified(outputDevice)
    }
}
