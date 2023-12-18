package money.rave.usefule.encode.base64

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Base64EncoderTest {
    
    @Test
    fun encode_A_test() {
        assertEquals("QQ==", Base64Encoder.DEFAULT.encodeToString("A".toByteArray()))
    }

    @Test
    fun encode_AB_test() {
        assertEquals("QUI=", Base64Encoder.DEFAULT.encodeToString("AB".toByteArray()))
    }

    @Test
    fun encode_ABC_test() {
        assertEquals("QUJD", Base64Encoder.DEFAULT.encodeToString("ABC".toByteArray()))
    }
}