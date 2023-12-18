package money.rave.usefule.encode.base64

import money.rave.usefule.encode.Encoder

class Base64Encoder(
    private val padding: Char = '=',
) : Encoder {

    companion object {
        private val indexes = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"

        val DEFAULT = Base64Encoder()
    }

    override fun encode(data: ByteArray): ByteArray {
        val results = mutableListOf<Byte>()
        val flag = 63
        for (chunk in data.toList().chunked(3)) {
            var value = 0
            val activeByteCount = chunk.size * 8
            var consumedByteCount = 0

            for (i in 0 until 3) {
                value = value.shl(8) + (chunk.getOrNull(i) ?: 0)
            }

            while (consumedByteCount < activeByteCount) {
                results.add(value.shr(18 - consumedByteCount).and(flag).toByte())
                consumedByteCount += 6
            }
        }
        return results.toByteArray()
    }

    override fun encodeToString(data: ByteArray): String {
        val encoded = encode(data)
        val sb = StringBuffer()

        for (chunk in encoded.toList().chunked(4)) {
            for (c in chunk) {
                sb.append(indexes[c.toInt()])
            }

            repeat (4 - chunk.size) {
                sb.append(padding)
            }
        }
        return sb.toString()
    }
}