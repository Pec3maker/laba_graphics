import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel

class Renderer(
    dimension: Dimension
) : JPanel() {

    private val frame = JFrame()
    private var image = BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB)

    init {
        frame.setSize(dimension.width, dimension.height)
        frame.isVisible = true
        frame.defaultCloseOperation = JFrame.HIDE_ON_CLOSE
        frame.add(this)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.drawImage(image, 0, 0, width, height, this)
    }

    fun setPixel(x: Int, y: Int, color: Color) {
        image.setRGB(x, y, color.rgb)
    }

    @OptIn(ExperimentalUnsignedTypes::class)
    fun setBitmap(bitmap: Bitmap) {
        var x = 0
        var y = 0
        bitmap.content.forEach { byte ->
            val paletteX1 = (byte and 12u).toInt() shr 2
            val paletteY1 = (byte and 3u).toInt()

            val contentI1 = paletteX1 * 4 + paletteY1

            val color1 = bitmap.palette[contentI1].toInt()
            setPixel(x, y, Color(color1))
            x++

            if (x >= bitmap.width) {
                x = 0
                y++
            }
        }
    }

    fun render() {
        repaint()
    }
}