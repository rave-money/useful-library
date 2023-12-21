package money.rave.useful.encode.hex

import money.rave.useful.encode.Decoder

class HexDecoder : Decoder {

    companion object {
        val DEFAULT = HexDecoder()

        private val indexes = "0123456789ABCDEF"
    }

    override fun decode(data: ByteArray): ByteArray {
        return decodeFromString(data.toString())
    }

    override fun decodeFromString(data: String): ByteArray {
        val results = ByteArray(data.length / 2)
        data.chunked(2).forEachIndexed { index, chunk ->
            val (value1, value2) = indexes.indexOf(chunk.first()) to indexes.indexOf(chunk.last())
            val byte = (value1.shl(4) + value2).toByte()
            results[index] = byte
        }
        return results
    }
}