data class Vector(
    var x: Float = 0f,
    var y: Float = 0f
) {

    fun normalize(speed: Float) {
        x *= 1 / speed
        y *= 1 / speed
    }

    fun addVector(newVector: Vector, currentSpeed: Float, newSpeed: Float) {
        val objVector = Vector()
        val tempVector = Vector()
        objVector.x = x * currentSpeed
        objVector.y = y * currentSpeed
        tempVector.x = newVector.x * newSpeed
        tempVector.y = newVector.x * newSpeed
        x = objVector.x + tempVector.x
        y = objVector.y + tempVector.y
        normalize(newSpeed)
    }
}