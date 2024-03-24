class Main {

}
fun main(){
    //potions
    val drop1 = Potion("Healing potion", "Heals the player for 10 HP", 1, 10, 0)
    val drop2 = Potion("Healing potion", "Heals the player for 10 HP", 2, 0, 10)

    print("Enter your name: ")
    val nameInput = readln()
    var player = Player(nameInput, 30, 10, 30, 1, 0, mutableListOf())
    var monster = Monster("placeholder", 30, 10, 10, drop1)
    var fighting = false


    fun createMonster(){
        if (!fighting){
            val rng = (0..3).random()
            if (player.level == 1){
                when (rng) {
                    0 -> monster = Monster("Ghoul", 50, 20, 20, drop2)
                    1 -> monster = Monster("Orc", 80, 15, 20, drop1)
                    2 -> monster = Monster("Skeleton", 20, 20, 15, drop2)
                    3 -> monster = Monster("Goblin", 30, 10, 10,drop1)
                }
            }
            else if (player.level > 1){
                when (rng) {
                    0 -> monster = Monster("Ghoul", 80, 20, 20, drop2)
                    1 -> monster = Monster("Orc", 100, 15, 20, drop1)
                    2 -> monster = Monster("Skeleton", 60, 20, 15, drop2)
                    3 -> monster = Monster("Goblin", 55, 10, 10,drop1)
                }
            }
            fighting = true
        }
    }
    createMonster()
    while (player.hp > 0 && monster.hp > 0 && fighting){

        println("""${player.name}'s health : ${player.hp}, ${monster.name}'s health: ${monster.hp}""")
        println("Choose your actions: ")
        println("1) Attack")
        println("2) Magic")
        println("3) Wait")
        println("4) Inventory")

        var userInput = readln()
        when (userInput.lowercase().trim()){
            "attack" -> player.Attack(monster, player)
            "magic" -> player.MagicAttack(monster, player)
            "wait" -> println("You reconsider your approach.")
            "inventory" -> player.openInventory(player)
            else -> {
                println("Invalid input.")
            }
        }
        if (monster.hp <= 0){
            println("You've slain the monster!")
            println("You gain ${monster.xp} XP!")
            player.xp += monster.xp
            player.addItem(monster.drop)
            fighting = false
            createMonster()
        }
        if (player.xp >= 30){
            player.levelUp(player)
        }
    }
}