open class Item(var name: String, var desc: String, var id: Int) {

    // object-oriented approach
    // (when printl(itemObj) is run, this function is called)
    override fun toString(): String {
        return "Item name: ${name} \nDescription: ${desc}"
    }
}