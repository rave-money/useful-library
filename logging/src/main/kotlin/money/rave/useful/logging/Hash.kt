package money.rave.useful.logging

interface Hash {

    fun hash(data: ByteArray): ByteArray
}