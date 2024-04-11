class Player constructor(var name: String, var hp: Int, var attack: Int, var mana: Int, var level: Int, var xp: Int, var potionInventory: MutableList<Potion>, var position: Position) {
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
    fun addItem(potion: Potion) {
        potionInventory.add(potion)
    }
    fun openInventory(player: Player) {
       for (item in potionInventory){
           println(item.name)
           println(item.desc)
       }
    }
    fun usePotion(potion: Potion) {
        if (potion.healing > 0) {
            println("You use the healing potion, restoring ${potion.healing} HP!")
            hp += potion.healing
        } else if (potion.manaHealing > 0) {
            println("You use the mana potion and restore ${potion.manaHealing} MP!")
            mana += potion.manaHealing
        }
        potionInventory.remove(potion)
    }
    fun playerMovement(position: Position){
        var movementInput = readln()
        println("Choose where to move? (North, south, east, west): {x:${this.position.x}, y: ${this.position.y}}")
        when (movementInput.lowercase().trim()) {
            "north" -> this.position.y += 1
            "south" -> this.position.y -=1
            "east" -> this.position.x -=1
            "west" -> this.position.x += 1
            else -> println("Choose a proper direction. ")
        }
    }


}