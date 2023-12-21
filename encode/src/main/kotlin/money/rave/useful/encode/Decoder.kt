package money.rave.useful.encode

interface Decoder {
    fun decode(data: ByteArray): ByteArray

    fun decodeFromString(data: String): ByteArray
}