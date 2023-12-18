package money.rave.usefule.encode

interface Decoder {
    fun decode(data: ByteArray): ByteArray

    fun decodeFromString(data: String): ByteArray
}