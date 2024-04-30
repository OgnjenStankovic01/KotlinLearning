class Player (name: String,hp: Int,  var maxHp: Int, attack: Int, var mana: Int, var level: Int, xp: Int, var potionInventory: MutableList<Potion>, var position: Position,icon: Char ) : Being(name, hp, attack, xp, icon) {
    fun Attack(monster: Being){
        if (hp >0 && monster.hp > 0){
            println("You attack the monster for " + attack * level+ " damage!")
            monster.hp -= attack * level
        }
    }
    fun MagicAttack(monster: Being){
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
        println("You've picked up a ${potion.name}")
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
        println("Choose where to move? (up, down, left, right): {x:${position.x}, y: ${position.y}}")
        var movementInput = readln()
        when (movementInput.lowercase().trim()) {
            "right" -> position.setY(y+1)
            "left" -> position.y -=1
            "up" -> position.x -=1
            "down" -> position.x += 1
            else -> println("Choose a proper direction. ")
        }
    }


}