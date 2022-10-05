import java.awt.Color
import java.awt.Dimension

fun main() {
    val renderer = Renderer(Dimension(600, 600))
    for (i in 1..500) {
        Thread.sleep(10)
        renderer.setPixel(i, i, Color.BLACK)
        renderer.render()
    }
}