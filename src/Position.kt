class Position(var x: Int, var y: Int){
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

}
