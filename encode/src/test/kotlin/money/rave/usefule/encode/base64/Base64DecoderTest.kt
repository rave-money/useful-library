package money.rave.usefule.encode.base64

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class Base64DecoderTest {

    @Test
    fun encode_A_test() {
        assertContentEquals("A".toByteArray(), Base64Decoder.DEFAULT.decodeFromString("QQ=="))
    }

    @Test
    fun encode_AB_test() {
        assertContentEquals("AB".toByteArray(), Base64Decoder.DEFAULT.decodeFromString("QUI="))
    }

    @Test
    fun encode_ABC_test() {
        assertContentEquals("ABC".toByteArray(), Base64Decoder.DEFAULT.decodeFromString("QUJD"))
    }
}