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
        frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        frame.add(this)
    }

    override fun paintComponent(g: Graphics?) {
        super.paintComponent(g)
        g?.drawImage(image, 0, 0, width, height, this)
    }

    fun setPixel(x: Int, y: Int, color: Color) {
        image.setRGB(x, y, color.rgb)
    }

    fun setObject(obj: GeometricObject, color: Color) {
        obj.points.forEach { point ->
            image.setRGB(point.x, point.y, color.rgb)
        }
    }

    fun render() {
        repaint()
    }
}