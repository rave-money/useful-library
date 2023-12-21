package money.rave.useful.encode.hex

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class HexDecoderTest {

    @Test
    fun encode_A_test() {
        assertContentEquals("A".toByteArray(), HexDecoder.DEFAULT.decodeFromString("41"))
    }

    @Test
    fun encode_AB_test() {
        assertContentEquals("AB".toByteArray(), HexDecoder.DEFAULT.decodeFromString("4142"))
    }

    @Test
    fun encode_ABC_test() {
        assertContentEquals("ABC".toByteArray(), HexDecoder.DEFAULT.decodeFromString("414243"))
    }
}