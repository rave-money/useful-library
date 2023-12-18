package money.rave.usefule.encode.hex

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class HexEncoderTest {
    
    @Test
    fun encode_A_test() {
        assertEquals("41", HexEncoder.DEFAULT.encodeToString("A".toByteArray()))
    }

    @Test
    fun encode_AB_test() {
        assertEquals("4142", HexEncoder.DEFAULT.encodeToString("AB".toByteArray()))
    }

    @Test
    fun encode_ABC_test() {
        assertEquals("414243", HexEncoder.DEFAULT.encodeToString("ABC".toByteArray()))
    }
}