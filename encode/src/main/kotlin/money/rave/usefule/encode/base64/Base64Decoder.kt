package money.rave.usefule.encode.base64

import money.rave.usefule.encode.Decoder

class Base64Decoder(
    private val padding: Char = '=',
) : Decoder {

    companion object {
        private val indexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

        val DEFAULT = Base64Decoder()
    }

    override fun decode(data: ByteArray): ByteArray {
        val results = mutableListOf<Byte>()
        val flag = 255
        for (chunk in data.toList().chunked(4)) {
            var value = 0
            val activeBitCount = chunk.size * 6

            for (i in 0 until 4) {
                value = value.shl(6) + (chunk.getOrNull(i) ?: 0)
            }

            for (i in 0 until activeBitCount / 8) {
                results.add(value.shr(16 - i * 8).and(flag).toByte())
            }
        }
        return results.toByteArray()
    }

    override fun decodeFromString(data: String): ByteArray {
        val bytes = data
            .filter { it != padding }
            .map { indexes.indexOf(it).toByte() }
            .toByteArray()

        return decode(bytes)
    }
}