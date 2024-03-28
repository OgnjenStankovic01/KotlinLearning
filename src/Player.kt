class Player constructor(var name: String, var hp: Int, var attack: Int, var mana: Int, var level: Int, var xp: Int, var potionInventory: MutableList<Item>) {
    fun Attack(monster: Monster,player: Player){
        if (player.hp >0 && monster.hp > 0){
            println("You attack the monster for " + player.attack * player.level+ " damage!")
            monster.hp -= player.attack * player.level
        }
    }
    fun MagicAttack(monster: Monster, player: Player){
        if(player.mana >= 10){
            println("You cast a firebolt! for "+ (player.attack * 2).times(player.level) + " damage!")
            monster.hp -= (player.attack * 2).times(player.level)
            player.mana -= 10
        }
        else{
            println("You don't have enough mana!")
        }
    }
    fun levelUp(player: Player){
        println("You've levelled up!")
        player.level += 1
        player.xp = 0
    }
    fun addItem(item: Item) {
        potionInventory.add(item)
    }
    fun openInventory(player: Player){
       for (item in potionInventory){
           println(item.name)
       }
    }
    fun usePotion(potion: Potion, player: Player) {
        player.openInventory(player)
        println("Which potion do you want to use? (type the potion name): ")

        var userInput = readln()
        when (userInput.lowercase().trim()){
            "healing potion" -> if (player.potionInventory.any {it.id == 1}){
                potion.useHealingPotion(potion,player)
            }
            "mana potion" -> if (player.potionInventory.any {it.id == 2}){
                potion.useManaPotion(potion,player)
            }
            else -> {
                println("You have no potions of that type.")
            }
        }
    }


}