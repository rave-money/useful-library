package money.rave.useful.hash

interface Hash {

    fun hash(data: ByteArray): ByteArray
}