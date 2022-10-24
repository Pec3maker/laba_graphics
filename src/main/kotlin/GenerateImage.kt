import java.io.File
import kotlin.random.Random

fun main() {
    GenerateImage.generate()
}

class GenerateImage {
    companion object {
        fun generate() {

            val byteArrayHeader = ByteArray(10) { 0 }
            val byteArrayPalette = ByteArray(64) { 0 }
            val byteArrayContent = ByteArray(1024 * 1024 / 2) { 0 }

            //width
            byteArrayHeader[2] = 3
            byteArrayHeader[3] = 255.toByte()
            //height
            byteArrayHeader[6] = 3
            byteArrayHeader[7] = 255.toByte()
            //count bit to pixel
            byteArrayHeader[8] = 4
            //count palette size
            byteArrayHeader[9] = 16

            //red
            byteArrayPalette[0] = 0xFF.toByte()
            byteArrayPalette[1] = 0xFF.toByte()
            byteArrayPalette[2] = 0
            byteArrayPalette[3] = 0

            byteArrayPalette[4] = 0xFF.toByte()
            byteArrayPalette[5] = 0xFF.toByte()
            byteArrayPalette[6] = 0x24.toByte()
            byteArrayPalette[7] = 0

            byteArrayPalette[8] = 0xFF.toByte()
            byteArrayPalette[9] = 0xFF.toByte()
            byteArrayPalette[10] = 0x4F.toByte()
            byteArrayPalette[11] = 0

            byteArrayPalette[12] = 0xFF.toByte()
            byteArrayPalette[13] = 0xFF.toByte()
            byteArrayPalette[14] = 0x03.toByte()
            byteArrayPalette[15] = 0x3E.toByte()

            ///////////////////////////////////////

            //yellow

            byteArrayPalette[16] = 0xFF.toByte()
            byteArrayPalette[17] = 0xFF.toByte()
            byteArrayPalette[18] = 0xFF.toByte()
            byteArrayPalette[19] = 0

            byteArrayPalette[20] = 0xFF.toByte()
            byteArrayPalette[21] = 0xFF.toByte()
            byteArrayPalette[22] = 0xD8.toByte()
            byteArrayPalette[23] = 0

            byteArrayPalette[24] = 0xFF.toByte()
            byteArrayPalette[25] = 0xCC.toByte()
            byteArrayPalette[26] = 0xFF.toByte()
            byteArrayPalette[27] = 0

            byteArrayPalette[28] = 0xFF.toByte()
            byteArrayPalette[29] = 0xFB.toByte()
            byteArrayPalette[30] = 0xEC.toByte()
            byteArrayPalette[31] = 0x5D.toByte()

            ///////////////////////////////////////

            //black

            byteArrayPalette[32] = 0xFF.toByte()
            byteArrayPalette[33] = 0x00.toByte()
            byteArrayPalette[34] = 0x00.toByte()
            byteArrayPalette[35] = 0

            byteArrayPalette[36] = 0xFF.toByte()
            byteArrayPalette[37] = 0x31.toByte()
            byteArrayPalette[38] = 0x00.toByte()
            byteArrayPalette[39] = 0x62.toByte()

            byteArrayPalette[40] = 0xFF.toByte()
            byteArrayPalette[41] = 0x01.toByte()
            byteArrayPalette[42] = 0x32.toByte()
            byteArrayPalette[43] = 0x20.toByte()

            byteArrayPalette[44] = 0xFF.toByte()
            byteArrayPalette[45] = 0x56.toByte()
            byteArrayPalette[46] = 0x03.toByte()
            byteArrayPalette[47] = 0x19.toByte()

            ///////////////////////////////////////
            //pink
            byteArrayPalette[48] = 0xFF.toByte()
            byteArrayPalette[49] = 0xFF.toByte()
            byteArrayPalette[50] = 0xC0.toByte()
            byteArrayPalette[51] = 0xCB.toByte()

            byteArrayPalette[52] = 0xFF.toByte()
            byteArrayPalette[53] = 0xFF.toByte()
            byteArrayPalette[54] = 0xB8.toByte()
            byteArrayPalette[55] = 0xC6.toByte()

            byteArrayPalette[56] = 0xFF.toByte()
            byteArrayPalette[57] = 0xFF.toByte()
            byteArrayPalette[58] = 0x67.toByte()
            byteArrayPalette[59] = 0x81.toByte()

            byteArrayPalette[60] = 0xFF.toByte()
            byteArrayPalette[61] = 0xF9.toByte()
            byteArrayPalette[62] = 0xC6.toByte()
            byteArrayPalette[63] = 0xCF.toByte()
            ///////////////////////////////////////

            for (i in 0 until 1024 * 1024 / 2) {

                var result = 0
                for (i in 0..3) {
                    result = result shl 2
                    val randomInt = Random.nextInt(4)
                    result += randomInt
                }
                byteArrayContent[i] = result.toByte()
            }

            File("image.gns").writeBytes(byteArrayHeader + byteArrayPalette + byteArrayContent)
        }
    }
}