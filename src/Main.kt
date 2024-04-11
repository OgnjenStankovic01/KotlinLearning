class Position(var x: Int, var y: Int)
var fighting = false
fun main() {
    print("Enter your name: ")
    val nameInput = readLine()
    var player = Player(nameInput ?: "", 30, 10, 30, 1, 0, mutableListOf(), Position(0, 0))
    var potion = Potion("Name", "desc", 0, 0, 0)
    var drop: Potion = potion


    println("Choose where to move? (North, south, east, west): {x:${player.position.x}, y: ${player.position.y}}")

    var ghoul1 = Monster("Ghoul", 50, 20, 20, drop)
    var orc1 = Monster("Orc", 80, 15, 20, drop)
    var skeleton1 = Monster("Skeleton", 20, 20, 15, drop)
    var goblin1 = Monster("Goblin", 30, 10, 10, drop)

    // Overworld map and monster generation
    var worldMap: HashMap<Position, Monster> = hashMapOf(
        Position(0, 0) to ghoul1,
        Position(1, 1) to orc1,
        Position(1, 2) to skeleton1,
        Position(1, 3) to goblin1
    )

    // Exploring the map and encountering monsters
    while (!fighting) {
        player.playerMovement(player.position)
        if (worldMap.containsKey(player.position)) {
            println("You've encountered a monster")
            val monster = worldMap[player.position]!!
            fighting = true
            combatLoop(player, monster)
        }
    }

}

fun combatLoop(player: Player, monster: Monster) {
    while (player.hp > 0 && monster.hp > 0) {
        println("""${player.name}'s health : ${player.hp}, ${monster.name}'s health: ${monster.hp}""")
        println("Choose your actions: ")
        println("1) Attack")
        println("2) Magic")
        println("3) Wait")
        println("4) Inventory")
        println("5) Use potion")

        val userInput = readLine()?.lowercase()?.trim()
        when (userInput) {
            "attack" -> player.Attack(monster, player)
            "magic" -> player.MagicAttack(monster, player)
            "wait" -> println("You reconsider your approach.")
            "inventory" -> player.openInventory(player)
            "use potion" -> {
                if (player.potionInventory.isEmpty()) {
                    println("You don't have any potions!")
                } else {
                    println("Select a potion to use:")
                    for ((index, potion) in player.potionInventory.withIndex()) {
                        println("${index + 1}) ${potion.name}")
                    }
                    val potionIndex = readLine()?.toIntOrNull()
                    if (potionIndex != null && potionIndex >= 1 && potionIndex <= player.potionInventory.size) {
                        val selectedPotion = player.potionInventory[potionIndex - 1]
                        player.usePotion(selectedPotion)
                    } else {
                        println("Invalid input. Please enter a valid potion number.")
                    }
                }
            }
            else -> println("Invalid input.")
        }

        // Check if combat should end
        if (player.hp <= 0) {
            println("Oh dear, you've died.")
            break
        } else if (monster.hp <= 0) {
            println("You've slain the monster!")
            println("You gain ${monster.xp} XP!")
            player.xp += monster.xp
            player.addItem(monster.drop)
            fighting = false
        }
    }
}
