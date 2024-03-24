open class Item(var name: String, var desc: String, var id: Int) {
    fun printDesc(item: Item){
        println("Item name: ${item.name}")
        println("Description: ${item.desc}")
    }
}