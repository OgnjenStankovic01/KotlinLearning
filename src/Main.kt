var fighting = false
fun main() {
    var observable = Observable()
    val booleanObserver = Player.BooleanObserver()
    observable.addObserver(booleanObserver)
    print("Enter your name: ")
    val nameInput = readLine()
    var player = Player(nameInput ?: "", 30, 30, 10, 30, 1, 0, mutableListOf(), Position(0, 0), 'Ø', )
    var potion = Potion("Name", "desc", 0, 0, 0)
    var drop: Potion = potion

    var ghoul1 = Monster("Ghoul", 50, 20, 20, drop, 'Ĝ')
    var orc1 = Monster("Orc", 80, 15, 20, drop, 'Ŏ')
    var skeleton1 = Monster("Skeleton", 20, 20, 15, drop, 'Ś')
    var goblin1 = Monster("Goblin", 30, 10, 10, drop, 'ĝ')

    var ghoul2 = Monster("Ghoul", 60, 20, 30, drop, 'Ĝ')
    var orc2 = Monster("Orc", 90, 15, 30, drop, 'Ŏ')
    var skeleton2 = Monster("Skeleton", 30, 20, 25, drop, 'Ś')
    var goblin2 = Monster("Goblin", 40, 10, 20, drop, 'ĝ')

    // Initialize the initial world map
    var worldMap: HashMap<Position, Being> = hashMapOf(
    Position(1, 1) to ghoul1,
    Position(2, 2) to orc1,
    Position(3, 2) to skeleton1,
    Position(4, 1) to goblin1
    )
    // Exploring the map and encountering monsters
    while (!fighting) {
        // Check if the player encounters a monster
        if (worldMap.containsKey(player.position) && worldMap.getValue(player.position) != player) {
            println("You've encountered a monster")
            val monster = (worldMap[player.position] as Monster)
            monster.drop = monster.monsterDrop()
            fighting = true
            combatLoop(player, monster, worldMap)
        }
        // Update player position in the world map
        worldMap.remove(player.position)
        worldMap[player.position] = player
        // Draw the world map
        drawWorldMap(worldMap, player)
        // Move the player
        player.playerMovement()
    }
}

private fun drawWorldMap(worldMap: HashMap<Position, Being>, player: Player) {

        println("First map")
        for (x in 0..5) {
            for (y in 0..5) {
                var currentPos = Position(x, y)
                if (worldMap.containsKey(currentPos)) {
                    var monster = worldMap[currentPos]
                    if (monster != null) {
                        print(" ${monster.icon} ")
                    }
                } else {
                    print(" X ")
                }
            }
            println()
        }
    }

fun combatLoop(player: Player, monster: Monster, worldMap: HashMap<Position, Being>) {
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
            "attack" -> player.Attack(monster)
            "magic" -> player.MagicAttack(monster)
            "wait" -> println("You reconsider your approach.")
            "inventory" -> player.openInventory()
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
            //check for level-up
            player.levelUp()
            player.addItem(monster.drop)
            fighting = false
            worldMap.remove(player.position)
        }
    }
}
