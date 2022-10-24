fun UInt.toByteArray(): ByteArray {
    var num = this
    return ByteArray(4) {
        val byte = num and 255u
        num = num shr 8
        byte.toByte()
    }.reversedArray()
}
