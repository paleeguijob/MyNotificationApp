package aof.rp.mynotificationapp.base.extensions

import aof.rp.mynotificationapp.base.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

@ExperimentalCoroutinesApi
class IntExtKtTest : BaseUnitTest() {


    @Test
    fun int_ext_kotlin_or_zero_has_value() {
        //Given
        val one = 1

        //When
        val result = one.orZero()

        //Then
        assertEquals(one, result)
    }

    @Test
    fun int_ext_kotlin_or_zero_is_nullable() {
        //Given
        val notValue: Int? = null

        //When
        val result = notValue.orZero()

        //Then
        assertEquals(0, result)
    }
}