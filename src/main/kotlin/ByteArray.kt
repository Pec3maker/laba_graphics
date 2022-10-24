@OptIn(ExperimentalUnsignedTypes::class)
fun UByteArray.extractBytes(count: Int): UInt {
    var result = 0u
    for (i in 0 until count) {
        result = (result shl 8) + (get(i) and 255u)
    }
    return result
}

@OptIn(ExperimentalUnsignedTypes::class)
fun UByteArray.unite(): ByteArray {
    val unitedArray = ByteArray(size / 2 + 1)
    var j = 0
    for (i in 0 until size step 2) {
        val first = (get(i).toUInt() shl 4)
        if(i + 1 < size + 1) {
            unitedArray[j] = (first + get(i + 1).toUInt()).toByte()
        }
        j++
    }

    return unitedArray
}