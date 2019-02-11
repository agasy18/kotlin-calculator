import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory

internal class CalculatorKtTest {

    @Test
    fun `Test eval number`() {
        assertEquals(5.0f, eval("5"))
    }

    @Test
    fun `Test eval div`() {
        assertEquals(6.0f, eval("12 / 2"))
    }

    @Test
    fun `Test eval mul`() {
        assertEquals(24.0f, eval("12 * 2"))
    }

    @Test
    fun `Test eval sub`() {
        assertEquals(4.0f, eval("5 - 1"))
    }

    @Test
    fun `Test eval add`() {
        assertEquals(6.0f, eval("5 + 1"))
    }


    @Test
    fun `Test div priority`() {
        assertEquals(5.0f, eval("5/5*5"))
    }

    @Test
    fun `Test sub priority`() {
        assertEquals(-2.0f, eval("2 - 2 - 2"))
    }

    @Test
    fun `Test mul priority`() {
        assertEquals(6.0f, eval("2 * 2 + 2"))
        assertEquals(6.0f, eval("2 + 2 * 2"))
    }


    @Test
    fun `Test braces`() {
        assertEquals(3.0f, eval("(3)"))
        assertEquals(4.0f, eval(" ( 2+2 )"))
    }

    @TestFactory
    fun `Test braces priority`() = listOf(
        "2 * (2 + 2)" to 8.0f,
        "(2 * 2) / 2" to 2.0f,
        "(2 + 2) * 2" to 8.0f,
        "2 *    ( 2 - 2)" to 0.0f,
        "7 / 5 * ( 2 + 2 * 2 )" to 8.4f,
        "7 / 5 * (((10 + 5) / 2.0 * 2 + (25-10/2*2.0)) / ((5 -7) - 4- 4/2 + 2) * 2)" to -14.0f
    ).map { (exp, res) ->
        DynamicTest.dynamicTest("Testing $exp") {
            assertEquals(res, eval(exp))
        }
    }

    @Test
    fun `Test spacing`() {
        assertEquals(6.0f, eval("2 *2 + 2  "))
        assertEquals(6.0f, eval("  2  + 2*      2"))
    }

    @Test
    fun `Test signs`() {
        assertEquals(-2.0f, eval("2 *-2 + 2  "))
        assertEquals(-2.0f, eval("  2  + 2*      -2"))
        assertEquals(-4.0f, eval("-5+1"))
        assertEquals(-4.0f, eval("-5++1"))
    }

    @Test
    fun `Test big expression`() {
        assertEquals(2.5f, eval("2 *-2 + 2  * 2 + 2 -2 / -4"))
    }


    @Test
    fun `Test wrong expression`() {
        assertThrows(IllegalExpressionException::class.java, {
            assertEquals(2.5f, eval("2 *-2 +aa  * 2 + 2 -2 / -4"))
        }, "Wrong Symbol")
    }


    @Test
    fun `Test invalid expression`() {
        assertThrows(IllegalExpressionException::class.java, {
            eval("5+ 5 5 6 +  7")
        }, "Invalid expression")
    }

    @Test
    fun `Test invalid braces`() {
        assertThrows(IllegalExpressionException::class.java, {
            eval("7 / 5 * ((2 + 2) / (((5 -7) + 2) * 2)")
        }, "Invalid braces")
    }

}