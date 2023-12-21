package money.rave.useful.encode.hex

import money.rave.useful.encode.Encoder

class HexEncoder : Encoder {

    companion object {
        val DEFAULT = HexEncoder()

        private val indexes = "0123456789ABCDEF"
    }

    override fun encode(data: ByteArray): ByteArray {
        return encodeToString(data).toByteArray()
    }

    override fun encodeToString(data: ByteArray): String {
        val sb = StringBuffer()
        for (d in data) {
            val origin = d.toInt()
            val (value1, value2) = (origin.and(15.shl(4)).shr(4) to origin.and(15))

            sb.append(indexes[value1])
            sb.append(indexes[value2])
        }

        return sb.toString()
    }
}