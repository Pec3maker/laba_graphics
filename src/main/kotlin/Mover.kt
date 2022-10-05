import java.awt.Dimension

class Mover(private val obj: GeometricObject, private val dimension: Dimension) {
    fun move() {
        if (canMove()) {
            obj.position.x += (obj.vector.x * obj.speed).toInt()
            obj.position.y += (obj.vector.y * obj.speed).toInt()
            obj.calculateHitBox()
        } else {
            //TODO
        }
    }

    private fun canMove(): Boolean = obj.hitBox.first.x >= 0 && obj.hitBox.first.x <= dimension.width &&
            obj.hitBox.first.y >= 0 && obj.hitBox.first.y <= dimension.height &&
            obj.hitBox.second.x >= 0 && obj.hitBox.second.x <= dimension.width &&
            obj.hitBox.second.y >= 0 && obj.hitBox.second.y <= dimension.height
}