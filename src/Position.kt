class Position(var x: Int, var y: Int) {
    override fun equals(other: Any?): Boolean {
        if (other is Position)
            if ((this.x == other.x) and (this.y == other.y))
                return true
        return false
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }

    fun getX(): Int {
        return x
    }

    fun getY(): Int {
        return y
    }

    fun setX(newX: Int) {
        var n = Int
        if(x + newX < 0 || x+newX > n){

        }
    }

    fun setY(newY: Int) {
        try {
            y = newY
        } catch (e: Exception) {
            throw IllegalArgumentException()
        }

    }
}