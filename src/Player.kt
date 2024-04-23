class Player constructor(var name: String, var hp: Int, var maxHp: Int, var attack: Int, var mana: Int, var level: Int, var xp: Int, var potionInventory: MutableList<Potion>, var position: Position, var icon : Char) {
    fun Attack(monster: Monster){
        if (hp >0 && monster.hp > 0){
            println("You attack the monster for " + attack * level+ " damage!")
            monster.hp -= attack * level
        }
    }
    fun MagicAttack(monster: Monster){
        if(mana >= 10){
            println("You cast a firebolt! for "+ (attack * 2).times(level) + " damage!")
            monster.hp -= (attack * 2).times(level)
            mana -= 10
        }
        else{
            println("You don't have enough mana!")
        }
    }
    fun levelUp(){
        val xpThreshold: Int = 30
        if (xp == xpThreshold){
            println("You've levelled up!")
            level += 1
            xp = 0
            maxHp *= 2
            hp = maxHp
        }
    }
    fun addItem(potion: Potion) {
        potionInventory.add(potion)
    }
    fun openInventory() {
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
    fun playerMovement(){
        /*
        Removed function parameter because of the redundancy:
        When calling a method over an object, the object is already
        included, so there is no need to pass any of its attributes

        Removed "this" references because of the redundancy:
        If there are no local variables with the same name as attribute,
        there is no need to use "this", since it is already reachable and default
         */
        println("Choose where to move? (North, south, east, west): {x:${position.x}, y: ${position.y}}")
        var movementInput = readln()
        when (movementInput.lowercase().trim()) {
            "north" -> position.y += 1
            "south" -> position.y -=1
            "east" -> position.x -=1
            "west" -> position.x += 1
            else -> println("Choose a proper direction. ")
        }
    }


}