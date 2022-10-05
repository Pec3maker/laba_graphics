import java.awt.Point

interface GeometricObject {
    val position: Point
    var speed: Float
    val vector: Vector
    val hitBox: Pair<Point, Point>
    val points: List<Point>

    fun calculateHitBox()
}