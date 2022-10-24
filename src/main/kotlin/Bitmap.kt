@OptIn(ExperimentalUnsignedTypes::class)
class Bitmap(
    byteArray: UByteArray
) {
    val width: Int
    val height: Int
    val pixelBytes: UByte
    val paletteSize: UByte
    val palette: UIntArray
    val content: UByteArray
    val contentSize: Int

    init {
        var bytes = byteArray

        // extract width
        width = bytes.extractBytes(WIDTH_BYTES).toInt()
        bytes = bytes.copyOfRange(WIDTH_BYTES, bytes.size).toUByteArray()

        // extract height
        height = bytes.extractBytes(HEIGHT_BYTES).toInt()
        bytes = bytes.copyOfRange(HEIGHT_BYTES, bytes.size).toUByteArray()

        // extract pixelBytes
        pixelBytes = bytes.extractBytes(PIXEL_BYTES).toUByte()
        bytes = bytes.copyOfRange(PIXEL_BYTES, bytes.size).toUByteArray()


        // extract palette size
        paletteSize = bytes.extractBytes(PALETTE_SIZE_BYTES).toUByte()
        bytes = bytes.copyOfRange(PALETTE_SIZE_BYTES, bytes.size).toUByteArray()

        // extract palette
        palette = UIntArray(PALETTE_SIZE)
        for (i in 0 until PALETTE_SIZE) {
            palette[i] = bytes.extractBytes(PALETTE_BYTE)
            bytes = bytes.copyOfRange(PALETTE_BYTE, bytes.size).toUByteArray()
        }

        // extract content
        contentSize = if ((width + 1) * (height + 1) % 2 == 0) {
            (width + 1) * (height + 1)
        } else {
            (width + 1) * (height + 1) + 1
        }

        content = UByteArray(contentSize)
        var j = 0
        for (i in 0 until contentSize / 2) {
            val byte = bytes[i]

            content[j] = ((byte and 240u).toInt() shr 4).toUByte()
            if (j + 1 < contentSize) content[j + 1] = byte and 15u
            j += 2
        }
    }

    fun toByteArray(): ByteArray = width.toUInt().toByteArray() + height.toUInt().toByteArray() +
            pixelBytes.toByteArray() + paletteSize.toByteArray() + getPalette() + content.unite().dropLast(1)


    private fun getPalette(): ByteArray {
        val resultPalette = ByteArray(PALETTE_SIZE * PALETTE_BYTE)
        var i = 0
        palette.map { it.toByteArray() }.forEach { arr ->
            arr.forEach {
                resultPalette[i] = it
                i++
            }
        }
        return resultPalette
    }


    companion object {
        const val PALETTE_SIZE = 16
        const val PALETTE_BYTE = 4
        const val WIDTH_BYTES = 4
        const val HEIGHT_BYTES = 4
        const val PIXEL_BYTES = 1
        const val PALETTE_SIZE_BYTES = 1
    }
}