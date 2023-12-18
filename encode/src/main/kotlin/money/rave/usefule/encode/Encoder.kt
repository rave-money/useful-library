package money.rave.usefule.encode

interface Encoder {

    fun encode(data: ByteArray): ByteArray

    fun encodeToString(data: ByteArray): String
}