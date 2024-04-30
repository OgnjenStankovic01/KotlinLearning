open class Item(var name: String, var desc: String, var id: Int) {
    override fun toString(): String {
        return "Item name: ${name} \nDescription: ${desc}"
    }
}