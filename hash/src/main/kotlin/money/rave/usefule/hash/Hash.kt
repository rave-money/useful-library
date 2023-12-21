package money.rave.usefule.hash

interface Hash {

    fun hash(data: ByteArray): ByteArray
}