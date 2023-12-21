package money.rave.useful.encode

interface Encoder {

    fun encode(data: ByteArray): ByteArray

    fun encodeToString(data: ByteArray): String
}